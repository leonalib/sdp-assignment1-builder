# Car Builder — Assignment #1 (Builder Pattern)

This is a Java project. It shows the Builder design pattern with a `Car` object.

## Files

- `Car.java` — the car object. It has a `Builder` class inside.
- `CarDirector.java` — this class makes 2 ready cars: a sports car and a family car.
- `Main.java` — this file runs the program and prints the results.

## How to run

Open `Main.java` in IntelliJ and click Run. Or use the terminal:

```bash
javac -d out $(find src -name "*.java")
java -cp out com.aisha.builder.Main
```

## How it works

A `Car` needs some things (model, seats) and can have extra things (engine, GPS, trip computer, color). Instead of one big constructor, we build the car step by step:

```java
Car car = new Car.Builder()
        .setModel("Sports Coupe")
        .setSeats(2)
        .setEngine("3.0L Turbo")
        .setGPS(true)
        .build();
```

Every method returns `this`, so we can chain the calls together. The `build()` method checks if the important fields are set. If not, it shows an error.

## Clean Code principles

1. **Good names** — `makeSportsCar()` and `makeFamilyCar()` are clear names. They are better than a general name like `make(type)`.
2. **Small methods** — `build()` calls a small method `validate()`. It does not do everything by itself.
3. **Small classes** — `Car` only stores data. `Builder` only builds the car. `Director` only knows the order of steps. `Main` only runs the program.
4. **Validation** — `build()` throws an error if `model` or `seats` are missing. This is better than making a broken `Car`.
5. **No magic strings** — the default engine and color are constants (`DEFAULT_ENGINE`, `DEFAULT_COLOR`). This is better than random text in the code.