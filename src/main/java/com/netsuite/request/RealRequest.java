package com.netsuite.request;

public final class RealRequest implements Request {
    private final String sessionId;
    private final String email;

    public RealRequest(String sessionId, String email) {
        this.sessionId = sessionId;
        this.email = email;
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

    @Override
    public String getEmail() {
        return email;
    }
}
