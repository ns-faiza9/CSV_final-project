# CSV Validator and Normalizer

Java project that reads a CSV file, validates each row using rules, normalizes data, and writes cleaned output and error reports.

## How to run
1. Build the project in Eclipse.
2. Run the `App` class.
3. Input CSV and schema are in `sample-data/`; output files are written to the `output/` folder.

CSV_final-project/
└── src/
    ├── | main/
        |    └── App.java
    │   ├── model/
    │   │   └── CsvRow.java
    │   ├── io/
    │   │   ├── CsvReader.java
    │   │   └── CsvWriter.java
    │   ├── service/
    │   │   └── CsvValidator.java
    │   ├── rules/
    │   │   ├── ValidationRule.java
    │   │   ├── RequiredRule.java
    │   │   ├── RangeRule.java
    │   │   ├── RegexRule.java
    │   │   └── RuleFactory.java
    │   ├── normalizer/
    │   │   ├── Normalizer.java
    │   │   └── SimpleNormalizer.java
    │   └── exceptions/
    │       ├── SchemaMismatchException.java
    │       └── TypeConversionException.java
    └── sample-data/
        ├── raw.csv
        └── schema.json
