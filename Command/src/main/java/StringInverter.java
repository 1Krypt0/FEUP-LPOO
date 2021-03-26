public class StringInverter implements StringTransformer{
    @Override
    public void execute(StringDrink drink) {
        String text = drink.getText();

        StringBuilder builder = new StringBuilder();

        builder.append(text);

        drink.setText(builder.reverse().toString());
    }

    @Override
    public void undo(StringDrink drink) {
        String text = drink.getText();

        StringBuilder builder = new StringBuilder();

        builder.append(text);

        drink.setText(builder.reverse().toString());
    }
}
