package net.mercuryksm.rotalm.di

import net.mercuryksm.rotalm.db.AppDatabase
import org.koin.dsl.module

val appModule = module {
    single<AppDatabase> { AppDatabase(get()) }
}
