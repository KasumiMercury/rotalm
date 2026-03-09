package net.mercuryksm.rotalm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform