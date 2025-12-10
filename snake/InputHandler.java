package snake;

import java.io.IOException;

public class InputHandler {
  public Direction readDirection() throws IOException {
    if (System.in.available() > 0) {
      char input = (char) System.in.read();
      return Direction.fromInputKey(input);
    }
    return null;
  }
}
