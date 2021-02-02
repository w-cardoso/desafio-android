package com.picpay.desafio.android

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.picpay.desafio.android.RecyclerViewMatchers.checkRecyclerViewItem
import com.picpay.desafio.android.presentation.MainActivity

class MainActivityRobots {

    class MainActivityPrepare {
        fun openActivity() = launchActivity<MainActivity>()
    }

    class MainActivityExecute {}

    class MainActivityValidate {

        fun validateText(text: String) {
            onView(withText(text))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }

        fun validateItemRecyclerView(id: Int = R.id.recyclerView, position: Int, text: String) {
            checkRecyclerViewItem(id, position, withText(text))
        }
    }
}