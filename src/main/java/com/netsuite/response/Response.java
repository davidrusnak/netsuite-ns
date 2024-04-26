package com.netsuite.response;

import java.util.Objects;

public final class Response {
    private final String sessionId;
    private final String content;

    public Response(String sessionId, String content) {
        this.sessionId = sessionId;
        this.content = content;
    }

    public String sessionId() {
        return sessionId;
    }

    public String content() {
        return content;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Response) obj;
        return Objects.equals(this.sessionId, that.sessionId) &&
                Objects.equals(this.content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, content);
    }

    @Override
    public String toString() {
        return "Response[" +
                "sessionId=" + sessionId + ", " +
                "content=" + content + ']';
    }

}
