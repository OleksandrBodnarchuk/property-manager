package pl.alex.dto;

import lombok.Builder;
import lombok.Getter;
import pl.alex.controller.CreatePropertyRequest;

import java.util.UUID;

@Builder
@Getter
public class Property {
    private UUID propertyId;

    private String name;

    private PropertyAddress address;

    public static Property from(CreatePropertyRequest request) {
        return Property.builder()
                .name(request.name())
                .address(
                        new PropertyAddress(
                                request.street(),
                                request.city(),
                                request.state(),
                                request.zip(),
                                request.country())
                ).build();
    }
}
