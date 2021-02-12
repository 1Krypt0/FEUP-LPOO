import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element{

    public Monster(int x, int y){
        super(x, y);
    }

    public Position move(){
        Random random = new Random();
        int rand = random.nextInt(4);
        if (rand == 0) return moveLeft();
        if (rand == 1) return moveRight();
        if (rand == 2) return moveUp();
        else return moveDown();
    }

    private Position moveLeft() {
        return new Position(getPosition().getX() - 1, getPosition().getY());

    }

    private Position moveRight() {
        return new Position(getPosition().getX() + 1, getPosition().getY());
    }

    private Position moveUp() {
        return new Position(getPosition().getX(), getPosition().getY() - 1);
    }

    private Position moveDown() {
        return new Position(getPosition().getX(), getPosition().getY() + 1);
    }

    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#494B4B"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "Z");
    }
}
