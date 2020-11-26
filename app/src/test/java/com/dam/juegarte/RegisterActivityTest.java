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

    private static final String FAKE_STRING = "Register was successful";

    @Mock
    Context mMockContext;

    @Test
    public void readStringFromContext_LocalizedString() {
        Sign_up myObjectUnderTest = new Sign_up(mMockContext);

        // Login Correct
//        String result = myObjectUnderTest.validate("user", "user");

        //Login Incorrect
        String result = myObjectUnderTest.validate("email", "user", "password", "password" );

        // ...then the result should be the expected one.
        assertThat(result, is(FAKE_STRING));
    }

}