package net.mercuryksm.rotalm.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import net.mercuryksm.rotalm.db.AppDatabase
import org.koin.dsl.module
import java.io.File

val platformModule = module {
    single<SqlDriver> {
        val dbPath = File(System.getProperty("user.home"), ".rotalm/rotalm.db")
        dbPath.parentFile.mkdirs()
        val dbExists = dbPath.exists()
        val driver = JdbcSqliteDriver("jdbc:sqlite:${dbPath.absolutePath}")
        if (!dbExists) {
            AppDatabase.Schema.create(driver)
        }
        driver
    }
}
