package rules;

import model.CsvRow;
import java.util.List;

public class RegexRule extends ValidationRule {
    private final int index;
    private final String fieldName;
    private final String regex;

    public RegexRule(int index, String fieldName, String regex) {
        this.index = index;
        this.fieldName = fieldName;
        this.regex = regex;
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
        if (!v.matches(regex)) {
            errors.add(fieldName + "_regex_invalid");
        }
    }
}
