@file:JvmName("ServerActor")
package net.cardosi.microservices.actorserverservice.actors

import akka.actor.UntypedActor
import com.netflix.appinfo.ApplicationInfoManager
import com.netflix.appinfo.InstanceInfo
import com.netflix.appinfo.MyDataCenterInstanceConfig
import com.netflix.discovery.DefaultEurekaClientConfig
import com.netflix.discovery.DiscoveryClient
import com.netflix.discovery.DiscoveryManager
import com.netflix.discovery.EurekaClient
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient
import java.lang.Exception

/**
 * Created by Gabriele Cardosi - gcardosi@cardosi.net on 07/05/17.
 */

open class ServerActor : UntypedActor() {


//    private var applicationInfoManager: ApplicationInfoManager? = null
//    private var eurekaClient: EurekaClient? = null

//    @Throws(Exception::class)
//    override fun preStart() {
//        registerWithEureka()
//    }
//
//    @Throws(Exception::class)
//    override fun postStop() {
//        unRegisterWithEureka()
//    }

    @Throws(Exception::class)
    override fun onReceive(o: Any) {
        if (o is String) {
            System.out.println("Hello " + o)
            sender.tell("OK", self)
        } else {
            unhandled(o)
        }
    }


//    private fun registerWithEureka() {
//        discoveryClient.getInstancesByVipAddress()
//
//        applicationInfoManager = ApplicationInfoManager.getInstance()
//        DiscoveryManager.getInstance().initComponent(
//                MyDataCenterInstanceConfig(),
//                DefaultEurekaClientConfig())
//        eurekaClient = DiscoveryManager.getInstance().eurekaClient
//        println("Registering service to eureka with STARTING status")
//        applicationInfoManager!!.setInstanceStatus(InstanceInfo.InstanceStatus.STARTING)
//        println("Simulating service initialization by sleeping for 2 seconds...")
//        // Now we change our status to UP
//        println("Done sleeping, now changing status to UP")
//        applicationInfoManager!!.setInstanceStatus(InstanceInfo.InstanceStatus.UP)
//        waitForRegistrationWithEureka(eurekaClient as EurekaClient)
//        println("Service started and ready to process requests..")
//    }
//
//    private fun unRegisterWithEureka() {
//        DiscoveryManager.getInstance().shutdownComponent()
//    }
//
//
//    private fun waitForRegistrationWithEureka(eurekaClient: EurekaClient) {
//        // my vip address to listen on
//        val vipAddress = "akka.idtrust.com.br"
//        var nextServerInfo: InstanceInfo? = null
//        while (nextServerInfo == null) {
//            try {
//                nextServerInfo = eurekaClient.getNextServerFromEureka(vipAddress, false)
//            } catch (e: Throwable) {
//                println("Waiting ... verifying service registration with eureka ...")
//                try {
//                    Thread.sleep(10000)
//                } catch (e1: InterruptedException) {
//                    e1.printStackTrace()
//                }
//
//            }
//
//        }
//    }

}
