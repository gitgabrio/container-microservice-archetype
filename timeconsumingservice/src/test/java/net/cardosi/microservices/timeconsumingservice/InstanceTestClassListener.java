package net.cardosi.microservices.timeconsumingservice;

/**
 * Interface used to provide methods (beforeClassSetup and afterClassSetup) to be executed only once during test execution, for example for
 * shared objects instantiation
 */
public interface InstanceTestClassListener {

    void beforeClassSetup();

    void afterClassSetup();

}