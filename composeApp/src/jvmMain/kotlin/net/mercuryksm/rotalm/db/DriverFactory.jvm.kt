package net.mercuryksm.rotalm.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import java.io.File

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        val dbPath = File(System.getProperty("user.home"), ".rotalm/rotalm.db")
        dbPath.parentFile.mkdirs()
        val dbExists = dbPath.exists()
        val driver = JdbcSqliteDriver("jdbc:sqlite:${dbPath.absolutePath}")
        if (!dbExists) {
            AppDatabase.Schema.create(driver)
        }
        return driver
    }
}
