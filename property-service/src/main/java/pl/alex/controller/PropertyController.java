package pl.alex.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.alex.dto.Property;
import pl.alex.service.PropertyService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/property")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PropertyController {

    PropertyService propertyService;

    @PostMapping
    public ResponseEntity<String> createProperty(@RequestBody CreatePropertyRequest request) {
        propertyService.create(Property.from(request));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyResponse> getProperty(@PathVariable(name = "id") UUID id) {
        return new ResponseEntity<>(propertyService.find(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PropertyResponse>> getProperties() {
        return new ResponseEntity<>(propertyService.getProperties(), HttpStatus.CREATED);
    }
}
