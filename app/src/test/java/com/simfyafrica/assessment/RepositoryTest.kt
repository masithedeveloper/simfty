package com.simfyafrica.assessment

import com.simfyafrica.assessment.data.CatsRepository
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RepositoryTest{
    @Mock
    private lateinit var mockRepository: CatsRepository

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)

    }
}