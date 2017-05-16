@file:JvmName("ActorServerConfiguration")

package net.cardosi.microservices.actorserverservice.configurations

//import org.springframework.cloud.client.discovery.DiscoveryClient
import akka.actor.ActorSystem
import akka.actor.Props
import com.netflix.appinfo.ApplicationInfoManager
import com.typesafe.config.ConfigFactory
import net.cardosi.microservices.actorserverservice.actors.ServerActor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 04/11/2016.
 */
@Configuration
@EnableDiscoveryClient
@ComponentScan("net.cardosi.microservices.actorserverservice")
open class ActorServerConfiguration {


//    @Autowired
//    private var applicationInfoManager: ApplicationInfoManager? = null

    @Bean
    open fun actorSystem(): ActorSystem {
        val system = ActorSystem.create("RemoteWorkerSystem", ConfigFactory.defaultApplication())
        system.actorOf(Props.create(ServerActor::class.java), "serverActor")
//        val metadata = applicationInfoManager?.info?.metadata
//        metadata?.put("foo", "bar")
        return system
    }

}
