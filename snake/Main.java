package snake;

public class Main {
    public static void main(String[] args) throws Exception {

        Game game = new Game();

        while (true) {

            if (System.in.available() > 0) {
                char c = (char) System.in.read();
                game.handleInput(c);
            }

            if (!game.update()) {
                System.out.println("GAME OVER - SCORE = " + game.getScore());
                return;
            }

            game.render();
            Thread.sleep(Game.TICK_MS);
        }
    }
}
