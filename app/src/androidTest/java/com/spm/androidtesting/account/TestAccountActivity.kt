package com.spm.androidtesting.account

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith


/**
 * Created by Sibaprasad Mohanty on 2020-01-30.
 * Spm Limited
 * sp.dobest@gmail.com
 */

@RunWith(AndroidJUnit4::class)
class TestAccountActivity {

    @Rule
    var activityActivityTestRule: ActivityTestRule<AccountActivity> =
        ActivityTestRule<AccountActivity>(
            AccountActivity::class.java
        )

    @Before
    fun init() {
        activityActivityTestRule.activity
            .supportFragmentManager.beginTransaction()
    }


}