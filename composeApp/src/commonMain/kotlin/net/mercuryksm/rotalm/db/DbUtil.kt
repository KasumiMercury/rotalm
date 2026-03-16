package net.mercuryksm.rotalm.db

fun generatePublicId(): String {
    val chars = "0123456789abcdef"
    return buildString(16) {
        repeat(16) {
            append(chars.random())
        }
    }
}
