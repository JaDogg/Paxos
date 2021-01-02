package com.pandabunnytech.bhathiyapaxos.message;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuggestionIDTest {
    @Test
    public void suggestionIDLess() {
        {
            SuggestionID a = new SuggestionID(1, 1);
            SuggestionID b = new SuggestionID(2, 1);

            assertTrue(SuggestionID.less(a, b));
        }

        {
            SuggestionID a = new SuggestionID(1, 1);
            SuggestionID b = new SuggestionID(1, 2);

            assertTrue(SuggestionID.less(a, b));
        }
    }

    @Test
    public void suggestionIDGreat() {
        {
            SuggestionID a = new SuggestionID(2, 1);
            SuggestionID b = new SuggestionID(1, 1);

            assertTrue(SuggestionID.great(a, b));
        }

        {
            SuggestionID a = new SuggestionID(1, 2);
            SuggestionID b = new SuggestionID(1, 1);

            assertTrue(SuggestionID.great(a, b));
        }
    }
}