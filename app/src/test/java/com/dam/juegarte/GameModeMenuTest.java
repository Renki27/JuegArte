package com.dam.juegarte;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)

public class GameModeMenuTest {

    private final String TRIVIA = "TRIVIA";
    private final String SCRATCH = "SCRATCH";
    private final String TOF = "TRUEORFALSE";
    private final String ERROR = "ERROR";

    @Mock
    Context mMockContext;

    @Test
    public void gameselectedtrivia() {
        GameModeMenu myObjectUnderTest = new GameModeMenu(mMockContext);

        // Correct selection of game
        String result = myObjectUnderTest.selectGameMode("trivia");

        // ...then the result should be the expected one.
        assertThat(result, is(TRIVIA));
    }

    @Test
    public void gameselectedscratch() {
        GameModeMenu myObjectUnderTest = new GameModeMenu(mMockContext);

        // Correct selection of game
        String result = myObjectUnderTest.selectGameMode("scratch");

        // ...then the result should be the expected one.
        assertThat(result, is(SCRATCH));
    }

    @Test
    public void gameselectedtof() {
        GameModeMenu myObjectUnderTest = new GameModeMenu(mMockContext);

        // Correct selection of game
        String result = myObjectUnderTest.selectGameMode("tof");

        // ...then the result should be the expected one.
        assertThat(result, is(TOF));
    }

    @Test
    public void gameselectederror() {
        GameModeMenu myObjectUnderTest = new GameModeMenu(mMockContext);

        // Correct selection of game
        String result = myObjectUnderTest.selectGameMode("");

        // ...then the result should be the expected one.
        assertThat(result, is(ERROR));
    }

}