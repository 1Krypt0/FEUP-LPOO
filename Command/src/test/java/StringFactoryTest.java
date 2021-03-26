import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StringFactoryTest {

    private StringRecipe getRecipe() {
        StringInverter si = new StringInverter();
        StringCaseChanger cc = new StringCaseChanger();
        StringReplacer sr = new StringReplacer('A', 'X');

        List<StringTransformer> transformers = new ArrayList<>();
        transformers.add(si);
        transformers.add(cc);
        transformers.add(sr);

        return new StringRecipe(transformers);
    }

    @Test
    public void ferengiAlreadyOpened() {
        StringBar stringBar = new StringBar();
        StringDrink drink = new StringDrink("AbCd-aBcD");
        StringRecipe recipe = getRecipe();

        FerengiClient client = new FerengiClient();

        // Recipe is ordered immediately
        stringBar.startHappyHour();
        client.wants(drink, recipe, stringBar);
        Assertions.assertEquals("dCbX-DcBa", drink.getText());
    }

    @Test
    public void ferengiStartClosed() {
        StringBar stringBar = new StringBar();
        StringDrink drink = new StringDrink("AbCd-aBcD");
        StringRecipe recipe = getRecipe();

        FerengiClient client = new FerengiClient();
        stringBar.addObserver(client); // this is important!

        client.wants(drink, recipe, stringBar);
        Assertions.assertEquals("AbCd-aBcD", drink.getText());

        // Recipe is only ordered here
        stringBar.startHappyHour();
        Assertions.assertEquals("dCbX-DcBa", drink.getText());
    }

    @Test
    public void romulan() {
        StringBar stringBar = new StringBar();
        StringDrink drink = new StringDrink("AbCd-aBcD");
        StringRecipe recipe = getRecipe();

        RomulanClient client = new RomulanClient();

        // Recipe is ordered immediately
        client.wants(drink, recipe, stringBar);
        Assertions.assertEquals("dCbX-DcBa", drink.getText());
    }

}
