@file:JvmName("PersistenceConfiguration")
package net.cardosi.microservices.persistenceservice.configurations

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder
import org.springframework.boot.context.properties.ConfigurationProperties
//import org.springframework.boot.orm.jpa.EntityScan
import org.springframework.context.annotation.*
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import java.util.logging.Logger
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

/**
 * The users Spring configuration.

 * @author Paul Chapman
 */
@Configuration
@ComponentScan("net.cardosi.microservices.persistenceservice")
@EntityScan("net.cardosi.microservices.persistenceservice.entities")
@EnableJpaRepositories("net.cardosi.microservices.persistenceservice.repositories")
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

//    @Bean
//    open fun sessionFactory(emf: EntityManagerFactory): HibernateJpaSessionFactoryBean {
//        val factory = HibernateJpaSessionFactoryBean()
//        factory.setEntityManagerFactory(emf)
//        return factory
//    }
//
//
////    @Bean
////    open fun entityManager(): EntityManager {
////        return entityManagerFactory().createEntityManager()
////    }
////
//    @Bean
//    open fun entityManagerFactory(): EntityManagerFactory {
//        val vendorAdapter = HibernateJpaVendorAdapter()
//        vendorAdapter.setGenerateDdl(false)
//
//        val factory : LocalContainerEntityManagerFactoryBean = LocalContainerEntityManagerFactoryBean()
//        factory.jpaVendorAdapter = vendorAdapter
////        factory.jpaDialect = "org.hibernate.dialect.HSQLDialect"
//        factory.dataSource = dataSource()
//        factory.setPackagesToScan("net.cardosi.microservices.persistenceservice.entities")
//        factory.afterPropertiesSet()
//        return factory.`object`
//    }
//
//    @Bean
//    open fun transactionManager(): PlatformTransactionManager {
//        val txManager = JpaTransactionManager()
//        txManager.entityManagerFactory = entityManagerFactory()
//        return txManager
//    }


}
