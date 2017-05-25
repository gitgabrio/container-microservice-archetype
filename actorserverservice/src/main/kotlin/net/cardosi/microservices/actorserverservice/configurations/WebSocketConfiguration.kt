package net.cardosi.microservices.actorserverservice.configurations

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 25/05/17.
 */

@Configuration
@EnableWebSocketMessageBroker
open class WebSocketConfiguration : AbstractWebSocketMessageBrokerConfigurer() {

    override fun configureMessageBroker(config: MessageBrokerRegistry?) {
        // use the /topic prefix for outgoing WebSocket communication
        config!!.enableSimpleBroker("/topic")
        // use the /app prefix for others
        config.setApplicationDestinationPrefixes("/app")
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        // use the /guestbook endpoint (prefixed with /app as configured above) for incoming requests
        registry.addEndpoint("/guestbook").withSockJS()
    }
}