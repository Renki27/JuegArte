package com.dam.juegarte;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)

public class LoginActivityTest {

    private static final String FAKE_STRING = "Login was successful";
    private static final String VOID_DATA = "Void data";

    @Mock
    Context mMockContext;

    @Test
    public void readStrings_LoginActivity() {

        LoginActivity loginTesting = new LoginActivity(mMockContext);

//         Login Correct
        String result = loginTesting.validate("user", "user");

//         Login Incorrect
//        String result = loginTesting.validate("user", "password");

//        No data written
//        String result = loginTesting.validate("", "");

        // ...then the result should be the expected one.
        assertThat(result, is(FAKE_STRING));
//        assertThat(result, is(VOID_DATA));
    }

}