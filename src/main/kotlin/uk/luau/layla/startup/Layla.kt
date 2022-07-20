package uk.luau.layla.startup

import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.GlobalChatInputCommandInteractionCreateEvent
import dev.kord.core.on
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
import kotlin.system.measureTimeMillis
import kotlin.time.ExperimentalTime

suspend fun main() {
    val kord = Kord(System.getenv("LAYLA_TOKEN"))

    kord.createGlobalChatInputCommand("ping", "Fetches connectivity information between Discord and Layla.") { }

    @OptIn(ExperimentalTime::class)
    kord.on<GlobalChatInputCommandInteractionCreateEvent> {
        val response = interaction.deferPublicResponse()
        val latency = measureTimeMillis {
            kord.rest.user.getCurrentUser()
        }

        var content = "Discord API Latency: **${latency}ms**\n"
        kord.gateway.averagePing?.let {
            content += "Discord Gateway Ping: **${it.inWholeMilliseconds}ms**"
        }

        response.respond {
            this.content = content
        }
    }

    kord.login {
        @OptIn(PrivilegedIntent::class)
        intents += Intent.MessageContent
    }
}