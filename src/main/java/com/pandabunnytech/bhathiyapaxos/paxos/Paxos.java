package com.pandabunnytech.bhathiyapaxos.paxos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Paxos {
    public static void main(String[] args) throws InterruptedException {
        List<Node> nodes = new ArrayList<>();
        PaxosProcess process1 = new PaxosProcess(1, nodes);
        PaxosProcess process2 = new PaxosProcess(2, nodes);
        PaxosProcess process3 = new PaxosProcess(3, nodes);
        PaxosProcess process4 = new PaxosProcess(4, nodes);
        PaxosProcess process5 = new PaxosProcess(5, nodes);
        PaxosProcess process6 = new PaxosProcess(6, nodes);
        PaxosProcess process7 = new PaxosProcess(7, nodes);

        List<Node> copy = new ArrayList<>(nodes);

        while (true) {
            Collections.shuffle(copy);
            copy.forEach(Node::work);
        }
    }
}
