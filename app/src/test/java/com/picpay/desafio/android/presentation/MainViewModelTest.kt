package com.picpay.desafio.android.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.picpay.desafio.android.data.repository.MainRepositoryImpl
import com.picpay.desafio.android.di.createGetMainUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MainViewModelTest : AutoCloseKoinTest() {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var observer: Observer<MainDataStates>

    private lateinit var mainViewModel: MainViewModel

    @MockK
    private lateinit var repository: MainRepositoryImpl

    @Before
    fun setupsTest() {
        MockKAnnotations.init(this)
        MockitoAnnotations.initMocks(this)
        observer = mock(Observer::class.java) as Observer<MainDataStates>
        Dispatchers.setMain(mainThreadSurrogate)
        startKoin {
            modules(
                module {
                    single { createGetMainUseCase(repository) }
                    viewModel { MainViewModel(get()) }
                }
            )
        }
        mainViewModel = get()
        coEvery { repository.getUsers()  } returns mockk()
    }

    @Test
    fun `Assert that viewStates is not null`() {
        mainViewModel.viewStates.observeForever(observer)
        assertNotNull(mainViewModel.viewStates)
    }

    @Test
    fun `Assert state hasObserver`() {
        mainViewModel.viewStates.observeForever(observer)
        assert(mainViewModel.viewStates.hasObservers())
    }

    @Test
    fun `Assert that users list should return success`() {
        mainViewModel.viewStates.observeForever(observer)
        mainViewModel.interpret(MainInteractor.GetListUsers)
        verify(observer).onChanged(isA(MainDataStates.CallSucess::class.java))
    }
}