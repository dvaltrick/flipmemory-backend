package com.dvaltrick.flipmemory.enums;

/**
 * Answear
 */
public enum ResultAnswer {
    CORRECT(1),
    INCOMPLETE(2),
    FAIL(9);

    public int answer;

    ResultAnswer(int result) {
        answer = result;
    }
    
}