package food.ordering.system.system.order.service.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

/**
 * @author kalvens on 3/31/23
 * @project food-ordering-system
 */

/**
 * <p>StreetAddress is a immutable class, once instantiated, values won't be changed</p>
 * <p>Id field is not used to calculate equals() and hashCode() methods, it's only used to persist information in
 * data layer</p>
 */
public class StreetAddress {
    private final UUID id;
    private final String city;
    private final String postalCode;
    private final String street;

    public StreetAddress(UUID id, String city, String postalCode, String street) {
        this.id = id;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
    }

    public UUID getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStreet() {
        return street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StreetAddress that = (StreetAddress) o;
        return Objects.equals(city, that.city) && Objects.equals(postalCode, that.postalCode) && Objects.equals(street, that.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, postalCode, street);
    }
}
