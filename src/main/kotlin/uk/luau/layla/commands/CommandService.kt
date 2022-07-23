package uk.luau.layla.commands

import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.core.on
import dev.kord.rest.builder.interaction.ChatInputCreateBuilder
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uk.luau.layla.startup.Layla

class CommandService : KoinComponent {
    private val commandMap by inject<MutableMap<String, Command>>()
    private val layla by inject<Layla>()

    fun setupEventHandlers() {
        layla.kord.on<GuildChatInputCommandInteractionCreateEvent> {
            commandMap[interaction.invokedCommandName]?.executeAsGuild(this)
        }

        layla.kord.on<ChatInputCommandInteractionCreateEvent> {
            commandMap[interaction.invokedCommandName]?.execute(this)
        }
    }

    suspend fun command(name: String, description: String, setup: ChatInputCreateBuilder.() -> Unit = {},
                        execute: suspend GuildChatInputCommandInteractionCreateEvent.() -> Unit = {}) {

        layla.kord.createGlobalChatInputCommand(name, description, setup)

        commandMap[name] = object : Command(name, description, setup) {
            override suspend fun executeAsGuild(context: GuildChatInputCommandInteractionCreateEvent) = execute(context)
        }
    }
}

@Suppress("unused") // Just to stop getting bothered about name/description not being referenced yet. ;w;
open class Command(val name: String, val description: String,
                   private val builder: ChatInputCreateBuilder.() -> Unit = {}) {

    open suspend fun execute(context: ChatInputCommandInteractionCreateEvent) {}
    open suspend fun executeAsGuild(context: GuildChatInputCommandInteractionCreateEvent) { execute(context) }
}