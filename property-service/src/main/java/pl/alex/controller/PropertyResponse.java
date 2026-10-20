package pl.alex.controller;

public record PropertyResponse(
        String id,
        String name,
        String street,
        String state,
        String city,
        String country,
        String zip
) {
}
