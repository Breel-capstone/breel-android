package com.example.breel.data.api.mentor

data class MyMentor(
    val id: Int,
    val fullName: String,
    val price: Int,
    val priceString: String,
    val profileUrl: String,
    val skills: List<String>
)
