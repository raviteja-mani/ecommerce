package com.rvtjakula.resourceservice.services;

import java.util.List;

import com.rvtjakula.resourceservice.entites.Address;
import com.rvtjakula.resourceservice.dtos.AddressDTO;

public interface AddressService {
	
	AddressDTO createAddress(AddressDTO addressDTO);
	
	List<AddressDTO> getAddresses();
	
	AddressDTO getAddress(Long addressId);
	
//	AddressDTO updateAddress(Long addressId, Address address);
//
//	String deleteAddress(Long addressId);
}
