package snake.state;

import snake.engine.GameEngine;

public interface GameState {
    void update(GameEngine game);

    String getStateName();
}
