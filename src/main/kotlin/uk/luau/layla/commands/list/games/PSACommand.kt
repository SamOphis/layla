package uk.luau.layla.commands.list.games

import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import uk.luau.layla.commands.CommandModule

class PSACommand : CommandModule() {
    override val name get() = "psa"
    override val description get() = "Gives you a perfect sensitivity approximation (PSA) for Overwatch."

    override suspend fun execute(context: ChatInputCommandInteractionCreateEvent) {
        val interaction = context.interaction
        val response = interaction.deferPublicResponse()

        response.respond {
            content = "Hi!"
        }
    }
}