import java.awt.*;
import java.util.ArrayList;

public class StringBar extends Bar{
    private boolean HappyHour;

    public StringBar() {
        super();
        this.HappyHour = false;
    }

    @Override
    public boolean isHappyHour() {
        return super.isHappyHour();
    }

    @Override
    public void startHappyHour() {
        super.startHappyHour();
    }

    @Override
    public void endHappyHour() {
        super.endHappyHour();
    }

    public void order(StringDrink drink, StringRecipe recipe) {
        recipe.mix(drink);
    }
}
