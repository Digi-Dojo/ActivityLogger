package it.unibz.activitylogger;

import java.util.ArrayList;
import java.util.List;

public class EventProcessor {
    public List<LogRecord> process(Event event) {
        List<LogRecord> records = new ArrayList<>();

        event.header().keys().forEach(key -> {
            Object fieldValue = event.body().get(key);

            LogRecord logRecord = new LogRecord(
                    event.header().classType(),
                    event.header().typeId(),
                    event.header().action(),
                    key,
                    fieldValue,
                    event.header().timestamp()
            );

            records.add(logRecord);
        });

        return records;
    }
}
