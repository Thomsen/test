package com.km2

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform