package com.simfyafrica.assessment

import android.app.Application
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class AppModuleTest{
    @Mock
    private lateinit var application: Application

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }
}