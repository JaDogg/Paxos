package com.pandabunnytech.bhathiyapaxos.message;

public class Message {
    protected final MessageType type;
    protected SuggestedValue value;
    protected int from;

    public Message(MessageType type) {
        this.type = type;
    }

    public MessageType getType() {
        return type;
    }

    public SuggestedValue getValue() {
        return value;
    }

    public void setValue(SuggestedValue value) {
        this.value = value;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }
}
