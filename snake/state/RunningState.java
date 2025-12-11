package snake.state;

import snake.engine.GameEngine;
import snake.entities.Food;
import snake.entities.Position;
import snake.entities.Snake;
import snake.config.GameConfig;

public class RunningState implements GameState {
    @Override
    public void update(GameEngine game) {
        Snake snake = game.getSnake();
        Food food = game.getFood();

        Position newHead = snake.getHead().move(snake.getCurrentDirection());

        // Check wall collision
        if (isOutOfBounds(newHead)) {
            game.setState(new GameOverState());
            return;
        }

        // Check self-collision
        if (snake.collidesWithBody(newHead)) {
            game.setState(new GameOverState());
            return;
        }

        // Check food collision
        boolean ateFood = food.isAt(newHead);
        snake.move(ateFood);

        if (ateFood) {
            game.incrementScore();
            food.relocate(game.getFactory().createFood(snake).getPosition());
        }
    }

    private boolean isOutOfBounds(Position pos) {
        return pos.getRow() <= 0 ||
                pos.getRow() >= GameConfig.SCREEN_HEIGHT - 1 ||
                pos.getColumn() <= 0 ||
                pos.getColumn() >= GameConfig.SCREEN_WIDTH - 1;
    }


    @Override
    public String getStateName() {
        return "PLAYING";
    }
}
