import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class Solution {
    private static int moveAction(BufferedReader reader, BufferedWriter writer) throws IOException{
        writer.write("1");
        writer.newLine();
        writer.flush();

        String systemResponse = reader.readLine();
        return Integer.parseInt(systemResponse);
    }

    private static void rotateAction(BufferedReader reader, BufferedWriter writer, int direction) throws IOException {
        writer.write("2 " + direction);
        writer.newLine();
        writer.flush();

        reader.readLine();
    }

    private static String[] makeFireAction(BufferedReader reader, BufferedWriter writer, int K) throws IOException {
        writer.write("3");
        writer.newLine();
        writer.flush();

        int size = 2 * K + 1;
        String[] visible = new String[size];
        for (int i = 0; i < size; i++) {
            visible[i] = reader.readLine();
        }

        return visible;
    }

    private static void finish(BufferedWriter writer, long timeTotal) throws IOException {
        writer.write("4 " + timeTotal);
        writer.newLine();
        writer.flush();
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
            System.err.println("minotaur: " + minotaur.getPosition().toString());

            while (!minotaur.isConfident()) {
                break;
            }

            String[] visible = makeFireAction(reader, writer, K);
            minotaur.makeFire(visible);
            System.err.println("after making fire:");

            finish(writer, minotaur.getTimeTotal());

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
