package uk.luau.layla.startup

import dev.kord.core.Kord
import org.koin.core.context.startKoin
import org.koin.dsl.module
import uk.luau.layla.commands.CommandModule
import uk.luau.layla.commands.CommandService
import uk.luau.layla.commands.list.games.PSACommand

suspend fun main() {
    val kord = Kord(System.getenv("LAYLA_TOKEN"))

    val laylaModule = module {
        single { kord }
    }

    val commandServiceModule = module {
        single { Layla() }
        single { mutableMapOf<String, CommandModule>() }
    }

    startKoin {
        printLogger()
        modules(laylaModule, commandServiceModule)
    }

    CommandService().setupEventHandlers()
    PSACommand().register()
    Layla().start()
}