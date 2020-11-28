import breakout.Breakout;
import flappy.FloppyGame;
import g2048.TwentyGame;
import minesweeper.MineGame;
import pacman.PacmanGame;
import snake.SnakeGame;
import tetris.Tetris;

public class Main {
    public static void main(String[] args) {
        final int GAME_CODE = 3;
        switch ((GAME_CODE % 7 + 7) % 7) {
            case 0:
                new Breakout();
                break;
            case 1:
                new FloppyGame();
                break;
            case 2:
                new TwentyGame();
                break;
            case 3:
                new MineGame();
                break;
            case 4:
                new PacmanGame();
                break;
            case 5:
                new SnakeGame();
                break;
            case 6:
                new Tetris();
                break;

            default:
                break;
        }
    }
}
