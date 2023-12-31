package it.unibz.activitylogger.http;

import io.javalin.Javalin;
import io.javalin.http.Context;
import it.unibz.activitylogger.core.api.Input;
import it.unibz.activitylogger.core.api.LogRecordSaver;
import it.unibz.activitylogger.core.api.Port;
import it.unibz.activitylogger.core.main.ActivityLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ActivityLoggerHttp implements Port {
    private final Logger logger = LoggerFactory.getLogger(ActivityLoggerHttp.class);
    private final ActivityLogger activityLogger = new ActivityLogger();

    private void logBody(Context ctx) {
        logger.info(ctx.body());
    }

    private void logResponse(Context ctx) {
        logger.info(ctx.result());
    }

    @Override
    public void run(Properties configs) {
        Javalin server = Javalin.create();

        server.before(this::logBody);
        server.after(this::logResponse);

        server.post("/input", ctx -> {
            try {
                Input input = ctx.bodyAsClass(Input.class);

                if (input.isValid())
                    throw new RuntimeException();

                activityLogger.processInput(input);
            } catch (Exception re) {
                ctx.result("{'error':'Invalid input structure'");
                ctx.status(400);
            }
        });

        int port = Integer.parseInt(configs.getProperty("activitylogger.http.port"));
        server.start(port);
    }

    @Override
    public void setLogRecordSaver(LogRecordSaver logRecordSaver) {
        this.activityLogger.setRecordSaver(logRecordSaver);
    }
}
