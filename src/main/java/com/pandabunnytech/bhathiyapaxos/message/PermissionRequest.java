package com.pandabunnytech.bhathiyapaxos.message;

public class PermissionRequest extends Message {
    public PermissionRequest() {
        super(MessageType.PERMISSION_REQUEST);
    }

    @Override
    public String toString() {
        return String.format("PermissionRequest[%d]:%s", this.from, this.value);
    }
}
