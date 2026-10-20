package pl.alex.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jspecify.annotations.NonNull;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pl.alex.controller.PropertyResponse;
import pl.alex.dto.Property;
import pl.alex.repository.PropertyEntity;
import pl.alex.repository.PropertyRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PropertyService {

    PropertyRepository propertyRepository;

    public void create(Property property) {
        propertyRepository.save(PropertyEntity.fromDomain(property));
    }

    public PropertyResponse find(UUID id) {
        return propertyRepository.findByPropertyId(id)
                .map(PropertyService::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Property not found")); // FIXME: custom exception

    }

    public List<PropertyResponse> getProperties() {
        return propertyRepository.findAll().stream().map(
                PropertyService::mapToResponse).toList();
    }

    // FIXME: Do not use entity
    private static @NonNull PropertyResponse mapToResponse(PropertyEntity propertyEntity) {
        return new PropertyResponse(
                propertyEntity.getPropertyId().toString(),
                propertyEntity.getName(),
                propertyEntity.getAddress().getStreet(),
                propertyEntity.getAddress().getState(),
                propertyEntity.getAddress().getCity(),
                propertyEntity.getAddress().getCountry(),
                propertyEntity.getAddress().getZip()
        );
    }
}
