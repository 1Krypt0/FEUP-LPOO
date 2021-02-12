import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena {

    private int width;
    private int height;

    private List<Wall> walls;

    Hero hero = new Hero(10, 10);


    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }


    public Arena(int width, int height){
        this.height = height;
        this.width = width;
        this.walls = createWalls();
    }

    private List<Wall> createWalls(){
        List<Wall> walls = new ArrayList<>();

        for(int c = 0; c < width; c++){
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }

        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }

        return walls;
    }

    public void processKey(KeyStroke key) throws IOException {
        if(key.getKeyType() == KeyType.ArrowUp) moveHero(hero.MoveUp());
        if(key.getKeyType() == KeyType.ArrowDown) moveHero(hero.MoveDown());
        if(key.getKeyType() == KeyType.ArrowLeft) moveHero(hero.MoveLeft());
        if(key.getKeyType() == KeyType.ArrowRight) moveHero(hero.MoveRight());
    }

    public void draw(TextGraphics screen) throws IOException {
        screen.setBackgroundColor(TextColor.Factory.fromString("#8C2D19"));
        screen.fillRectangle( new TerminalPosition(0, 0), new TerminalSize(getWidth(), getHeight()), ' ');
        for (Wall wall : walls){
            wall.draw(screen);
        }
        hero.draw(screen);
    }

    public boolean canMoveHero(Position position){
        if (position.getX() < 0) return false;
        if (position.getX() > width - 1) return false;
        if (position.getY() < 0) return false;
        if (position.getY() > height - 1) return false;

        for (Wall wall : walls)
            if (wall.getPosition().equals(position)) return false;

        return true;
    }

    public void moveHero(Position position){
        if(canMoveHero(position)){
            hero.setPosition(position);
        }
    }
}
