package snake.goodSnake;

public class StartGameCommandInterface implements CommandInterface {
    private final Game game;

    public StartGameCommandInterface(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.start();
    }
}
