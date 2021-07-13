package entity;

import java.io.Console;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class MethodSet {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void postSwitch() {
        Console console = System.console();
        Reader reader = console.reader();

        try {
            System.out.print("Enter any key to return...");
            reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String formatAsTable(List<List<String>> rows) {
        int[] max_lengths = new int[rows.get(0).size()];
        for (List<String> row : rows) {
            for (int i = 0; i < row.size(); i++) {
                max_lengths[i] = Math.max(max_lengths[i], row.get(i).length());
            }
        }

        StringBuilder formatter = new StringBuilder();
        for (int max_length : max_lengths) {
            formatter.append("%-").append(max_length + 2).append("s");
        }

        StringBuilder table = new StringBuilder();
        for (List<String> row : rows) {
            table.append(String.format(formatter.toString(), row.toArray(new Object[0])))
                    .append("\n");
        }
        return table.toString();
    }
}
