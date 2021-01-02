package com.pandabunnytech.bhathiyapaxos.message;

import java.util.Comparator;
import java.util.Objects;

public class SuggestionID {
    private final int nodeId;
    private final int incrementalId;

    // Compare with incremental id and then compare with node id.
    public static final Comparator<SuggestionID> COMPARATOR =
            Comparator.comparingInt(SuggestionID::getIncrementalId)
                    .thenComparingInt(SuggestionID::getNodeId);

    public SuggestionID(int nodeId, int incrementalId) {
        this.nodeId = nodeId;
        this.incrementalId = incrementalId;
    }

    public int getNodeId() {
        return nodeId;
    }

    public int getIncrementalId() {
        return incrementalId;
    }

    public static boolean less(SuggestionID a, SuggestionID b) {
        if (a.incrementalId < b.incrementalId) return true;
        if (a.incrementalId > b.incrementalId) return false;
        return a.nodeId < b.nodeId;
    }

    public static boolean great(SuggestionID a, SuggestionID b) {
        if (a.incrementalId > b.incrementalId) return true;
        if (a.incrementalId < b.incrementalId) return false;
        return a.nodeId > b.nodeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuggestionID that = (SuggestionID) o;
        return nodeId == that.nodeId && incrementalId == that.incrementalId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeId, incrementalId);
    }

    @Override
    public String toString() {
        return "(" + nodeId + ", " + incrementalId + ")";
    }
}
