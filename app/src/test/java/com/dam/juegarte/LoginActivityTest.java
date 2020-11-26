package com.dam.juegarte;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)

public class LoginActivityTest {

    private static final String FAKE_STRING = "Login was successful";

    @Mock
    Context mMockContext;

    @Test
    public void readStringFromContext_LocalizedString() {
        Sign_in myObjectUnderTest = new Sign_in(mMockContext);

        // Login Correct
//        String result = myObjectUnderTest.validate("user", "user");

        //Login Incorrect
        String result = myObjectUnderTest.validate("user", "password");

        // ...then the result should be the expected one.
        assertThat(result, is(FAKE_STRING));
    }

}