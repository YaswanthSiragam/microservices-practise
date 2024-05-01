package com.example.address.service.impl;

import com.example.address.dto.AddressDto;
import com.example.address.model.Address;
import com.example.address.repository.AddressRepository;
import com.example.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public AddressDto createAddress(AddressDto addressDto) {
        Address address = Address.builder()
                .city(addressDto.getCity())
                .country(addressDto.getCountry())
                .build();
        Address savedAddress = addressRepository.save(address);
        return AddressDto.builder()
                .city(savedAddress.getCity())
                .country(savedAddress.getCountry())
                .build();
    }

    @Override
    public AddressDto getAddress(int addressId) {
        Address savedAddress = addressRepository.findById(addressId).get();
        return AddressDto.builder()
                .city(savedAddress.getCity())
                .country(savedAddress.getCountry())
                .build();
    }

}
