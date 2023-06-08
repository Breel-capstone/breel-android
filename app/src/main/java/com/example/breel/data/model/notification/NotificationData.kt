package com.example.breel.data.model.notification

data class NotificationData(
    val id: String,
    val title: String,
    val description: String,
    val profileUrl: String = "https://api.dicebear.com/6.x/open-peeps/png?clothingColor=17231d&skinColor=fdf2f5&seed=test.mentor.2"
)