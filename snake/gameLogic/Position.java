package snake.gameLogic;

import java.io.IOException;

public class Position {
    private Direction direction;



    public void setDirection() throws IOException {
        while (true) {
            if (System.in.available() > 0) {
                char c = (char) System.in.read();
                if (c == 'a' && !direction.equals("R")) direction = Direction.valueOf("L");
                else if (c == 'd' && !direction.equals("L")) direction = Direction.valueOf("R");
                else if (c == 'w' && !direction.equals("D")) direction = Direction.valueOf("U");
                else if (c == 's' && !direction.equals("U")) direction = Direction.valueOf("D");
            }
    }
}

    public static int[] move(int[] height, Direction direction) {
        int[] key = new int[]{height[0], height[1]};
        if (direction.equals("L")) key[1]--;
        else if (direction.equals("R")) key[1]++;
        else if (direction.equals("U")) key[0]--;
        else if (direction.equals("D")) key[0]++;
        return key;
    }
}
