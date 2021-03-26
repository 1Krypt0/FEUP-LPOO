public class StringCaseChanger implements StringTransformer{
    @Override
    public void execute(StringDrink drink) {
        String text = drink.getText();

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i)))
                res.append(Character.toLowerCase(text.charAt(i)));
            else
                res.append(Character.toUpperCase(text.charAt(i)));
        }

        drink.setText(res.toString());
    }

    @Override
    public void undo(StringDrink drink) {
        execute(drink);
    }
}
