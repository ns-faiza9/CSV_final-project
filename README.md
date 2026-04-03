# CSV Validator and Normalizer

Java project that reads a CSV file, validates each row using rules, normalizes data, and writes cleaned output and error reports.

## How to run
1. Build the project in Eclipse.
2. Run the `App` class.
3. Input CSV and schema are in `sample-data/`; output files are written to the `output/` folder.

#Problem Statement

Many CSV files contain problems such as:

Missing values in important columns.
Wrong data types.
Inconsistent formatting, like extra spaces or mixed styles.

When these problems exist, it becomes hard to trust the data, slow to use it, and easy to make mistakes in reports or applications that depend on this CSV data.
