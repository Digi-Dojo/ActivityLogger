package it.unibz.activitylogger.inferrers;

import it.unibz.activitylogger.core.api.EditableLogRecord;
import it.unibz.activitylogger.core.api.InferenceContext;
import it.unibz.activitylogger.core.api.InferenceTier;
import it.unibz.activitylogger.core.api.Input;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@InferenceTier(InferenceTier.Tier.MEDIUM)
public class ActionInUri extends BaseInferrer {
    @Override
    public void infer(InferenceContext context) {
        Input input = context.readInput();
        EditableLogRecord logRecord = context.getLogRecord();

        String lastTerm = getPathLastTerm(input);

        if (!relatedToBody(lastTerm, input.body())) {
            logRecord.setAction(lastTerm);
        }

        safeCallNext(context);
    }

    private boolean relatedToBody(String lastTerm, Object body) {
        boolean isNumber = lastTerm.matches("^\\d+$");
        if (isNumber)
            return false;

        boolean isMap = body instanceof Map<?, ?>;
        if (!isMap)
            return false;

        Map<?, ?> mapBody = (Map<?, ?>) body;

        boolean termIsSubstring = false;

        for (Object key: mapBody.keySet()) {
            if (!(key instanceof String)) {
                continue;
            }

            boolean currentIsSubstring = lastTerm.contains((String) key);
            termIsSubstring = termIsSubstring || currentIsSubstring;
        }

        return termIsSubstring;
    }

    private String getPathLastTerm(Input input) {
        String uriPath = input.uri();
        List<String> terms = Arrays.stream(uriPath.split("/")).toList();
        int lastIndex = terms.size() - 1;
        return terms.get(lastIndex);
    }
}
