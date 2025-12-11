package snake.input;

import snake.engine.GameEngine;

public interface GameCommand {
    void execute(GameEngine game);
}
