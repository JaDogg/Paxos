package com.pandabunnytech.bhathiyapaxos.paxos;

import com.pandabunnytech.bhathiyapaxos.message.Message;

import java.util.Objects;

public abstract class Node {
    private final int nodeId;

    public Node(int nodeId) {
        this.nodeId = nodeId;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void send(final Message message) {
        onMessage(message);
    }

    public abstract void onMessage(final Message message);

    public abstract void work();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return nodeId == node.nodeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeId);
    }
}
