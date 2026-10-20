package pl.alex.repository;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.alex.dto.PropertyAddress;

@Data
@Builder
@NoArgsConstructor
@Embeddable
public class PropertyAddressEntity {
    private String street;
    private String state;
    private String city;
    private String country;
    private String zip;

    private PropertyAddressEntity(String street,String state, String city, String country, String zip) {
        this.street = street;
        this.state = state;
        this.city = city;
        this.country = country;
        this.zip = zip;
    }

    public PropertyAddress toDomain() {
        return PropertyAddress.builder()
                .street(this.street)
                .state(this.state)
                .city(this.city)
                .country(this.country)
                .zip(this.zip)
                .build();
    }

    public static PropertyAddressEntity fromDomain(PropertyAddress address) {
        return PropertyAddressEntity.builder()
                .street(address.street())
                .state(address.state())
                .city(address.city())
                .country(address.country())
                .zip(address.zip())
                .build();

    }
}
