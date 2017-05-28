package net.cardosi.microservices.timeconsumingservice;

import net.cardosi.microservices.timeconsumingservice.configurations.TimeConsumingConfiguration;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Gabriele Cardosi
 * Abstracty class to be extended by actual Test classes. It implements <code>InstanceTestClassListener</code>
 * to provide implementation of method to be called only once for class (for example instantiation of object shared between methods)
 */
@RunWith(SpringInstanceTestClassRunner.class) // This is to use our custom <code>SpringJUnit4ClassRunner</code>
@WebAppConfiguration
@ContextConfiguration(classes = TimeConsumingConfiguration.class)
@ActiveProfiles("test")
public abstract class AbstractContextTest implements InstanceTestClassListener {

    /**
     * The <code>Environment</code> env.
     */
    @Autowired
    protected Environment env;

    /**
     * Before class setup.
     */
    @Override
    public void beforeClassSetup() {
        assertNotNull(env);
    }

    /**
     * After class setup.
     */
    @Override
    public void afterClassSetup() {

    }


}
