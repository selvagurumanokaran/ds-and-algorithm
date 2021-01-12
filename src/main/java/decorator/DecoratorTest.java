package decorator;

public class DecoratorTest {
    public static void main(String[] args) {
        BasicCar basicCar = new BasicCar();
        LuxuryCarDecorator luxuryCarDecorator = new LuxuryCarDecorator(basicCar);
        Car carDecorator = new SportsCarDecorator(luxuryCarDecorator);
        carDecorator.assemble();
    }
}
