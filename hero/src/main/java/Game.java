import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class Game {

    private Screen screen;
    private final Arena arena = new Arena(125, 50);

    public Game(){
        try {
            Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(arena.getWidth(), arena.getHeight())).createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void draw() throws IOException{
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    public void run() throws IOException {
        while (true){
            draw();
            if (arena.getHero().getLife() == 0){
                System.out.println("You lost! Sorry..");
                System.out.println("But at least you had a score of " + arena.getHero().getScore());;
                screen.close(); break;
            }
            if (arena.noMoreCoins()){
                screen.close(); break;
            }
            KeyStroke key = screen.readInput();
            processKey(key);
            if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                screen.close();
            if(key.getKeyType() == KeyType.EOF)
                break;
        }
    }


    private void processKey(KeyStroke key) {
        arena.processKey(key);
    }
}
