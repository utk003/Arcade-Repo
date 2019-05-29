import java.util.*;
import java.lang.*;
/**
 * Write a description of class Arcade here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Arcade
{
   ArrayList<String> gameNames;
   public Arcade()
   {
       gameNames = new ArrayList<String>();
       gameNames.add("Pacman");
       gameNames.add("MineSweeper");
       gameNames.add("Breakout");
       gameNames.add("Pong");
       gameNames.add("Snake");
       gameNames.add("2048");
       gameNames.add("FlappyBird");
       gameNames.add("Tetris");
   }
   
   public Game makePacman()
   {
       return new Pacman();
   }
   
   public Game makeMineSweeper()
   {
       return new MineGame();
   }
   
   public Game makeAtari()
   {
       return new Atari();
   }
   
   public Game makePong()
   {
       return new Pong();
   }
   
   public Game makeSnake()
   {
       return new Snake();
   }
   
   public Game make2048()
   {
       return new TwentyGame();
   }
   
   public Game makeFlappyBird()
   {
       return new FloppyGame();
   }
   
   public Game makeTetris()
   {
       return new Tetris();
   }
}
