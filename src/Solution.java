import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
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

            System.err.printf("start: %d, %d; dir: %d, %d; A: %d; B: %d; C: %d; K: %d\n",
                    x, y, x_1, y_1, A, B, C, K);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
