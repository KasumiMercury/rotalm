package net.mercuryksm.rotalm.di

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import net.mercuryksm.rotalm.db.AppDatabase
import org.koin.dsl.module

fun platformModule(context: Context) = module {
    single<SqlDriver> { AndroidSqliteDriver(AppDatabase.Schema, context, "rotalm.db") }
}
