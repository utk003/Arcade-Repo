package snake;

import java.util.*;

public class Snake {
    public LinkedList<Location> body = new LinkedList<Location>();
    public SnakeGame game;

    private boolean isDead;

    public Snake(SnakeGame g) {
        isDead = false;
        game = g;
        body.add(new Location(game.BOARD_DIM * 3 / 4, game.BOARD_DIM / 2));
    }

    public void grow() {
        Location l = body.getLast();
        int r = l.getRow(), c = l.getCol();
        if (body.size() == 1) {
            if (game.dir == Location.NORTH)
                r++;
            else if (game.dir == Location.SOUTH)
                r--;
            else if (game.dir == Location.EAST)
                c--;
            else if (game.dir == Location.WEST)
                c++;
            if (isValid(r, c)) {
                body.addLast(new Location(r, c));
                return;
            }
        } else {
            body.removeLast();
            Location l2 = body.getLast();
            body.addLast(l);
            int r2 = l2.getRow(), c2 = l2.getCol();
            if (r2 - r == 1)
                r--;
            else if (r2 - r == -1)
                r++;
            else if (c2 - c == 1)
                c--;
            else if (c2 - c == -1)
                c++;
            if (isValid(r, c)) {
                body.addLast(new Location(r, c));
                return;
            }
        }
        r = l.getRow();
        c = l.getCol();
        if (isValid(r - 1, c)) {
            body.addLast(new Location(r - 1, c));
            return;
        }
        if (isValid(r, c - 1)) {
            body.addLast(new Location(r, c - 1));
            return;
        }
        if (isValid(r + 1, c)) {
            body.addLast(new Location(r + 1, c));
            return;
        }
        if (isValid(r, c + 1)) {
            body.addLast(new Location(r, c + 1));
            return;
        }
    }

    public void move(int dir) {
        body.getLast();
        Location l = body.getFirst();
        int r = l.getRow(), c = l.getCol();
        if (dir == Location.NORTH)
            r--;
        else if (dir == Location.SOUTH)
            r++;
        else if (dir == Location.EAST)
            c++;
        else if (dir == Location.WEST)
            c--;
        if (!isValid(r, c)) {
            deathAnimation();
            isDead = true;
            return;
        }
        body.addFirst(new Location(r, c));
        body.removeLast();
    }

    private void deathAnimation() {
        while (body.size() > 0) {
            body.removeFirst();
            game.display.update();
            try {
                Thread.sleep(1000 / game.mps);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    private boolean isValid(int r, int c) {
        if (r >= game.BOARD_DIM || r < 0)
            return false;
        if (c >= game.BOARD_DIM || c < 0)
            return false;
        for (Location l : body)
            if (l.getRow() == r && l.getCol() == c)
                return false;
        return true;
    }

    public boolean isDead() {
        return isDead;
    }
}
