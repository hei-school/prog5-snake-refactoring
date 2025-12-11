package snake;

public class Main {
    public static void main(String[] args){
        Game game = new Game();
        ConsoleRenderer renderer = new ConsoleRenderer(Game.SCREEN_HEIGHT, Game.SCREEN_WIDTH);

        game.addObserver(renderer);

        renderer.onGameUpdate(game);

        while (!game.isGameOver()) {

            if (System.in.available() > 0) {
                char c = (char) System.in.read();
                game.handleInput(c);
            }

            game.update();

            try {
                Thread.sleep(Game.TICK_DELAY_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
