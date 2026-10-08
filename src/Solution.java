import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

import java.util.*;

public class Solution {
    private static void stepFollowPath(List<Coordinates> path, Minotaur minotaur,
                                       CommandSender sender) throws IOException {
        if (path == null || path.size() <= 1) return;

        Coordinates currentPosition = minotaur.getPosition();
        Coordinates nextPosition = path.get(1);
        Coordinates direction = new Coordinates(nextPosition.x() - currentPosition.x(),
                nextPosition.y() - currentPosition.y());

        while (!minotaur.getDirection().equals(direction)) {
            sender.sendRotate(1);
            minotaur.makeRotateRight();
        }

        int stepResult = sender.sendMove();
        minotaur.makeMove(stepResult == 1);
    }

    private static List<Coordinates> pathToNearestUnvisited(Minotaur minotaur) {
        Coordinates startPosition = minotaur.getPosition();
        Set<Coordinates> visited = minotaur.getVisited();
        Map<Coordinates, Character> info = minotaur.getKnown();

        Set<Coordinates> visitedBFS = new HashSet<>();
        Queue<Coordinates> queue = new ArrayDeque<>();
        Map<Coordinates, Coordinates> parent = new HashMap<>();
        queue.offer(startPosition);
        visitedBFS.add(startPosition);

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();

            if (!current.equals(startPosition) && info.get(current) == '_' && !visited.contains(current)) {
                List<Coordinates> path = new ArrayList<>();
                while (current != null) {
                    path.add(current);
                    current = parent.get(current);
                }
                Collections.reverse(path);

                return path;
            }

            for (int i = 0; i < 4; i++) {
                Coordinates newCoordinates = new Coordinates(current.x() + dx[i], current.y() + dy[i]);
                if (info.get(newCoordinates) == '_' && !visitedBFS.contains(newCoordinates)) {
                    parent.put(newCoordinates, current);
                    queue.offer(newCoordinates);
                    visitedBFS.add(newCoordinates);
                }
            }
        }

        return null;
    }

    private static boolean shouldMakeFire(Minotaur minotaur) {
        return minotaur.getMakeFireTime() <= minotaur.getMoveTime() * minotaur.getLookDistance();
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            String[] line = reader.readLine().trim().split("\\s+");
            if (line.length != 8) {
                System.err.println("Expected 8 numbers");
                System.exit(1);
            }

            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            int x_1 = Integer.parseInt(line[2]);
            int y_1 = Integer.parseInt(line[3]);
            int A = Integer.parseInt(line[4]);
            int B = Integer.parseInt(line[5]);
            int C = Integer.parseInt(line[6]);
            int K = Integer.parseInt(line[7]);

            Minotaur minotaur = new Minotaur(x, y, x_1, y_1, A, B, C, K);
            boolean usedFire = false;
            CommandSender sender = new CommandSender(reader, writer);

            while (!minotaur.isConfident()) {
                List<Coordinates> path = pathToNearestUnvisited(minotaur);
                if (path == null) {
                    String[] visible = sender.sendMakeFire(minotaur.getLookDistance());
                    minotaur.makeFire(visible);
                    usedFire = true;
                } else if (shouldMakeFire(minotaur) && !usedFire) {
                    String[] visible = sender.sendMakeFire(minotaur.getLookDistance());
                    minotaur.makeFire(visible);
                    usedFire = true;
                } else {
                    stepFollowPath(path, minotaur, sender);
                    usedFire = false;
                }
            }

            sender.sendFinish(minotaur.getTimeTotal());

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
