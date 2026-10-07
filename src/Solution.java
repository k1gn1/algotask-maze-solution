import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class Solution {
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
            System.err.println("minotaur: " + minotaur.getPosition().toString());
            while (!minotaur.isConfident()) {
                break;
            }

            writer.write("4 " + minotaur.getTimeTotal());
            writer.newLine();
            writer.flush();

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
