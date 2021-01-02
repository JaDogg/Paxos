package com.pandabunnytech.bhathiyapaxos.paxos;

import com.pandabunnytech.bhathiyapaxos.message.Message;
import com.pandabunnytech.bhathiyapaxos.paxos.agent.Acceptor;
import com.pandabunnytech.bhathiyapaxos.paxos.agent.Agent;
import com.pandabunnytech.bhathiyapaxos.paxos.agent.Learner;
import com.pandabunnytech.bhathiyapaxos.paxos.agent.Proposer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PaxosProcess extends Node {
    private final Agent proposer;
    private final Agent acceptor;
    private final Agent learner;
    private final List<Node> nodes;
    private Map<Integer, Node> nodeMap;
    private boolean myValueAccepted = false;

    public PaxosProcess(int id, List<Node> nodes) {
        this(id, nodes, new Proposer(), new Acceptor(), new Learner());
    }

    public boolean isMyValueAccepted() {
        return myValueAccepted;
    }

    public void setMyValueAccepted(boolean myValueAccepted) {
        this.myValueAccepted = myValueAccepted;
    }

    public PaxosProcess(int id, List<Node> nodes, Agent proposer, Agent acceptor, Agent learner) {
        super(id);
        this.proposer = proposer;
        this.acceptor = acceptor;
        this.learner = learner;
        this.nodes = nodes;
        this.nodes.add(this); // add my self

        proposer.setProcess(this);
        acceptor.setProcess(this);
        learner.setProcess(this);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public Node getNode(int id) {
        if (nodeMap == null) {
            nodeMap = nodes.stream().collect(Collectors.toMap(Node::getNodeId, x -> x));
        }
        return nodeMap.get(id);
    }

    @Override
    public void onMessage(Message message) {
        proposer.onMessage(message);
        acceptor.onMessage(message);
        learner.onMessage(message);
    }

    @Override
    public void work() {
        proposer.work();
        acceptor.work();
        learner.work();
    }


}