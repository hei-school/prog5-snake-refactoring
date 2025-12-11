package snake;

public class StartCommand implements Command {
    @Override
    public void execute(Game game) {
        game.start();
    }
}
