import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class CommandSender {
    private final BufferedReader reader;
    private final BufferedWriter writer;

    public CommandSender(BufferedReader reader, BufferedWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public int sendMove() throws IOException {
        writer.write("1");
        writer.newLine();
        writer.flush();

        return Integer.parseInt(reader.readLine());
    }

    public void sendRotate(int direction) throws IOException {
        writer.write("2 " + direction);
        writer.newLine();
        writer.flush();
        reader.readLine();
    }

    public String[] sendMakeFire(int lookDistance) throws IOException {
        writer.write("3");
        writer.newLine();
        writer.flush();

        int size = 2 * lookDistance + 1;
        String[] visible = new String[size];
        for (int i = 0; i < size; i++) {
            visible[i] = reader.readLine();
        }

        return visible;
    }

    public void sendFinish(long timeTotal) throws IOException {
        writer.write("4 " + timeTotal);
        writer.newLine();
        writer.flush();
    }
}
