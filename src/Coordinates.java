public record Coordinates(int x, int y) {

    public Coordinates add(Coordinates other) {
        return new Coordinates(x + other.x, y + other.y);
    }

    public Coordinates rotateRight() {
        return new Coordinates(y, -x);
    }

    public Coordinates rotateLeft() {
        return new Coordinates(-y, x);
    }
}
