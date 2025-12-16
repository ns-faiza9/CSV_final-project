package normalizer;

import model.CsvRow;

public interface Normalizer {
    void normalize(CsvRow row);
}
