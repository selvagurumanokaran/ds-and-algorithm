package decorator;

public class BasicCar implements Car {
    @Override
    public void assemble() {
        System.out.println("Building basic car");
    }
}
