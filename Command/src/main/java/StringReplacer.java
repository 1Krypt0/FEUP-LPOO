public class StringReplacer implements StringTransformer{

    private Character old, replacement;

    public StringReplacer(Character old, Character replacement) {
        this.old = old;
        this.replacement = replacement;
    }

    @Override
    public void execute(StringDrink drink) {
        String text = drink.getText();

        text = text.replace(old, replacement);

        drink.setText(text);
    }

    @Override
    public void undo(StringDrink drink) {
        String text = drink.getText();

        text = text.replace(replacement, old);

        drink.setText(text);
    }
}
