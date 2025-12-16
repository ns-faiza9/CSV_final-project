package main;

import service.CsvValidator;

public class App {
    public static void main(String[] args) {
        try {
            CsvValidator.validate("sample-data/raw.csv", "sample-data/schema.json");
            System.out.println("Done. Check output folder.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
