package com.simfyafrica.assessment.data.model

import org.parceler.Parcel

@Parcel
data class CatsResponse(
    val cats: MutableList<Cat> // just a list of cats
)



