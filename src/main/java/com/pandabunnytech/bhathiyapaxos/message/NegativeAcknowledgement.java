package com.pandabunnytech.bhathiyapaxos.message;

public class NegativeAcknowledgement extends Message {
    public NegativeAcknowledgement() {
        super(MessageType.NEGATIVE_ACK);
    }
}
