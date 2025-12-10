package snake;

public class Game {
    private int SCREEN_HEIGHT = 20;
    private int SCREEN_WIDTH = 40;
    private int TICK_DELAY = 120;
    private Food food;
    private Snake snake;
    private int score = 0;

    public Game(){
        snake = new Snake(new Position(SCREEN_HEIGHT / 2,SCREEN_WIDTH / 2),Direction.R);
        food = new Food(SCREEN_HEIGHT,SCREEN_WIDTH);
    }

    public void handleInput() throws Exception {
        if (System.in.available() > 0) {
            char c = (char) System.in.read();
            Direction newDir = switch (c) {
                case 'w' -> Direction.U;
                case 's' -> Direction.D;
                case 'a' -> Direction.L;
                case 'd' -> Direction.R;
                default -> snake.getCurrentDirection();
            };
            snake.setDirection(newDir);
        }
    }

    public void render(){
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < SCREEN_HEIGHT; i++) {
            for (int j = 0; j < SCREEN_WIDTH; j++) {
                Position pos = new Position(i, j);

                if (i == 0 || j == 0 || i == SCREEN_HEIGHT - 1 || j == SCREEN_WIDTH - 1) {
                    stringBuilder.append("X");
                } else if (pos.equals(food.getPosition())) {
                    stringBuilder.append("*");
                } else if (snake.body.contains(pos)) {
                    stringBuilder.append("#");
                } else {
                    stringBuilder.append(" ");
                }
            }
            stringBuilder.append("\n");
        }

        System.out.print("\033[H\033[2J");  // Clear screen
        System.out.flush();
        System.out.println(stringBuilder);
        System.out.println("Score: " + score);
    }

    public void gameOver(){
        System.out.println("Game Over :" + score);
        System.exit(0);
    }

    private void update() {
        Position newHead = snake.move();

        // Vérifier collision avec murs
        if (newHead.getRow() <= 0 || newHead.getRow() >= SCREEN_HEIGHT - 1 ||
                newHead.getColumn() <= 0 || newHead.getColumn() >= SCREEN_WIDTH - 1) {
            gameOver();
        }

        // Vérifier collision avec le corps
        if (snake.body.stream().skip(1).anyMatch(pos -> pos.equals(newHead))) {
            gameOver();
        }

        // Vérifier si nourriture mangée
        if (newHead.equals(food.getPosition())) {
            score++;
            food.generateNewPosition();
        } else {
            snake.removeTail();
        }
    }

    void start() throws Exception {
        while (true) {
            handleInput();
            update();
            render();
            Thread.sleep(TICK_DELAY);
        }
    }
}
