package com.pandabunnytech.bhathiyapaxos.message;

public class SuggestedValue {
    private SuggestionID id;
    private String value;

    public SuggestedValue(SuggestionID id, String value) {
        this.id = id;
        this.value = value;
    }

    public SuggestionID getId() {
        return id;
    }

    public void setId(SuggestionID id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SuggestedValue{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
