package com.example.address.controller;

import com.example.address.dto.AddressDto;
import com.example.address.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apis/address/")
@Slf4j
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping("add")
    public ResponseEntity<AddressDto> addAddress(@RequestBody AddressDto addressDto) {
        AddressDto savedAddressDto = addressService.createAddress(addressDto);
        return new ResponseEntity<>(savedAddressDto, HttpStatus.OK);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<AddressDto> findAddress(@PathVariable("id") int addressId) {
        log.info("inside address controller");
        AddressDto addressDto = addressService.getAddress(addressId);
        return new ResponseEntity<>(addressDto, HttpStatus.OK);
    }

}
