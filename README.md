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

### 1. Meaningful, intention-revealing names

**Before (bad):**
```java
public Car make(int type) {
    if (type == 1) {
        return builder.setModel("Sports Coupe").setSeats(2).build();
    } else {
        return builder.setModel("Family SUV").setSeats(5).build();
    }
}
```

**After (in this project):**
```java
public Car makeSportsCar(Car.Builder builder) { ... }
public Car makeFamilyCar(Car.Builder builder) { ... }
```

A generic `make(type)` hides intent behind a magic number (`type == 1`). The reader has to trace the `if` branch to know what car they get. Splitting it into `makeSportsCar()` / `makeFamilyCar()` makes each configuration self-documenting — the method name alone tells you the result.

### 2. Small methods, each doing one thing

**Before (bad):**
```java
public Car build() {
    if (model == null || model.isBlank()) {
        throw new IllegalStateException("Cannot build Car: model is required.");
    }
    if (seats <= 0) {
        throw new IllegalStateException("Cannot build Car: seats must be greater than 0.");
    }
    return new Car(this);
}
```

**After (in this project):**
```java
public Car build() {
    validate();
    return new Car(this);
}

private void validate() {
    if (model == null || model.isBlank()) {
        throw new IllegalStateException("Cannot build Car: model is required.");
    }
    if (seats <= 0) {
        throw new IllegalStateException("Cannot build Car: seats must be greater than 0.");
    }
}
```

Cramming validation logic directly into `build()` makes it do two jobs at once: checking rules and constructing the object. Extracting `validate()` gives `build()` a single responsibility — each method now does exactly one thing and reads like a short sentence.

### 3. Small, focused classes

**Before (bad — one "God" class):**
```java
public class Car {
    // fields + validation + construction logic + build-order logic
    // all mixed inside one class
}
```

**After (in this project):**
```java
public class Car { ... }          // stores data only
public static class Builder { ... } // builds the object, step by step
public class CarDirector { ... }    // knows the order of steps for known configs
public class Main { ... }           // runs the program
```

Putting data, construction rules, build order, and program entry point all in one class would make each part harder to change without breaking the others. Splitting them by responsibility — `Car` holds state, `Builder` builds, `Director` orchestrates, `Main` runs — keeps each class small and easy to reason about on its own.

### 4. Validated construction

**Before (bad — silently broken object):**
```java
private Car(Builder builder) {
    this.model = builder.model; // could be null
    this.seats = builder.seats; // could be 0 or negative
}
```

**After (in this project):**
```java
public Car build() {
    validate();
    return new Car(this);
}
```

Without validation, a `Car` with `model = null` or `seats = 0` could exist silently and cause a bug much later, far from where it was created. `validate()` fails fast with a clear `IllegalStateException`, so a broken `Car` can never be constructed in the first place.

### 5. No magic numbers/strings

**Before (bad):**
```java
private String engine = "1.6L Petrol"; // magic string, repeated wherever a default is needed
private String color = "White";
```

**After (in this project):**
```java
private static final String DEFAULT_ENGINE = "1.6L Petrol";
private static final String DEFAULT_COLOR = "White";

private String engine = DEFAULT_ENGINE;
private String color = DEFAULT_COLOR;
```

Hardcoding `"1.6L Petrol"` directly in a field initializer means the value has no name and, if used in more than one place, would need to be changed in every spot by hand. Naming it `DEFAULT_ENGINE` makes the intent clear and gives a single place to update the default later.
