package pl.alex.dto;

import lombok.Builder;

import java.sql.ConnectionBuilder;

@Builder
public record PropertyAddress(String street, String city, String state, String zip, String country) {

}
