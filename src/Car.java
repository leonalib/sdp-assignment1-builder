/**
 * Product: Car.
 * The Builder is a static nested class right inside Car — this is the
 * exact style shown in the lecture's Car.Builder example.
 */
public class Car {

    // Required
    private final String model;
    private final int seats;

    // Optional
    private final String engine;
    private final boolean hasGPS;
    private final boolean hasTripComputer;
    private final String color;

    // Private constructor: a Car can only be created through Builder.build()
    private Car(Builder builder) {
        this.model = builder.model;
        this.seats = builder.seats;
        this.engine = builder.engine;
        this.hasGPS = builder.hasGPS;
        this.hasTripComputer = builder.hasTripComputer;
        this.color = builder.color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", seats=" + seats +
                ", engine='" + engine + '\'' +
                ", hasGPS=" + hasGPS +
                ", hasTripComputer=" + hasTripComputer +
                ", color='" + color + '\'' +
                '}';
    }

    /**
     * Builder for Car.
     * Every setter returns "this" (the builder itself) so calls can be
     * chained: new Car.Builder().setModel(...).setSeats(...).build()
     */
    public static class Builder {

        private static final String DEFAULT_ENGINE = "1.6L Petrol";
        private static final String DEFAULT_COLOR = "White";

        private String model;
        private int seats;
        private String engine = DEFAULT_ENGINE;
        private boolean hasGPS = false;
        private boolean hasTripComputer = false;
        private String color = DEFAULT_COLOR;

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setSeats(int seats) {
            this.seats = seats;
            return this;
        }

        public Builder setEngine(String engine) {
            this.engine = engine;
            return this;
        }

        public Builder setGPS(boolean hasGPS) {
            this.hasGPS = hasGPS;
            return this;
        }

        public Builder setTripComputer(boolean hasTripComputer) {
            this.hasTripComputer = hasTripComputer;
            return this;
        }

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        /**
         * Clean Code principle: Validated construction.
         * build() checks the required fields first and throws a clear
         * error instead of silently creating a broken Car.
         */
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
    }
}
