package com.yapp.timecapsule.init

enum class AppStatus(val priority: Int) {
    BACKGROUND(0),
    RETURNED_TO_FOREGROUND(1),
    FOREGROUND(2)
}