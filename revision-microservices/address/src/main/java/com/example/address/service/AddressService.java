package com.example.address.service;

import com.example.address.dto.AddressDto;

public interface AddressService {

    public AddressDto createAddress(AddressDto addressDto);
    public AddressDto getAddress(int addressId);

}
