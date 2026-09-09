public class CarDirector {

    public Car makeSportsCar(Car.Builder builder) {
        return builder
                .setModel("Sports Coupe")
                .setSeats(2)
                .setEngine("3.0L Turbo")
                .setGPS(true)
                .setTripComputer(true)
                .setColor("Red")
                .build();
    }

    public Car makeFamilyCar(Car.Builder builder) {
        return builder
                .setModel("Family SUV")
                .setSeats(5)
                .setEngine("2.0L Hybrid")
                .setGPS(true)
                .setColor("Silver")
                .build();
    }
}
