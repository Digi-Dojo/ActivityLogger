package it.unibz.activitylogger.http;

import io.javalin.Javalin;
import io.javalin.http.Context;
import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.api.Port;
import it.unibz.activitylogger.core.main.ActivityLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActivityLoggerHttp implements Port {
    private final Logger logger = LoggerFactory.getLogger(ActivityLoggerHttp.class);

    private void logBody(Context ctx) {
        logger.info(ctx.body());
    }

    private void logResponse(Context ctx) {
        logger.info(ctx.result());
    }

    @Override
    public void run() {
        Javalin server = Javalin.create();

        server.before(this::logBody);
        server.after(this::logResponse);

        server.post("/input", ctx -> {
            Input input = ctx.bodyAsClass(Input.class);

            ActivityLogger.process(input);
        });

        server.start(8080);
    }
}
