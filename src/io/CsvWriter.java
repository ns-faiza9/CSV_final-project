package io;

import model.CsvRow;
import java.nio.file.*;
import java.util.*;

public class CsvWriter {

    public static void writeClean(String path, List<CsvRow> rows, List<String> header) throws Exception {
        Path p = Paths.get(path);
        if (p.getParent() != null) Files.createDirectories(p.getParent());

        List<String> lines = new ArrayList<>();
        lines.add(String.join(",", header));
        for (CsvRow row : rows) {
            lines.add(String.join(",", row.values));
        }
        Files.write(p, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static void writeRejects(String path, List<CsvRow> rows, List<String> reasons, List<String> header) throws Exception {
        Path p = Paths.get(path);
        if (p.getParent() != null) Files.createDirectories(p.getParent());

        List<String> lines = new ArrayList<>();
        List<String> rejectHeader = new ArrayList<>(header);
        rejectHeader.add("reason");
        lines.add(String.join(",", rejectHeader));

        for (int i = 0; i < rows.size(); i++) {
            CsvRow row = rows.get(i);
            String reason = reasons.get(i);
            lines.add(String.join(",", row.values) + "," + reason);
        }
        Files.write(p, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
