package snake;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Game game = new Game();
        boolean running = true;
        System.out.println("Use W/A/S/D to move. Press ENTER to quit.");

        while (running) {
            if (System.in.available() > 0) {
                char c = (char) System.in.read();
                switch (c) {
                    case 'w' -> game.getSnake().setDirection(Direction.UP);
                    case 's' -> game.getSnake().setDirection(Direction.DOWN);
                    case 'a' -> game.getSnake().setDirection(Direction.LEFT);
                    case 'd' -> game.getSnake().setDirection(Direction.RIGHT);
                    case '\n' -> running = false;
                }
            }

            running = game.update();
            game.render();
            Thread.sleep(Game.TICK_DELAY);
        }

        System.out.println("GAME OVER!");
    }
}
