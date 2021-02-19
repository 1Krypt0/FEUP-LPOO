import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {

    private final int width;
    private final int height;

    private final List<Wall> walls;
    private final List<Coin> coins;
    private final List<Monster> monsters;

    Hero hero = new Hero(2, 5);

    public Hero getHero(){
        return hero;
    }

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

        for (int i = 0; i < 30; i++){
            walls.add(new Wall(20 + i, 10));
            walls.add(new Wall(15 + i, 42));
        }

        /* For small vertical walls! */
        for (int i = 0; i < 10; i++){
            walls.add(new Wall(20, i));
            walls.add(new Wall(21, i));
            walls.add(new Wall(38, 20 + i));
            walls.add(new Wall(39, 20 + i));
            walls.add(new Wall(20, 20 + i));
            walls.add(new Wall(21, 20 + i));
        }

        /* For size 20 walls */
        for (int i = 0; i < 20; i++){
            walls.add(new Wall(i, 20));
            walls.add(new Wall(20 + i, 30));
            walls.add(new Wall(105 + i,20));
            walls.add(new Wall(40 + i, 20));
            walls.add(new Wall(60 + i, 15));
            walls.add(new Wall(90 + i, 35));
        }

        for (int i = 0; i < 5; i++){
            walls.add(new Wall(58, 15 + i));
            walls.add(new Wall(59, 15 + i));
        }

        for (int i = 0; i < 15; i++){
            walls.add(new Wall(78, 15 + i));
            walls.add(new Wall(79, 15 + i));
            walls.add(new Wall(90, 35 + i));
            walls.add(new Wall(91, 35 + i));
            walls.add(new Wall(90, i));
            walls.add(new Wall(91, i));
            walls.add(new Wall(59, 35 + i));
            walls.add(new Wall(60, 35 + i));
        }

        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        Position position = new Position(0, 0);
        boolean collision = false;
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            position.setX(random.nextInt(width - 2) + 1);
            position.setY(random.nextInt(height - 2) + 1);
            for (Wall wall: walls){
                if (position.equals(wall.getPosition())){
                    collision = true;
                    break;
                }
            }
            if (!collision){
                coins.add(new Coin(position.getX(), position.getY()));
            }
        }
        return coins;
    }

    private List<Monster> createMonsters(){
        Random random = new Random();
        Position position = new Position(0, 0);
        boolean collision = false;
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            position.setX(random.nextInt(width - 2) + 1);
            position.setY(random.nextInt(height - 2) + 1);
            for (Wall wall: walls){
                if (position.equals(wall.getPosition())){
                    collision = true;
                    break;
                }
            }
            if (!collision){
                monsters.add(new Monster(position.getX(), position.getY()));
            }
        }
        return monsters;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void processKey(KeyStroke key) {
        if(key.getKeyType() == KeyType.ArrowUp) moveHero(hero.MoveUp());
        if(key.getKeyType() == KeyType.ArrowDown) moveHero(hero.MoveDown());
        if(key.getKeyType() == KeyType.ArrowLeft) moveHero(hero.MoveLeft());
        if(key.getKeyType() == KeyType.ArrowRight) moveHero(hero.MoveRight());
        moveMonsters();
    }

    public void draw(TextGraphics screen) {
        screen.setBackgroundColor(TextColor.Factory.fromString("#FFC289"));
        screen.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(screen);
        for (Wall wall : walls){
            wall.draw(screen);
        }
        for (Monster monster: monsters){
            monster.draw(screen);
        }
        for(Coin coin: coins){
            coin.draw(screen);
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
        verifyMonsterCollisions();
    }

    public void moveMonsters(){
        for (Monster monster: monsters){
            Position pos = new Position(monster.move());
            if (canMoveMonster(pos)){
                monster.setPosition(pos);
            }
        }
    }

    public void verifyMonsterCollisions(){
        for (Monster monster: monsters){
            if (monster.getPosition().equals(hero.getPosition())){
                hero.setLife(hero.getLife() - 25);
                System.out.print("Your life is now: ");
                System.out.println(hero.getLife());
            }
        }
    }

    public boolean noMoreCoins(){
        if (coins.isEmpty()){
            System.out.println("Congratulations! You won the game! Life is now meaningless");
            return true;
        }
        return false;
    }
}
