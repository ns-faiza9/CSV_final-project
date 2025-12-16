package normalizer;

import model.CsvRow;

public class SimpleNormalizer implements Normalizer {

    @Override
    public void normalize(CsvRow row) {
        // Trim spaces
        for (int i = 0; i < row.values.size(); i++) {
            row.values.set(i, row.values.get(i).trim());
        }
        // Lowercase email at index 2, if present
        if (row.values.size() > 2) {
            row.values.set(2, row.values.get(2).toLowerCase());
        }
    }
}
