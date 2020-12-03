package com.dam.juegarte;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)

public class ScratchGameTest {

    private final String A = "A";
    private final String B = "B";
    private final String C = "C";
    private final String D = "D";
    private final String ERROR = "ERROR";

    @Mock
    Context mMockContext;

    @Test
    public void answerSelectedA() {

        ScratchGame myObjectUnderTest = new ScratchGame(mMockContext);

        // Correct selection of game
        String result = myObjectUnderTest.questionBase("A");

        // ...then the result should be the expected one.
        assertThat(result, is(A));
    }

    @Test
    public void answerSelectedB() {

        ScratchGame myObjectUnderTest = new ScratchGame(mMockContext);

        // Correct selection of game
        String result = myObjectUnderTest.questionBase("B");

        // ...then the result should be the expected one.
        assertThat(result, is(B));
    }

    @Test
    public void answerSelectedC() {

        ScratchGame myObjectUnderTest = new ScratchGame(mMockContext);

        // Correct selection of game
        String result = myObjectUnderTest.questionBase("C");

        // ...then the result should be the expected one.
        assertThat(result, is(C));
    }

    @Test
    public void answerSelectedD() {

        ScratchGame myObjectUnderTest = new ScratchGame(mMockContext);

        // Correct selection of game
        String result = myObjectUnderTest.questionBase("D");

        // ...then the result should be the expected one.
        assertThat(result, is(D));
    }
        
}