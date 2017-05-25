@file:JvmName("MainStarter")

import net.cardosi.microservices.registrationservice.RegistrationServer
import net.cardosi.microservices.configurationservice.ConfigurationServer
import net.cardosi.microservices.persistenceservice.PersistenceServer
import kotlin.concurrent.thread

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 17/11/2016.
 */
class MainStarter {

    companion object {

        /**
         * Run the application using Spring Boot and an embedded servlet engine.

         * @param args
         * *            Program arguments - ignored.
         */
        @JvmStatic fun main(args: Array<String>) {
            thread(true, false, null, "RegistrationService", Thread.MAX_PRIORITY, {
                RegistrationServer.main(args)
            })
            thread(true, false, null, "PersistenceServer", Thread.NORM_PRIORITY, {
                PersistenceServer.main(args)
            })
            thread(true, false, null, "ConfigurationServer", Thread.NORM_PRIORITY, {
                ConfigurationServer.main(args)
            })
            // new Tread().RegistrationServer.main(args)
        }
    }
}
