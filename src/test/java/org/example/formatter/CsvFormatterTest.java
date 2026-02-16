package org.example.formatter;

import org.example.model.Address;
import org.example.model.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvFormatterTest {

    @Test
    void formatWithHeaderAndAllFields() {
        CsvFormatter formatter = new CsvFormatter(true);
        Person person = Person.builder().firstName("Julio").lastName("Nowak")
                .age(34)
                .address(Address.builder().street("Kolorowa").city("Lądek Zdrój").zipCode("57-540").build())
                .build();

        String result = formatter.format(person);

        assertTrue(result.startsWith("first-name,last-name,age,street,city,zip-code\n"));
        assertTrue(result.endsWith("Julio,Nowak,34,Kolorowa,Lądek Zdrój,57-540"));
    }

    @Test
    void formatWithoutHeader() {
        CsvFormatter formatter = new CsvFormatter(false);
        Person person = Person.builder().firstName("Julio").lastName("Nowak")
                .age(34)
                .address(Address.builder().street("Kolorowa").city("Lądek Zdrój").zipCode("57-540").build())
                .build();

        String result = formatter.format(person);

        assertFalse(result.contains("first-name"));
        assertEquals("Julio,Nowak,34,Kolorowa,Lądek Zdrój,57-540", result);
    }

    @Test
    void formatWithNullAge() {
        CsvFormatter formatter = new CsvFormatter(false);
        Person person = Person.builder().firstName("Sebastian").lastName("Kowalski").build();

        String result = formatter.format(person);

        assertEquals("Sebastian,Kowalski,,,," , result);
    }

    @Test
    void formatWithNullAddress() {
        CsvFormatter formatter = new CsvFormatter(false);
        Person person = Person.builder().firstName("Stefan").lastName("Siarzewki")
                .age(54)
                .build();

        String result = formatter.format(person);

        assertEquals("Stefan,Siarzewki,54,,," , result);
    }

    @Test
    void formatWithAllNullAddressFields() {
        CsvFormatter formatter = new CsvFormatter(false);
        Person person = Person.builder().firstName("Sebastian").lastName("Kowalski")
                .address(Address.builder().build())
                .build();

        String result = formatter.format(person);

        assertEquals("Sebastian,Kowalski,,,," , result);
    }

    @Test
    void formatWithPartialAddress() {
        CsvFormatter formatter = new CsvFormatter(false);
        Person person = Person.builder().firstName("Julio").lastName("Nowak")
                .age(34)
                .address(Address.builder().street("Kolorowa").build())
                .build();

        String result = formatter.format(person);

        assertEquals("Julio,Nowak,34,Kolorowa,,", result);
    }
}
