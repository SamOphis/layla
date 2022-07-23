package uk.luau.layla.commands

import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.core.on
import dev.kord.rest.builder.interaction.ChatInputCreateBuilder
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uk.luau.layla.startup.Layla

class CommandService : KoinComponent {
    private val commandMap by inject<MutableMap<String, CommandModule>>()
    private val layla by inject<Layla>()

    fun setupEventHandlers() {
        layla.kord.on<GuildChatInputCommandInteractionCreateEvent> {
            commandMap[interaction.invokedCommandName]?.executeAsGuild(this)
        }

        layla.kord.on<ChatInputCommandInteractionCreateEvent> {
            commandMap[interaction.invokedCommandName]?.execute(this)
        }
    }

    suspend fun registerCommand(command: CommandModule) {
        layla.kord.createGlobalChatInputCommand(command.name, command.description) { command.setup(this) }
        commandMap[command.name] = command
    }
}

abstract class CommandModule : KoinComponent {
    abstract val name: String
    abstract val description: String

    open fun setup(builder: ChatInputCreateBuilder) {}
    open suspend fun execute(context: ChatInputCommandInteractionCreateEvent) {}
    open suspend fun executeAsGuild(context: GuildChatInputCommandInteractionCreateEvent) { execute(context) }
    open suspend fun register() = CommandService().registerCommand(this)
}