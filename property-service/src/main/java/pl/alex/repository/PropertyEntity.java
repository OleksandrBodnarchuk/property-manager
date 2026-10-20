package pl.alex.repository;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.alex.dto.Property;

import java.util.UUID;


@Data
@NoArgsConstructor
@Entity
@Table(
        name = "property",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_property_address",
                        columnNames = {
                                "street",
                                "state",
                                "city",
                                "country",
                                "zip"
                        }
                )
        }
)
public class PropertyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID propertyId;

    private String name;

    @Embedded
    private PropertyAddressEntity address;

    private PropertyEntity(String name, PropertyAddressEntity address) {
        this.propertyId = UUID.randomUUID();
        this.name = name;
        this.address = address;
    }

    public Property toDomain() {
        return Property.builder()
                .propertyId(propertyId)
                .name(name)
                .address(address.toDomain())
                .build();
    }

    public static PropertyEntity fromDomain(Property property) {
        return new  PropertyEntity(property.getName(), PropertyAddressEntity.fromDomain(property.getAddress()));
    }

}
