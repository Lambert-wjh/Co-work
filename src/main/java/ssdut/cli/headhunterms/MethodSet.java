/*
 * This file is part of the Auxiliary Methods in the HeadhunterMS project. It contains all Auxiliary
 * Functions to help the other functions work better.
 */
package ssdut.cli.headhunterms;

import java.io.Console;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class MethodSet {
    /*
     * Centered menu display
     *
     * @terminal : Get the terminal of the currently running program. And Initialization in
     * static{...}
     */
    private static final Terminal terminal;

    static {
        Terminal temp = null;
        try {
            temp = TerminalBuilder.terminal();
        } catch (IOException e) {
            e.printStackTrace();
        }
        terminal = temp;
    }

    /*
     * Refresh menu display helper functions :
     *
     * @clearScreen()
     *
     * @postSwitch()
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void postSwitch() {
        Console console = System.console();
        Reader reader = console.reader();

        try {
            System.out.print("Press enter key to return...");
            reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Formatting output helper functions :
     *
     * @formatAsTable
     *
     * @formatMenu
     */
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

    public static String formatMenu(List<String> menu) {
        final int COLUMN = terminal.getWidth();

        int max_length = menu.get(0).length();
        for (String item : menu) {
            max_length = Math.max(max_length, item.length());
        }
        final int WIDTH = (COLUMN + max_length) / 2;

        StringBuilder line_separator = new StringBuilder();
        line_separator.append('+');
        for (int i = 0; i < max_length + 2; i++) {
            line_separator.append('-');
        }
        line_separator.append('+');

        List<String> appended_menu = new ArrayList<>();
        for (String item : menu) {
            appended_menu.add(String.format("| %-" + max_length + "s |", item));
        }

        StringBuilder formated_menu = new StringBuilder();
        formated_menu.append(String.format("%" + WIDTH + "s", line_separator)).append("\n");
        for (String item : appended_menu) {
            formated_menu.append(String.format("%" + WIDTH + "s", item)).append("\n");
            formated_menu.append(String.format("%" + WIDTH + "s", line_separator)).append("\n");
        }

        return formated_menu.toString();
    }
}
