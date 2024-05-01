package com.example.student.feignclients;

import com.example.student.dto.AddressDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "address-service", path = "apis/address/")
public interface AddressFeign {

    @GetMapping("find/{id}")
    public ResponseEntity<AddressDto> findAddress(@PathVariable("id") int addressId);

}
