public class HumanClient implements Client{

    OrderingStrategy strategy;

    public HumanClient(OrderingStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void wants(StringDrink drink, StringRecipe recipe, StringBar bar) {
        if (bar.isHappyHour())
            happyHourStarted(bar);
        else
            happyHourEnded(bar);
        strategy.wants(drink, recipe, bar);
    }

    @Override
    public void happyHourEnded(Bar bar) {
        strategy.happyHourEnded(bar);
    }

    @Override
    public void happyHourStarted(Bar bar) {
        strategy.happyHourStarted(bar);
    }
}
