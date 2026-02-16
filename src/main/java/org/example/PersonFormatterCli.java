package org.example;

import java.util.List;

import org.example.formatter.CsvFormatter;
import org.example.formatter.PersonFormatter;
import org.example.formatter.PrettyStringFormatter;
import org.example.model.Address;
import org.example.model.Person;

class PersonFormatterCli {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: PersonFormatterCli <pretty-string|csv> [header]");
            return;
        }

        String outputFormat = args[0];
        PersonFormatter formatter = switch (outputFormat) {
            case "pretty-string" -> new PrettyStringFormatter();
            case "csv" -> {
                boolean header = args.length > 1 && Boolean.parseBoolean(args[1]);
                yield new CsvFormatter(header);
            }
            default -> throw new IllegalArgumentException("Unknown format: " + outputFormat);
        };

        List<Person> db = List.of(
            Person.builder()
                .firstName("Sebastian").lastName("Kowalski")
                .address(Address.builder().build())
                .build(),
            Person.builder()
                .firstName("Julio").lastName("Nowak")
                .age(34)
                .address(Address.builder().street("Kolorowa").city("Lądek Zdrój").build())
                .build(),
            Person.builder()
                .firstName("Stefan").lastName("Siarzewki")
                .age(54)
                .build());

        db.forEach(person -> System.out.println(formatter.format(person)));
    }
}
