package com.pandabunnytech.bhathiyapaxos.paxos.agent;

import com.pandabunnytech.bhathiyapaxos.message.*;

import static com.pandabunnytech.bhathiyapaxos.message.SuggestionID.great;

public class Acceptor extends Agent {
    private SuggestionID accepted = null;
    private SuggestionID permissionGranted = null;
    private String value = null;

    @Override
    public void onMessage(Message message) {
        int me = process.getNodeId();
        if (message.getType() == MessageType.PERMISSION_REQUEST) {
            if (permissionGranted == null || great(message.getValue().getId(), permissionGranted)) {
                permissionGranted = message.getValue().getId();
                PermissionGranted granted = new PermissionGranted();
                granted.setFrom(me);
                // if we don't have set a value then others can send us a one
                granted.setValue(new SuggestedValue(permissionGranted, value));
                process.getNode(message.getFrom()).send(granted);
            }
        }
        if (message.getType() == MessageType.SUGGESTION) {
            if (accepted == null || great(message.getValue().getId(), accepted)) {
                accepted = message.getValue().getId();
                value = message.getValue().getValue(); // get suggested value
                Accepted acceptedMsg = new Accepted();
                acceptedMsg.setFrom(me);
                acceptedMsg.setValue(new SuggestedValue(accepted, value));
                process.getNode(message.getFrom()).send(acceptedMsg);
            }
        }
    }

    @Override
    public void work() {
    }
}
