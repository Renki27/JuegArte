package com.dam.juegarte;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)

public class RegisterActivityTest {


    private static final String FAKE_STRING = "Account already created";
    private static final String FAKE_STRINGTWO = "Account created";

    @Mock
    Context mMockContext;

    @Test
    public void registerNewDataEntered() {
        RegisterActivity myObjectUnderTest = new RegisterActivity(mMockContext);

        // Register Correct
        String result = myObjectUnderTest.validateDataEntered("user", "user", "password", "password");

        // ...then the result should be the expected one.
        assertThat(result, is(FAKE_STRINGTWO));
    }

    @Test
    public void registerAlreadyExist() {
        RegisterActivity myObjectUnderTest = new RegisterActivity(mMockContext);

        //Register already did it
        String result = myObjectUnderTest.validate("email", "user");

        // ...then the result should be the expected one.
        assertThat(result, is(FAKE_STRING));
    }

}