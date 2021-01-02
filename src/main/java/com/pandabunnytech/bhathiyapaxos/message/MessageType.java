package com.pandabunnytech.bhathiyapaxos.message;

public enum MessageType {
    PERMISSION_REQUEST(1),
    PERMISSION_GRANTED(2),
    SUGGESTION(3),
    ACCEPTED(4),
    NEGATIVE_ACK(5),
    HEARTBEAT(6);

    private final int id;


    MessageType(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
