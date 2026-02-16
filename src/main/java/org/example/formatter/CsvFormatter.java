package org.example.formatter;

import java.util.Optional;

import org.example.model.Address;
import org.example.model.Person;

public class CsvFormatter implements PersonFormatter {

    private final boolean header;
    private static final String HEADER = "first-name,last-name,age,street,city,zip-code";

    public CsvFormatter(boolean header) {
        this.header = header;
    }

    @Override
    public String format(Person person) {
        StringBuilder result = new StringBuilder();
        if (header) {
            result.append(HEADER).append("\n");
        }
        result.append(person.getFirstName()).append(",");
        result.append(person.getLastName()).append(",");
        result.append(Optional.ofNullable(person.getAge()).map(String::valueOf).orElse("")).append(",");
        if (person.getAddress() != null) {
            Address address = person.getAddress();
            result.append(address.getStreet() != null ? address.getStreet() : "").append(",");
            result.append(address.getCity() != null ? address.getCity() : "").append(",");
            result.append(address.getZipCode() != null ? address.getZipCode() : "");
        } else {
            result.append(",,");
        }

        return result.toString();
    }
}
