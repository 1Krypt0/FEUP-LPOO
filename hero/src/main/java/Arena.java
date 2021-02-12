import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {

    private int width;
    private int height;


    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    Hero hero = new Hero(10, 10);

    public Arena(int width, int height){
        this.height = height;
        this.width = width;
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
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

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 15; i++)
            coins.add(new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return coins;
    }

    private List<Monster> createMonsters(){
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 15; i++)
            monsters.add(new Monster(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return monsters;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void processKey(KeyStroke key) throws IOException {
        if(key.getKeyType() == KeyType.ArrowUp) moveHero(hero.MoveUp());
        if(key.getKeyType() == KeyType.ArrowDown) moveHero(hero.MoveDown());
        if(key.getKeyType() == KeyType.ArrowLeft) moveHero(hero.MoveLeft());
        if(key.getKeyType() == KeyType.ArrowRight) moveHero(hero.MoveRight());
        moveMonsters();
    }

    public void draw(TextGraphics screen) throws IOException {
        screen.setBackgroundColor(TextColor.Factory.fromString("#FFC289"));
        screen.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(screen);
        for (Monster monster: monsters){
            monster.draw(screen);
        }
        for(Coin coin: coins){
            coin.draw(screen);
        }
        for (Wall wall : walls){
            wall.draw(screen);
        }
    }

    public boolean canMoveHero(Position position){
        if (position.getX() < 0) return false;
        if (position.getX() > width - 1) return false;
        if (position.getY() < 0) return false;
        if (position.getY() > height - 1) return false;

        for (Wall wall : walls)
            if (wall.getPosition().equals(position)) return false;
        for(Coin coin: coins){
            if(coin.position.equals(position)){
                retrieveCoin(coin);
                break;
            }
        }
        return true;
    }

    public boolean canMoveMonster(Position position){
        if (position.getX() < 0) return false;
        if (position.getX() > width - 1) return false;
        if (position.getY() < 0) return false;
        if (position.getY() > height - 1) return false;

        for (Wall wall : walls)
            if (wall.getPosition().equals(position)) return false;

        return true;
    }

    private void retrieveCoin(Coin coin) {
        coins.remove(coin);
    }

    public void moveHero(Position position){
        if(canMoveHero(position)){
            hero.setPosition(position);
        }
    }

    public void moveMonsters(){
        for (Monster monster: monsters){
            Position pos = new Position(monster.move());
            if (canMoveMonster(pos)){
                monster.setPosition(pos);
            }
        }
    }

    public boolean verifyMonsterCollisions(){
        for (Monster monster: monsters){
            if (monster.getPosition().equals(hero.getPosition())){
                System.out.println("You lost! Sorry...");
                return true;
            }
        }
        return false;
    }

    public boolean noMoreCoins(){
        if (coins.isEmpty()){
            System.out.println("Congratulations! You won the game! Your life is now meaningless");
            return true;
        }
        return false;
    }
}
