package com.example.student.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressDto {

    private String city;
    private String country;

}
