public class Triangle implements AreaShape{
    private int base;
    private int height;

    public Triangle(int base, int height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double getArea() {
        return (double) base * height / 2;
    }

    @Override
    public void draw() {
        System.out.println("Triangle");
    }

    public int getHeight() {
        return height;
    }

    public int getBase() {
        return base;
    }
}
