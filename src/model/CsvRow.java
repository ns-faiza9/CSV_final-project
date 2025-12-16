package model;

import java.util.List;

public class CsvRow {
    public List<String> values;

    @Override
    public String toString() {
        return String.join(", ", values);
    }
}
