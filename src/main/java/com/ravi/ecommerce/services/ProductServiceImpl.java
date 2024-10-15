package com.ravi.ecommerce.services;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.sound.midi.MidiSystem;

import com.ravi.ecommerce.exceptions.*;
import com.ravi.ecommerce.libraries.GoogleMapAPIAdapter;
import com.ravi.ecommerce.libraries.MapApiAdapter;
import com.ravi.ecommerce.libraries.modals.GLocation;
import com.ravi.ecommerce.libraries.modals.GoogleMapsApi;
import com.ravi.ecommerce.models.*;
import com.ravi.ecommerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jakarta.websocket.server.ServerEndpoint;
@Service
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;
    private AddressRepository addressRepository;
    private MapApiAdapter mapApiAdapter;
    private UserRepository userRepository;
    private DeliveryHubRepository deliveryHubRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              AddressRepository addressRepository,
                              UserRepository userRepository,
                              DeliveryHubRepository deliveryHubRepository) {
        this.productRepository = productRepository;
        this.addressRepository = addressRepository;
        this.mapApiAdapter = new GoogleMapAPIAdapter();
        this.userRepository = userRepository;
        this.deliveryHubRepository = deliveryHubRepository;
    }



    public Date estimateDeliveryDate(int productId, int addressId) throws ProductNotFoundException, AddressNotFoundException {

        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product not found!!");
        }

        Optional<Address> addressOptional = addressRepository.findById(addressId);
        if(addressOptional.isEmpty()){
            throw new AddressNotFoundException("Address not found!!");
        }

        //Gathering lattitude & longitude from address of destination and
        // creating a Glocation object for destion with that
        Address dest = addressOptional.get();
        GLocation destination = new GLocation();
        destination.setLatitude(dest.getLatitude());
        destination.setLongitude(dest.getLongitude());

        //As one product belongs to only one seller, we can get seller from that product
        // And from that seller, we can know about its address and lat&long of address
        //After getting lat&long of seller/ source address, we can create object of Glocation and create object
        Seller seller = productOptional.get().getSeller();
        Address src = seller.getAddress();
        GLocation source = new GLocation();
        source.setLatitude(src.getLatitude());
        source.setLongitude(src.getLongitude());


        //Finding address of Delivery hub location

        Optional<DeliveryHub> hubAddressOptional = deliveryHubRepository.findByAddress_ZipCode(src.getZipCode());
        if(hubAddressOptional.isEmpty()){
            throw new AddressNotFoundException("Delivery hub not found!");
        }

        DeliveryHub deliveryHub = hubAddressOptional.get();
        Address hubAddress = deliveryHub.getAddress();
        GLocation hub = new GLocation();
        hub.setLatitude(hubAddress.getLatitude());
        hub.setLongitude(hubAddress.getLongitude());


        //With help of Glocation source & Glocation destination, we can get time of estimate with GoogleMapApi

        //First time from Source to Delivery hub
        int srcToHubTime = mapApiAdapter.estimate(source,hub);
        //Second time from  Delivery hub to destination
        int hubToDestTime = mapApiAdapter.estimate(hub,destination);

        long sorceToDeliveryHubTime = (long)srcToHubTime*1000;
        long deliveryHubToDestinationTime = (long)hubToDestTime*1000;

        long totalRequiredTime = sorceToDeliveryHubTime+deliveryHubToDestinationTime+System.currentTimeMillis();


        //Instantiating the SimpleDateFormat class
        SimpleDateFormat dateformatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z");
        //Parsing the given String to Date object
        Date date = new Date(totalRequiredTime);


        return date;

    }

}

