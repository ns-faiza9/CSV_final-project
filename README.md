# 📊 CSV Validator & Normalizer

A command-line Java application that validates, cleans, and standardises CSV data using a configurable schema.

---

## 📌 Abstract

The CSV Validator & Normalizer is a Java-based tool designed to automatically check and clean CSV files. It uses a configurable schema (JSON file) to define column names, data types, and validation rules such as **required fields** or **non-empty constraints**.

The application:
- Reads raw CSV data  
- Validates each row against defined rules  
- Normalises data (trims spaces, standardises formats)  

✅ Valid rows are saved to `clean.csv`  
❌ Invalid rows are stored in `rejects.csv` with clear error reasons  

---

## ❗ Problem Statement

Many CSV files suffer from common issues:

- Missing values in important columns  
- Incorrect data types  
- Inconsistent formatting (extra spaces, mixed styles)  

These problems make data:
- Unreliable  
- Slow to process  
- Prone to errors in reports and applications  

---

## 🎯 Objectives

- Clean and normalise field values (trim spaces, fix basic formats)  
- Automatically validate CSV rows using simple rules  
- Separate:
  - Valid data → `clean.csv`  
  - Invalid data → `rejects.csv`  
- Record detailed reasons for rejected rows  
- Provide basic statistics:
  - Total rows  
  - Valid rows  
  - Invalid rows  

---

## ⚙️ How It Works

```
Input:
  CSV file + JSON schema

Processing:
  → Validation (rules check)
  → Normalisation (cleaning)

Output:
  → clean.csv (valid data)
  → rejects.csv (invalid data + errors)
```

---

## 🛠️ How to Run

1. Open the project in Eclipse  
2. Build the project  
3. Run the `App` class  
4. Input files are in `sample-data/`  
5. Output files will be generated in `output/`  

---

## 📁 Project Structure

```
├── sample-data/     # Input CSV and schema files
├── output/          # Generated clean and reject files
├── src/             # Java source code
└── README.md
```

---

## 💡 Key Features

- Schema-driven validation (flexible and customizable)  
- Automatic data cleaning  
- Clear error reporting  
- Simple CLI-based workflow  

---

## 🚀 Future Improvements

- Add a GUI for easier interaction  
- Support advanced validation rules  
- Handle large datasets more efficiently  
- Export reports in additional formats  
