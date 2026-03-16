package net.mercuryksm.rotalm.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import net.mercuryksm.rotalm.db.AppDatabase
import org.koin.dsl.module

val platformModule = module {
    single<SqlDriver> { NativeSqliteDriver(AppDatabase.Schema, "rotalm.db") }
}
