import java.util.ArrayList;
import java.util.List;

public abstract class Bar {

    private boolean HappyHour;
    private List<BarObserver> observers;

    public Bar() {
        List<BarObserver> list = new ArrayList<>();
        this.observers = list;
        this.HappyHour = false;
    }

    public List<BarObserver> getObservers() {
        return observers;
    }

    public void setHappyHour(boolean happyHour) {
        HappyHour = happyHour;
    }

    public boolean isHappyHour() {
        return HappyHour;
    }

    public void startHappyHour() {
        setHappyHour(true);
        this.notifyObservers();
    }

    public void endHappyHour() {
        setHappyHour(false);
        this.notifyObservers();
    }

    public void addObserver(BarObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(BarObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (BarObserver observer : observers) {
            if (isHappyHour())
                observer.happyHourStarted(this);
            else
                observer.happyHourEnded(this);
        }
    }

}
