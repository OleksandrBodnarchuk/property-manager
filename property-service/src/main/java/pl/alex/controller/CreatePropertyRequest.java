package pl.alex.controller;

public record CreatePropertyRequest(String name, String street, String state,String city, String country, String zip) {
}
