import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Minotaur {
    private Coordinates currentPosition;
    private Coordinates currentDirection;
    private final int moveTime;
    private final int rotateTime;
    private final int makeFireTime;
    private final int lookDistance;

    private long timeTotal;
    private Set<Coordinates> visited;
    private Map<Coordinates, Character> info;

    public Minotaur(int x, int y, int x_1, int y_1, int A, int B, int C, int K) {
        currentPosition = new Coordinates(x, y);
        currentDirection = new Coordinates(x_1 - x, y_1 - y);
        moveTime = A;
        rotateTime = B;
        makeFireTime = C;
        lookDistance = K;

        timeTotal = 0;
        visited = new HashSet<>();
        info = new HashMap<>();
        visited.add(currentPosition);
        info.put(currentPosition, '_');
    }

    public Coordinates getPosition() {
        return currentPosition;
    }

    public long getTimeTotal() {
        return timeTotal;
    }

    public boolean isVisited(Coordinates coordinates) {
        return visited.contains(coordinates);
    }

    public boolean isConfident() {
        for (var coordinates : info.keySet()) {
            if (info.get(coordinates) == '_' && !isVisited(coordinates)) {
                return false;
            }
        }
        return true;
    }

    public void makeMove(boolean success) {
        Coordinates newPosition = currentPosition.add(currentDirection);
        if (success) {
            currentPosition = newPosition;
            visited.add(newPosition);
            info.put(newPosition, '_');
        } else {
            info.put(newPosition, '#');
        }

        timeTotal += moveTime;
    }

    public void makeRotateRight() {
        currentDirection = currentDirection.rotateRight();
        timeTotal += rotateTime;
    }

    public void makeRotateLeft() {
        currentDirection = currentDirection.rotateLeft();
        timeTotal += rotateTime;
    }

    public void makeFire(String[] visible) {
        int size = 2 * lookDistance + 1;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int dx = x - lookDistance;
                int dy = y - lookDistance;
                info.put(new Coordinates(currentPosition.x() + dx, currentPosition.y() + dy),
                        visible[y].charAt(x));
            }
        }
        timeTotal += makeFireTime;
    }
}
