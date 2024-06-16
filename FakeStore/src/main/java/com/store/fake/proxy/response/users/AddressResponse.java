package com.store.fake.proxy.response.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
    private GeolocationResponse geolocation;
    private String city;
    private String street;
    private String number;
    private String zipcode;
}
