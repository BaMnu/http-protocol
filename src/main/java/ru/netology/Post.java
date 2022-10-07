package ru.netology;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Post {

    private final String text;
    private final String id;
    private final String type;
    private final String user;
    private final String upvotes;
    private static int countOutput = 1;

    public Post(@JsonProperty("id") String id, @JsonProperty("text") String text,
            @JsonProperty("type") String type, @JsonProperty("user") String user,
                @JsonProperty("upvotes") String upvotes) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
    }

    public String getUpvotes() {
        return upvotes;
    }

    @Override
    public String toString() {
        return "Post %d\nid: %s\ntext: %s\ntype: %s\nuser: %s\nupvotes: %s\n"
                .formatted(countOutput++, id, text, type, user, upvotes);
    }
}
