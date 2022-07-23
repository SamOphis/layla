package uk.luau.layla.startup

import dev.kord.core.Kord
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Layla : KoinComponent {
    val kord by inject<Kord>()

    suspend fun start() {
        kord.login {
            @OptIn(PrivilegedIntent::class)
            intents += Intent.MessageContent
        }
    }
}