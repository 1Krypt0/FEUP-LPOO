public class House implements HasArea{
    private int area;

    @Override
    public double getArea() {
        return area;
    }

    public House(int area) {
        this.area = area;
    }
}
