@file:JvmName("PersistenceConfiguration")
package net.cardosi.microservices.persistenceservice.configurations

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityScan
import org.springframework.context.annotation.*
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import java.util.logging.Logger
import javax.sql.DataSource

/**
 * The users Spring configuration.

 * @author Paul Chapman
 */
@Configuration
@ComponentScan("net.cardosi.microservices.persistenceservice")
@EntityScan("net.cardosi.microservices.persistenceservice.entities")
@EnableJpaRepositories("net.cardosi.microservices.persistenceservice.repositories")
//@PropertySource("persistence-server.properties", "classpath:db-config.properties")
open class PersistenceConfiguration {

    protected var logger: Logger

    init {
        logger = Logger.getLogger(javaClass.name)
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "testDatasource")
    open fun dataSource(): DataSource {
        logger.info("dataSource() invoked")
        return DataSourceBuilder.create().build()
    }


}
