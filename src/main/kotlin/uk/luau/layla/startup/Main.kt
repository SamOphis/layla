package uk.luau.layla.startup

import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.response.respond
import org.koin.core.context.startKoin
import org.koin.dsl.module
import uk.luau.layla.commands.Command
import uk.luau.layla.commands.CommandService

suspend fun main() {
    val kord = Kord(System.getenv("LAYLA_TOKEN"))
    val laylaModule = module {
        single { kord }
    }

    val commandServiceModule = module {
        single { Layla() }
        single { mutableMapOf<String, Command>() }
    }

    startKoin {
        printLogger()
        modules(laylaModule, commandServiceModule)
    }

    CommandService().setupEventHandlers()
    CommandService().command("ping", "Pings") {
        val response = interaction.deferPublicResponse()
        response.respond { content = "Pong!" }
    }

    Layla().start()
}