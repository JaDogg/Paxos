package com.pandabunnytech.bhathiyapaxos.message;

public class PermissionGranted extends Message {
    public PermissionGranted() {
        super(MessageType.PERMISSION_GRANTED);
    }
}
