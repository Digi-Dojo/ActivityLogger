package it.unibz.activitylogger.core.api;

public record Metadata(
        String action,
        String entityType,
        String entityKey,
        String contextType,
        String contextKey
) {
    public static Builder builder() {
        return new Builder();
    }

    public static Metadata empty() {
        return new Builder().build();
    }

    public static class Builder {
        private String action;
        private String entityType;
        private String entityKey;
        private String contextType;
        private String contextKey;

        private Builder() {
            action = null;
            entityType = null;
            entityKey = null;
            contextType = null;
            contextKey = null;
        }

        public Builder withAction(String action) {
            this.action = action;
            return this;
        }

        public Builder withEntityType(String type) {
            entityType = type;
            return this;
        }

        public Builder withEntityKey(String key) {
            entityKey = key;
            return this;
        }

        public Builder withEntity(String type, String key) {
            return withEntityType(type)
                    .withEntityKey(key);
        }

        public Builder withContextType(String type) {
            contextType = type;
            return this;
        }

        public Builder withContextKey(String key) {
            contextKey = key;
            return this;
        }

        public Builder withContext(String type, String key) {
            return withContextType(type)
                    .withContextKey(key);
        }

        public Metadata build() {
            return new Metadata(
                    action,
                    entityType,
                    entityKey,
                    contextType,
                    contextKey
            );
        }
    }
}


/*

{
    // raw data
    method: POST,
    uri: /organizations/789/memberships/applications
    body: {
        application: {
            user_id: 123,
            position: "Software Engineer",
            experience: "5+ years in web development",
            references: [
                { name: "John Doe", contact: "john.doe@email.com" },
                { name: "Jane Smith", contact: "jane.smith@email.com" }
            ]
        }
    },
    meta: {
        // terminal type (raw value)
        action: "membership applied",
        entityType: "user",

        // reference type (pointer to raw data)
        entityKey: "{body@application.user_id}",
        contextName: "{uri@0}",
        contextKey: "{uri@1}"

        //  -> /organizations/{orgId}/membership/applications
        //          0-^        1-^
    }
}


{
    method: PUT,
    uri: /projects/xyz/tasks/789/update,
    body: {
        task: {
            title: "Implement Feature X",
            description: "Add new functionality to improve user experience",
            priority: "High",
            deadline: "2023-12-01",
            assigned_to: [456, 789, 321]
        }
    },
    meta: {
        action: "task edited",
        entityType: "{uri@2},
        entityKey: "{uri@3},
        contextName: "{uri@0}",
        contextKey: "{uri@1}"
    }
}

{
    method: PATCH
    uri: /users/567/profile/settings
    body: {
        settings: {
            notifications: {
                email: true,
                push: false
            },
            theme: "dark",
            language: "en_US",
            preferences: {
                show_avatars: true,
                auto_play_videos: false
            }
        }
    },
    meta: {
        action: "personal settings updated",
        entityType: "profile settings",
        contextName: "{uri@0}",
        contextKey: "{uri@1}"
    }
}

{
    method: DELETE
    uri: /reviews/products/654/comments/987/report
    body: {
        report: {
            reason: "Inappropriate content",
            additional_details: "Contains offensive language and violates community guidelines."
        }
    },
    meta: {
        action: "comment reported",
        entityType: "{uri@3}",
        entityKey: "{uri@4}",
        contextName: "{uri@1}",
        contextKey: "{uri@2}"
    }
}

 */
