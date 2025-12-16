package service;

import exceptions.SchemaMismatchException;
import io.CsvReader;
import io.CsvWriter;
import model.CsvRow;
import normalizer.Normalizer;
import normalizer.SimpleNormalizer;
import rules.*;

import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;

public class CsvValidator {

    public static void validate(String csvPath, String schemaPath) throws Exception {

        // Load schema
        String schemaJson = Files.readString(Paths.get(schemaPath));
        if (schemaJson == null || schemaJson.isBlank()) {
            throw new SchemaMismatchException("schema.json is missing or empty");
        }

        List<String> header = Arrays.asList("name", "age", "email", "salary");

        // Read all rows
        List<CsvRow> allRows = CsvReader.read(csvPath);

        if (allRows.size() < 4) {
            throw new Exception("CSV must contain at least 4 data rows");
        }

        // Setup normalizer + validation chain
        Normalizer normalizer = new SimpleNormalizer();

        ValidationRule chain =
                new RequiredRule(0, "name");
        chain.linkWith(new RequiredRule(1, "age"))
             .linkWith(new RequiredRule(2, "email"))
             .linkWith(new RequiredRule(3, "salary"))
             .linkWith(new RangeRule(1, "age", 0, 120))
             .linkWith(new RangeRule(3, "salary", 0, Double.MAX_VALUE))
             .linkWith(new RegexRule(2, "email",
                     "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.(com|in)$"));

        List<CsvRow> cleanRows = new ArrayList<>();
        List<CsvRow> rejectRows = new ArrayList<>();
        List<String> rejectReasons = new ArrayList<>();
        Map<String, Integer> errorCounts = new HashMap<>();

        // Process each row
        for (CsvRow row : allRows) {
            normalizer.normalize(row);

            List<String> errors = new ArrayList<>();
            chain.check(row, errors);

            if (row.values.size() != header.size()) {
                errors.add("wrong_column_count");
            }

            if (errors.isEmpty()) {
                cleanRows.add(row);
            } else {
                rejectRows.add(row);
                String reason = String.join(";", errors);
                rejectReasons.add(reason);
                for (String e : errors) {
                    errorCounts.put(e, errorCounts.getOrDefault(e, 0) + 1);
                }
            }
        }

        // Write outputs
        CsvWriter.writeClean("output/clean.csv", cleanRows, header);
        CsvWriter.writeRejects("output/rejects.csv", rejectRows, rejectReasons, header);

        // Stats
        int totalRows = allRows.size();
        int validRows = cleanRows.size();
        int rejectRowsCount = rejectRows.size();
        double validPercent = totalRows > 0 ? (double) validRows / totalRows * 100 : 0;

        // Write stats.csv
        List<String> statsLines = new ArrayList<>();
        statsLines.add("total_rows,valid_rows,reject_rows,valid_percent");
        statsLines.add(String.format("%d,%d,%d,%.1f", totalRows, validRows, rejectRowsCount, validPercent));
        statsLines.add("error_type,count");
        for (Map.Entry<String, Integer> entry : errorCounts.entrySet()) {
            statsLines.add(entry.getKey() + "," + entry.getValue());
        }

        Path statsPath = Paths.get("output/stats.csv");
        if (statsPath.getParent() != null) Files.createDirectories(statsPath.getParent());
        Files.write(statsPath, statsLines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        // Write audit.txt
        List<String> auditLines = new ArrayList<>();
        auditLines.add("=== CSV VALIDATION AUDIT ===");
        auditLines.add("Timestamp: " + LocalDateTime.now());
        auditLines.add("File: " + csvPath);
        auditLines.add("Schema: " + schemaPath);
        auditLines.add("Total: " + totalRows + " | Valid: " + validRows + " | Rejected: " + rejectRowsCount);
        auditLines.add("Validation Rate: " + String.format("%.1f%%", validPercent));
        auditLines.add("\n=== ERRORS BY TYPE ===");
        for (Map.Entry<String, Integer> e : errorCounts.entrySet()) {
            auditLines.add(e.getKey() + ": " + e.getValue());
        }

        Path auditPath = Paths.get("output/audit.txt");
        Files.write(auditPath, auditLines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        System.out.printf("Valid: %d/%d (%.1f%%)%n", validRows, totalRows, validPercent);
    }
}
