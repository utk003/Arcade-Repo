package snake;

public class MyThread extends Thread {
    private SnakeGame g;
    public MyThread(SnakeGame game) {
        g = game;
    }

    public void run() {
        while (!g.hasEnded()) {
            g.update();
            try {
                Thread.sleep(1000 / g.mps);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
