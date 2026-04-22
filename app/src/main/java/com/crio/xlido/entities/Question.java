package com.crio.xlido.entities;

import java.util.*;

public class Question {
    public static void resetCounter() {
        counter = 1;
    }

    private static long counter = 1;   // ID generator

    private final long id;
    private final String content;
    private final long userId;
    private final long eventId;
    private int votes = 0;

    private final List<Reply> replies = new ArrayList<>();
    private final Set<Long> upvotedUsers = new HashSet<>();

    public Question(String content, long userId, long eventId) {
        this.id = counter++;           // 
        this.content = content;
        this.userId = userId;
        this.eventId = eventId;
    }

    public long getId() { return id; }
    public String getContent() { return content; }
    public long getUserId() { return userId; }
    public long getEventId() { return eventId; }
    public int getVotes() { return votes; }

    public void upvote(long userId) {
        if (upvotedUsers.contains(userId)) {
            throw new RuntimeException(
                "ERROR: User with an id " + userId +
                " has already upvoted a question with an id " + id
            );
        }
        upvotedUsers.add(userId);
        votes++;
    }

    public void addReply(long userId, String text) {
        replies.add(new Reply(userId, text));
    }

    public List<Reply> getReplies() {
        return replies;
    }

    @Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Question ID: ").append(id).append("\n");
    sb.append("Content: ").append(content).append("\n");
    sb.append("Votes: ").append(votes).append("\n");
    sb.append("Replies:\n");

    for (Reply r : replies) {
        sb.append("  - User ")
          .append(r.getUserId())
          .append(": ")
          .append(r.getText())
          .append("\n");
    }

    return sb.toString().trim();
}

}
