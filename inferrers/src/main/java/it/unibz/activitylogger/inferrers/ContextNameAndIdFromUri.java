package it.unibz.activitylogger.inferrers;

import it.unibz.activitylogger.core.api.EditableLogRecord;
import it.unibz.activitylogger.core.api.InferenceContext;
import it.unibz.activitylogger.core.api.InferenceTier;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@InferenceTier(InferenceTier.Tier.MEDIUM)
public class ContextNameAndIdFromUri extends BaseInferrer {
    @Override
    public void infer(InferenceContext context) {
        String uri = context.readInput().uri();

        Pattern regex = Pattern.compile("^/([a-zA-Z]\\w*)/(\\d+)/.*$");

        Matcher matcher = regex.matcher(uri);
        boolean matches = matcher.find();

        if (!matches) {
            safeCallNext(context);
            return;
        }

        String ctxName = matcher.group(1);
        String ctxId = matcher.group(2);

        EditableLogRecord logRecord = context.getLogRecord();
        logRecord.setContextName(ctxName);
        logRecord.setContextId(ctxId);

        safeCallNext(context);
    }
}
