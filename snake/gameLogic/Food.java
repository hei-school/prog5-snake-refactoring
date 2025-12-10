package snake.gameLogic;

import java.util.Random;

public class Food {
    private int[] placement;

    public Food(int screenHeight, int screenWidth) {
        Random r = new Random();
        this.placement = new int[]{
                r.nextInt(screenHeight - 2) + 1,
                r.nextInt(screenWidth - 2) + 1
        };
    }

    public int[] getPlacement() {
        return placement;
    }
}

