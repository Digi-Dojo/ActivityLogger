package it.unibz.activitylogger.http;

import io.javalin.Javalin;
import io.javalin.http.Context;
import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.main.ActivityLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActivityLoggerHttp {
    private static final Logger logger =
            LoggerFactory.getLogger(ActivityLoggerHttp.class);

    private static void log(Context ctx) {
        ActivityLoggerHttp.logger.info(ctx.body());
    }

    public static void main(String[] args) {

        Javalin server = Javalin.create();

        server.before(ActivityLoggerHttp::log);

        server.post("/input", ctx -> {
            Input input = ctx.bodyAsClass(Input.class);

            logger.info(String.valueOf(input.body()));

            ActivityLogger.run(input);
        });

        server.start(8080);
    }
}
