package com.android.nytimes.presenter

import com.android.nytimes.view.iview.IHomeView
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomePresenterTest {

    @Mock
    private val iHomeView: IHomeView? = null
    private var iHomePresenter: HomePresenter? = null
    @Before
    fun setUp() {
        iHomePresenter = HomePresenter(iHomeView!!)


    }

    @After
    fun tearDown() {
    }

    @Test
    fun onCreate() {
    }

    /*@Test
    fun onItemClick(data: NewsDetail?) {
        assertEquals("No Data", (data == null))
    }*/
}