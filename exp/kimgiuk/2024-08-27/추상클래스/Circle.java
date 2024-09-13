class Circle extends AbstractShape {
    double radius;

    public Circle(double radius) {
        super("원");
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return radius * radius * Math.PI;
    }
}
