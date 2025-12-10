package snake;

public class Food {
    private String output = "*";
    private Position position;

    public Food(Position position) {
        this.output = output;
        this.position = position;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
