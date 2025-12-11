package snake;

import snake.model.Direction;
import snake.model.Game;

public class Main {
    public static void main(String[] args) throws Exception {
        Game game = new Game();

        while (true) {
            if (System.in.available() > 0) {
                char c = (char) System.in.read();
                switch (c) {
                    case 'w' -> game.getSnake().setDirection(Direction.UP);
                    case 's' -> game.getSnake().setDirection(Direction.DOWN);
                    case 'a' -> game.getSnake().setDirection(Direction.LEFT);
                    case 'd' -> game.getSnake().setDirection(Direction.RIGHT);
                }
            }

            if (!game.update()) {
                System.out.println("GAME OVER!");
                Thread.sleep(1000);
                game.reset();
            }

            game.render();
            Thread.sleep(Game.TICK);
        }
    }
}
