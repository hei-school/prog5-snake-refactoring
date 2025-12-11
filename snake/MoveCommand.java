package snake;

public class MoveCommand implements Command {

    private final Direction dir;

    public MoveCommand(Direction dir) {
        this.dir = dir;
    }

    @Override
    public void execute(Game game) {
        game.getSnake().setDirection(dir);
    }
}
