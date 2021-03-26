import java.util.ArrayList;
import java.util.List;

public class SmartStrategy implements OrderingStrategy{

    private boolean onHold;
    private final List<StringDrink> drinksToOrder;
    private final List<StringRecipe> recipesToOrder;

    public SmartStrategy() {
        this.drinksToOrder = new ArrayList<>();
        this.recipesToOrder = new ArrayList<>();
    }

    @Override
    public void wants(StringDrink drink, StringRecipe recipe, StringBar bar) {
        if (bar.isHappyHour())
            bar.order(drink, recipe);
        else {
            drinksToOrder.add(drink);
            recipesToOrder.add(recipe);
            onHold = true;
        }
    }

    public void wants(List<StringDrink> drinks, List<StringRecipe> recipes, StringBar bar) {
        for (int i = 0; i < drinks.size(); i++) {
            bar.order(drinks.get(i), recipes.get(i));
        }
        onHold = false;
    }

    @Override
    public void happyHourStarted(Bar bar) {
        if (onHold)
            wants(drinksToOrder, recipesToOrder, (StringBar) bar);
    }

    @Override
    public void happyHourEnded(Bar bar) {
    }
}