package io;

import model.CsvRow;
import java.nio.file.*;
import java.util.*;

public class CsvReader {
    public static List<CsvRow> read(String path) throws Exception {
        List<CsvRow> rows = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(path));

        if (lines.isEmpty()) return rows;

        // skip header
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty()) continue;

            String[] values = line.split(",");
            CsvRow row = new CsvRow();
            row.values = new ArrayList<>(Arrays.asList(values));
            rows.add(row);
        }
        return rows;
    }
}
