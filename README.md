# Person Formatter CLI

Aplicacion de linea de comandos en Java que formatea datos de personas en diferentes formatos de salida.

## Estructura del proyecto

```
src/main/java/org/example/
├── PersonFormatterCli.java          # Punto de entrada (CLI)
├── model/
│   ├── Person.java                  # Modelo de persona (@Value @Builder)
│   └── Address.java                 # Modelo de direccion (@Value @Builder)
└── formatter/
    ├── PersonFormatter.java         # Interfaz de formateo
    ├── PrettyStringFormatter.java   # Formato legible (texto plano)
    └── CsvFormatter.java            # Formato CSV
```

## Diseño 

### Modelos

`Person` y `Address` son clases generadas con Lombok (`@Value`) que utilizan el patron Builder (`@Builder`) para su construccion. `Person` contiene campos obligatorios (`firstName`, `lastName`) y opcionales (`age`, `address`).

### Estrategias

La interfaz `PersonFormatter` define un unico metodo `format(Person)` que retorna un `String`. Cada implementacion encapsula una estrategia de formateo distinta:

- **PrettyStringFormatter** - Genera una representacion legible con etiquetas (`First Name:`, `Age:`, etc.). 
- **CsvFormatter** - Genera una linea CSV con las columnas: `first-name,last-name,age,street,city,zip-code`. Acepta un parametro `header` para incluir o no la cabecera.

### CLI

`PersonFormatterCli` selecciona el formateador mediante un `switch` sobre el primer argumento y aplica el formato a un listado de personas.

## Uso

```bash
# Formato texto legible
java -cp build/classes/java/main org.example.PersonFormatterCli pretty-string

# Formato CSV sin cabecera
java -cp build/classes/java/main org.example.PersonFormatterCli csv

# Formato CSV con cabecera
java -cp build/classes/java/main org.example.PersonFormatterCli csv true
```

## Tests

```bash
./gradlew test
```

Se incluyen tests unitarios para `PrettyStringFormatter` y `CsvFormatter` cubriendo todos los campos, campos nulos, direcciones parciales y la opcion de cabecera CSV.

