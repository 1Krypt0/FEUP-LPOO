import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {

    private int width;
    private int height;
    Hero hero = new Hero();

    public Arena(int width, int height){
        this.height = height;
        this.width = width;
    }

    public void processKey(KeyStroke key) throws IOException {
        if(key.getKeyType() == KeyType.ArrowUp) moveHero(hero.MoveUp());
        if(key.getKeyType() == KeyType.ArrowDown) moveHero(hero.MoveDown());
        if(key.getKeyType() == KeyType.ArrowLeft) moveHero(hero.MoveLeft());
        if(key.getKeyType() == KeyType.ArrowRight) moveHero(hero.MoveRight());
    }

    public void draw(Screen screen) throws IOException {
        hero.draw(screen);
    }

    public boolean canMoveHero(Position position){
        if(position.getX() >= 0 && position.getX() <= width){
            return position.getY() >= 0 && position.getX() <= height;
        }
        return false;
    }

    public void moveHero(Position position){
        if(canMoveHero(position)){
            hero.setPosition(position);
        }
    }
}
