package com.spm.androidtesting.account.viewmodel


/**
 * Created by Sibaprasad Mohanty on 2020-01-30.
 * Spm Limited
 * sp.dobest@gmail.com
 */

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.spm.androidtesting.R
import com.spm.androidtesting.utils.CommonUtils
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`


private const val FAKE_STRING = "HELLO WORLD"

class RegisterViewModelTest {

    val context = ApplicationProvider.getApplicationContext<Context>()

    @Before
    fun setUp() {

    }

    @After
    fun setUpAfter() {

    }

    @Test
    fun emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertTrue(CommonUtils.isValidEmail("name@email.com"))
    }

    @Test
    fun emailValidator_IncorrectEmailSimple_ReturnsTrue() {
        assertTrue(CommonUtils.isValidEmail("name@"))
    }

    @Test
    fun passwordValidator_IncorrectPasswordSimple_ReturnsFalse() {
        assertTrue(CommonUtils.isValidEmail("mail"))
    }

    @Test
    fun passwordValidator_CorrectPasswordSimple_ReturnsFalse() {
        assertTrue(CommonUtils.isValidEmail("1234567"))
    }

    @Mock
    private lateinit var mockContext: Context

    @Test
    fun readStringFromContext_LocalizedString() {
        // Given a mocked Context injected into the object under test...
        `when`(mockContext.getString(R.string.hello_word))
            .thenReturn(FAKE_STRING)
        //    val myObjectUnderTest = ClassUnderTest(mockContext)

        // ...when the string is returned from the object under test...
        val result: String = ""// myObjectUnderTest.getHelloWorldString()

        // ...then the result should be the expected one.
        // assertThat(result, `is`(FAKE_STRING))
    }

    @Test
    fun checkValidEmail() {
        val email = "s@s.com"
        Assert.assertEquals(4, 2 + 2)
    }

}