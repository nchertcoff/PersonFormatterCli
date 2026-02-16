package org.example.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Address {
    String street;
    String city;
    String zipCode;
}
