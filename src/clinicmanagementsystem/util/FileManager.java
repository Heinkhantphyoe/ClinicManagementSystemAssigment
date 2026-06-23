package clinicmanagementsystem.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import clinicmanagementsystem.model.User;

public class FileManager {

    // Method to load data from a file directly into a JTable model
    public static void loadDataToTable(String fileName, DefaultTableModel model) {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String eachLine;
            boolean firstLine = true;
            while ((eachLine = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                if (eachLine.trim().isEmpty()) {
                    continue;
                }
                String[] eachLineValue = eachLine.split(",");
                model.addRow(eachLineValue);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to count lines in a file so we can create an array of the right size
    public static int countLines(String fileName) {
        int count = 0;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            boolean firstLine = true;
            while (br.readLine() != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                count++;
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    // Method to rewrite the entire file from the table model
    public static void rewriteFile(String fileName, DefaultTableModel model) {
        try {
            String header = null;
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                header = br.readLine();
            } catch (Exception e) {}

            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            
            if (header != null) {
                bw.write(header);
                bw.newLine();
            }

            int columnCount = model.getColumnCount();
            for (int i = 0; i < model.getRowCount(); i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < columnCount; j++) {
                    sb.append(model.getValueAt(i, j));
                    if (j < columnCount - 1) {
                        sb.append(",");
                    }
                }
                bw.write(sb.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to read all lines from a file into a standard String array
    public static String[] readLines(String fileName) throws IOException {
        int size = countLines(fileName);
        String[] lines = new String[size];
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String eachLine;
            int index = 0;
            boolean firstLine = true;
            while ((eachLine = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                lines[index] = eachLine;
                index++;
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    // Method to write a standard String array to a file
    public static void writeLines(String fileName, String[] lines) throws IOException {
        try {
            String header = null;
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                header = br.readLine();
            } catch (Exception e) {}

            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);

            if (header != null) {
                bw.write(header);
                bw.newLine();
            }

            for (int i = 0; i < lines.length; i++) {
                if (lines[i] != null) {
                    bw.write(lines[i]);
                    bw.newLine();
                }
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to append a single line to a file
    public static void appendLine(String fileName, String line) throws IOException {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(line);
            bw.newLine();
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
