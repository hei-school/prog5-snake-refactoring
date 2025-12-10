package snake;

public class Position {
    private int screenWidth;
    private int screenHeight;

    public Position(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public Position move(Direction d) {
        switch (d) {
            case LEFT:  return new Position(screenWidth, screenHeight - 1);
            case RIGHT: return new Position(screenWidth, screenHeight + 1);
            case UP:    return new Position(screenWidth - 1, screenHeight);
            case DOWN:  return new Position(screenWidth + 1, screenHeight);
        }
        return this;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }
}
