public class Main {

    public static void main(String[] args) {
        System.out.println("--- 1) Director builds a sports car ---");
        CarDirector director = new CarDirector();
        Car sportsCar = director.makeSportsCar(new Car.Builder());
        System.out.println(sportsCar);

        System.out.println();
        System.out.println("--- 2) Director builds a family car ---");
        Car familyCar = director.makeFamilyCar(new Car.Builder());
        System.out.println(familyCar);

        System.out.println();
        System.out.println("--- 3) Client builds a fully custom car directly ---");
        Car customCar = new Car.Builder()
                .setModel("Pickup Truck")
                .setSeats(4)
                .setEngine("2.8L Diesel")
                .setColor("Black")
                .build();
        System.out.println(customCar);

        System.out.println();
        System.out.println("--- 4) Validation: build() fails fast without required fields ---");
        try {
            new Car.Builder()
                    .setSeats(4) // model is missing on purpose
                    .build();
        } catch (IllegalStateException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
    }
}
