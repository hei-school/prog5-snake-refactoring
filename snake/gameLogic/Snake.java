package snake.gameLogic;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private final List<int[]> body = new ArrayList<>();

    public Snake(int startX, int startY) {
        body.add(new int[]{startX, startY});
        body.add(new int[]{startX, startY - 1});
        body.add(new int[]{startX, startY - 2});
    }

    public List<int[]> getBody() {
        return body;
    }
}

