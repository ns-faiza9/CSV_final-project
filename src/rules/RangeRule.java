package rules;

import model.CsvRow;
import java.util.List;

public class RangeRule extends ValidationRule {
    private final int index;
    private final String fieldName;
    private final double min;
    private final double max;

    public RangeRule(int index, String fieldName, double min, double max) {
        this.index = index;
        this.fieldName = fieldName;
        this.min = min;
        this.max = max;
    }

    @Override
    protected void apply(CsvRow row, List<String> errors) {
        if (index >= row.values.size()) {
            errors.add(fieldName + "_missing_column");
            return;
        }
        String v = row.values.get(index).trim();
        if (v.isEmpty()) {
            return; // RequiredRule handles empties
        }
        try {
            double d = Double.parseDouble(v);
            if (d < min || d > max) {
                errors.add(fieldName + "_out_of_range");
            }
        } catch (NumberFormatException e) {
            errors.add(fieldName + "_type_error");
        }
    }
}
