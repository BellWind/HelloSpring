public class Car {
    private String brand;
    private int speed;
    private String color;
    private double price;
    public Car(String brand, int speed, String color) {
        this.brand = brand;
        this.speed = speed;
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", speed=" + speed +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }
}
