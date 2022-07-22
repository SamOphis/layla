package uk.luau.layla.startup

import dev.kord.core.Kord
import kotlinx.coroutines.runBlocking
import org.koin.core.context.startKoin
import org.koin.dsl.module

suspend fun main() {
    val laylaModule = module {
        single { runBlocking { Kord(System.getenv("LAYLA_TOKEN")) } }
    }

    startKoin {
        printLogger()
        modules(laylaModule)
    }

    Layla().start()
}