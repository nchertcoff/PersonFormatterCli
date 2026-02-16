package org.example.formatter;

import org.example.model.Address;
import org.example.model.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrettyStringFormatterTest {

    private final PrettyStringFormatter formatter = new PrettyStringFormatter();

    @Test
    void formatWithAllFields() {
        Person person = Person.builder().firstName("Julio").lastName("Nowak")
                .age(34)
                .address(Address.builder().street("Kolorowa").city("Lądek Zdrój").zipCode("57-540").build())
                .build();

        String result = formatter.format(person);

        assertEquals("""
                First Name: Julio
                Last Name: Nowak
                Age: 34
                Address:
                  Street: Kolorowa
                  City: Lądek Zdrój
                  Zip: 57-540
                """, result);
    }

    @Test
    void formatWithOnlyMandatoryFields() {
        Person person = Person.builder().firstName("Stefan").lastName("Siarzewki").build();

        String result = formatter.format(person);

        assertEquals("""
                First Name: Stefan
                Last Name: Siarzewki
                """, result);
    }

    @Test
    void formatWithNullAge() {
        Person person = Person.builder().firstName("Sebastian").lastName("Kowalski").build();

        String result = formatter.format(person);

        assertEquals("First Name: Sebastian\nLast Name: Kowalski\n", result);
    }

    @Test
    void formatWithAllNullAddressFields() {
        Person person = Person.builder().firstName("Sebastian").lastName("Kowalski")
                .address(Address.builder().build())
                .build();

        String result = formatter.format(person);

        assertEquals("First Name: Sebastian\nLast Name: Kowalski\n", result);
    }

    @Test
    void formatWithPartialAddress() {
        Person person = Person.builder().firstName("Julio").lastName("Nowak")
                .age(34)
                .address(Address.builder().street("Kolorowa").build())
                .build();

        String result = formatter.format(person);

        assertEquals("""
                First Name: Julio
                Last Name: Nowak
                Age: 34
                Address:
                  Street: Kolorowa
                """, result);
    }
}
