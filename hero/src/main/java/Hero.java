import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;


public class Hero extends Element{

    public Hero(int x, int y) {
        super(x, y);
    }

    public Position MoveUp(){
       return new Position(position.getX(), position.getY() - 1);
    }

    public Position MoveDown(){
        return new Position(position.getX(), position.getY() + 1);
    }

    public Position MoveLeft(){
        return new Position(position.getX() - 1, position.getY());
    }

    public Position MoveRight(){
        return new Position(position.getX() + 1, position.getY());
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#0E5135"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");
    }
}
