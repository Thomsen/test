package com.km1

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform