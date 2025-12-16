package rules;

import model.CsvRow;
import java.util.List;

public class RequiredRule extends ValidationRule {
    private final int index;
    private final String fieldName;

    public RequiredRule(int index, String fieldName) {
        this.index = index;
        this.fieldName = fieldName;
    }

    @Override
    protected void apply(CsvRow row, List<String> errors) {
        if (index >= row.values.size()) {
            errors.add(fieldName + "_missing_column");
            return;
        }
        String v = row.values.get(index).trim();
        if (v.isEmpty()) {
            errors.add(fieldName + "_required");
        }
    }
}
