package org.example.formatter;

import java.util.Optional;

import org.example.model.Address;
import org.example.model.Person;

public class PrettyStringFormatter implements PersonFormatter {

    @Override
    public String format(Person person) {
        StringBuilder result = new StringBuilder();
        result.append("First Name: ").append(person.getFirstName()).append("\n")
                .append("Last Name: ").append(person.getLastName()).append("\n");
        Optional.ofNullable(person.getAge()).ifPresent(age -> result.append("Age: ").append(age).append("\n"));
        if (person.getAddress() != null) {
            Address address = person.getAddress();
            if (address.getStreet() != null || address.getCity() != null || address.getZipCode() != null) {
                result.append("Address:").append("\n");
                if (address.getStreet() != null) {
                    result.append("  Street: ").append(address.getStreet()).append("\n");
                }
                if (address.getCity() != null) {
                    result.append("  City: ").append(address.getCity()).append("\n");
                }
                if (address.getZipCode() != null) {
                    result.append("  Zip: ").append(address.getZipCode()).append("\n");
                }
            }
        }
        return result.toString();
    }
}
