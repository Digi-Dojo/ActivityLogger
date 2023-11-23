#####################################
# BASE IMAGE FOR BUILDING THE PROJECT
#####################################
FROM gradle:8.4-jdk17 AS dependencies

WORKDIR /home/gradle/activitylogger

RUN mkdir async core http inferrers \
    && chown gradle:gradle . -R

USER gradle

CMD gradle build --continuous

# COPY ALL PROJECTS build.gradle.kts FILES TO THEIR DESTINATIONS
COPY --chown=gradle:gradle  settings.gradle.kts         build.gradle.kts            ./
COPY --chown=gradle:gradle  async/build.gradle.kts      async/gradle.properties     ./async/
COPY --chown=gradle:gradle  core/build.gradle.kts                                   ./core/
COPY --chown=gradle:gradle  http/build.gradle.kts       http/gradle.properties      ./http/
COPY --chown=gradle:gradle  inferrers/build.gradle.kts                              ./inferrers/


# PRE-INSTALL JUST THE DEPENDENCIES -- THIS SHALL SPEEDUP FUTURE BUILDS
RUN gradle clean build

# COPY THE REST OF THE CODE
COPY --chown=gradle:gradle . ./





###########################
# BUILDER IMAGE
###########################
FROM dependencies AS builder

# BUILD THE FAT JAR
RUN gradle build





############################
# RUNNER IMAGE
############################
FROM dependencies AS runner

ENV ACTIVITY_LOGGER_HTTP_PORT=8080

EXPOSE ${ACTIVITY_LOGGER_HTTP_PORT}

ENTRYPOINT ["gradle", "run"]