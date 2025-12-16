package rules;

import model.CsvRow;
import java.util.List;

public abstract class ValidationRule {
    protected ValidationRule next;

    public ValidationRule linkWith(ValidationRule next) {
        this.next = next;
        return next;
    }

    public final void check(CsvRow row, List<String> errors) {
        apply(row, errors);
        if (next != null) {
            next.check(row, errors);
        }
    }

    protected abstract void apply(CsvRow row, List<String> errors);
}
