package net.cardosi.microservices.timeconsumingservice;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Custom class extending <code>SpringJUnit4ClassRunner</code> to call our specific methods
 * defined in <code>InstanceTestClassListener</code>
 */
public class SpringInstanceTestClassRunner extends SpringJUnit4ClassRunner {

    private InstanceTestClassListener InstanceSetupListener;

    public SpringInstanceTestClassRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }

    @Override
    protected Object createTest() throws Exception {
        Object test = super.createTest();
        // Note that JUnit4 will call this createTest() multiple times for each
        // test method, so we need to ensure to call "beforeClassSetup" only once.
        if (test instanceof InstanceTestClassListener && InstanceSetupListener == null) {
            InstanceSetupListener = (InstanceTestClassListener) test;
            InstanceSetupListener.beforeClassSetup();
        }
        return test;

    }

    @Override
    public void run(RunNotifier notifier) {
        super.run(notifier);
        if (InstanceSetupListener != null)
            InstanceSetupListener.afterClassSetup();
    }

}