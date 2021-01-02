package com.pandabunnytech.bhathiyapaxos.paxos.agent;

import com.pandabunnytech.bhathiyapaxos.message.Message;
import com.pandabunnytech.bhathiyapaxos.message.MessageType;
import com.pandabunnytech.bhathiyapaxos.message.SuggestedValue;
import com.pandabunnytech.bhathiyapaxos.util.Counter;

import java.util.LinkedHashMap;
import java.util.Map;

public class Learner extends Agent {

    boolean consensus = false;
    private final Map<Integer, SuggestedValue> accepted = new LinkedHashMap<>();

    @Override
    public void onMessage(Message message) {
        if (consensus) return;
        if (message.getType() == MessageType.ACCEPTED) {
            int from = message.getFrom();
            accepted.put(from, message.getValue());
        }

        int majority = process.getNodes().size() / 2 + 1;
        if (accepted.size() >= majority) {
            Counter<String> counter = new Counter<>();
            for (Map.Entry<Integer, SuggestedValue> val : accepted.entrySet()) {
                counter.count(val.getValue().getValue());
            }
            Counter.Res<String> res = counter.maxOccurrence();
            if (res.getCount() == accepted.size()) {
                consensus = true;
                System.out.printf("Others accepted my value [me=%d]: %s  %n", process.getNodeId(), res.getValue());
                process.setMyValueAccepted(true);
            }
        }
    }

    @Override
    public void work() {

    }
}
