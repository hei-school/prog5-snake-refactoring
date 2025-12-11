package snake.goodSnake;

class ChangeDirectionCommandInterface implements CommandInterface {
    private final Snake snake;
    private final Direction newDirection;

    public ChangeDirectionCommandInterface(Snake snake, Direction newDirection) {
        this.snake = snake;
        this.newDirection = newDirection;
    }

    @Override
    public void execute() {
        snake.changeDirection(newDirection);
    }
}
