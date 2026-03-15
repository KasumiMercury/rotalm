package net.mercuryksm.rotalm.db

import app.cash.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createAppDatabase(driverFactory: DriverFactory): AppDatabase {
    return AppDatabase(driverFactory.createDriver())
}

fun generatePublicId(): String {
    val chars = "0123456789abcdef"
    return buildString(16) {
        repeat(16) {
            append(chars.random())
        }
    }
}
