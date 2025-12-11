package snake.input;

import snake.engine.GameEngine;
import snake.entities.Direction;

public class ChangeDirectionCommand implements GameCommand {
    private final Direction direction;

    public ChangeDirectionCommand(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void execute(GameEngine game) {
        game.changeSnakeDirection(direction);
    }
}
