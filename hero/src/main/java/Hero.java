import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Hero {

    private Position position = new Position();

    public Hero(){
        position.setX(10);
        position.setY(10);
    }

    public int getY() {
        return position.getY();
    }

    public void setY(int y) {
        this.position.setY(y);
    }

    public int getX() {
        return position.getX();
    }

    public void setX(int x) {
        this.position.setX(x);
    }

    public void setPosition(Position pos){
        this.position = pos;
    }

    public Position MoveUp(){
       ;return new Position(position.getX(), position.getY() - 1);
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

    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");
    }
}
