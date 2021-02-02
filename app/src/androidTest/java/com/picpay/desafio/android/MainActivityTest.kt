package com.picpay.desafio.android

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    private fun prepare(func: MainActivityRobots.MainActivityPrepare.() -> Unit) =
        MainActivityRobots.MainActivityPrepare().apply { (func()) }

    private fun execute(func: MainActivityRobots.MainActivityExecute.() -> Unit) =
        MainActivityRobots.MainActivityExecute().apply { (func()) }

    private fun validate(func: MainActivityRobots.MainActivityValidate.() -> Unit) =
        MainActivityRobots.MainActivityValidate().apply { (func()) }

    @Test
    fun shouldDisplayTitle() {
        prepare { openActivity() }
        validate { validateText("Contatos") }
    }

    @Test
    fun shouldDisplayListItem() {
        prepare {
            openActivity()
        }
        validate {
            validateItemRecyclerView(position = 0, text= " Eduardo Santos")
        }
    }
}
