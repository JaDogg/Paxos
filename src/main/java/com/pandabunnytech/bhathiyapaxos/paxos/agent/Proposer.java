package com.pandabunnytech.bhathiyapaxos.paxos.agent;

import com.pandabunnytech.bhathiyapaxos.message.*;
import com.pandabunnytech.bhathiyapaxos.util.Counter;

import java.util.LinkedHashMap;
import java.util.Map;

public class Proposer extends Agent {
    private boolean init = false;
    private int incrementalId;
    private final Map<Integer, SuggestedValue> suggestions = new LinkedHashMap<>();

    @Override
    public void onMessage(Message message) {
        if (message.getType() == MessageType.PERMISSION_GRANTED) {
            suggestions.put(message.getFrom(), message.getValue());
        } else {
            return;
        }
        // when we have more than maximum amount
        int majority = process.getNodes().size() / 2 + 1;
        int highestIncrementalId = -1;
        if (suggestions.size() >= majority) {
            Counter<String> counter = new Counter<>();
            for(Map.Entry<Integer, SuggestedValue> val: suggestions.entrySet()) {
                counter.count(val.getValue().getValue());
                if (highestIncrementalId < val.getValue().getId().getIncrementalId()) {
                    highestIncrementalId = val.getValue().getId().getIncrementalId();
                }
            }
            Counter.Res<String> res = counter.maxOccurrence();
            considerMajorityValue(res.getValue(), highestIncrementalId);
        }
    }

    private void considerMajorityValue(String value, int highestSuggestionId) {
        String valueToSend;
        if (value == null) {
            // Now we can suggest an arbitrary value
            valueToSend = "val=" + process.getNodeId();
        } else {
            // We need to suggest what we got here.
            valueToSend = value;
        }
        SuggestionID id = new SuggestionID(process.getNodeId(), highestSuggestionId + 1);
        Suggestion suggestion = new Suggestion();
        suggestion.setFrom(process.getNodeId());
        suggestion.setValue(new SuggestedValue(id, valueToSend));
        this.process.getNodes().forEach(node -> node.send(suggestion));
    }

    @Override
    public void work() {
        if (!init) {
            init = true;
            incrementalId = 1;
        }
        // We can optionally stop or pause after everyone accept my value
        if (!process.isMyValueAccepted()) {
            // send message to all nodes
            int me = process.getNodeId();
            SuggestionID id = new SuggestionID(me, incrementalId);
            PermissionRequest permissionRequest = new PermissionRequest();
            permissionRequest.setFrom(me);
            permissionRequest.setValue(new SuggestedValue(id, null));
            System.out.println(permissionRequest);
            this.process.getNodes().forEach(node -> node.send(permissionRequest));
            incrementalId++;
        }
    }
}
