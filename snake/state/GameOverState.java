package snake.state;

import jdk.jshell.spi.ExecutionControl;
import snake.engine.GameEngine;

public class GameOverState implements GameState {
    @Override
    public void update(GameEngine game) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getStateName() {
        return "GAME_OVER";
    }
}
