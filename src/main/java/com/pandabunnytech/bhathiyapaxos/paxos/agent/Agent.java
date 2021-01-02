package com.pandabunnytech.bhathiyapaxos.paxos.agent;

import com.pandabunnytech.bhathiyapaxos.message.Message;
import com.pandabunnytech.bhathiyapaxos.paxos.Node;
import com.pandabunnytech.bhathiyapaxos.paxos.PaxosProcess;


/**
 * com.pandabunnytech.bhathiyapaxos.paxos.agent.Agent is an aspect of a process.
 * Usually process will act as agents -> Proposer / Acceptor / Leaner
 */
public abstract class Agent {
    protected PaxosProcess process;

    public void setProcess(PaxosProcess process) {
        this.process = process;
    }

    public abstract void onMessage(final Message message);

    public abstract void work();
}
