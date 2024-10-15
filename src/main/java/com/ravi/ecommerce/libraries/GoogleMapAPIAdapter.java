package com.ravi.ecommerce.libraries;

import com.ravi.ecommerce.libraries.modals.GLocation;
import com.ravi.ecommerce.libraries.modals.GoogleMapsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GoogleMapAPIAdapter implements MapApiAdapter {

    private GoogleMapsApi googleMapsApi;
    public GoogleMapAPIAdapter() {
        this.googleMapsApi = new GoogleMapsApi();
    }

    @Override
    public int estimate(GLocation src, GLocation dest) {
        return googleMapsApi.estimate(src, dest);
    }
}
