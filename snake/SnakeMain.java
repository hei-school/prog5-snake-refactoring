package snake;

public class SnakeMain {

    public static void main(String[] args) throws Exception {
        Game game = new Game();

        while (true) {

            if (System.in.available() > 0) {
                game.handleInput((char) System.in.read());
            }

            if (!game.update()) {
                System.out.println("GAME OVER - SCORE = " + game.getScore());
                break;
            }

            game.render();

            Thread.sleep(Game.TICK_DELAY);
        }
    }
}