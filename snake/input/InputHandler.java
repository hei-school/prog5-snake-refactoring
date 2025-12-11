package snake.input;

import snake.entities.Direction;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InputHandler {
    private final Map<Character, GameCommand> keyBindings;

    public InputHandler() {
        this.keyBindings = new HashMap<>();
        initializeKeyBindings();
    }

    private void initializeKeyBindings() {
        keyBindings.put('w', new ChangeDirectionCommand(Direction.UP));
        keyBindings.put('s', new ChangeDirectionCommand(Direction.DOWN));
        keyBindings.put('a', new ChangeDirectionCommand(Direction.LEFT));
        keyBindings.put('d', new ChangeDirectionCommand(Direction.RIGHT));
    }

    public Optional<GameCommand> parseInput(char key) {
        return Optional.ofNullable(keyBindings.get(key));
    }
}
