package test;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;

import com.buscapecompany.poc.cucumbermicroservice.CucumberMicroserviceApplication;
import com.buscapecompany.poc.cucumbermicroservice.config.ServerStartedInitializingBean;

/**
 *
 * @author pthomas3
 */
public class ServerStart {

    private static final Logger logger = LoggerFactory.getLogger(ServerStart.class);

    private ConfigurableApplicationContext context;
    private MonitorThread monitor;
    private int port = 0;

    public void start(String[] args, boolean wait) throws Exception {
        if (wait) {
            try {
                logger.info("attempting to stop server if it is already running");
                new ServerStop().stopServer();
            } catch (Exception e) {
                logger.info("failed to stop server (was probably not up): {}", e.getMessage());
            }
        }
        context = CucumberMicroserviceApplication.run(args);
        ServerStartedInitializingBean ss = context.getBean(ServerStartedInitializingBean.class);
        port = ss.getLocalPort();
        logger.info("started server on port: {}", port);
        if (wait) {
            int stopPort = port + 1;
            logger.info("will use stop port as {}", stopPort);
            monitor = new MonitorThread(stopPort, new Stoppable() {
                @Override
                public void stop() throws Exception {
                    context.close();
                }
            });
            monitor.start();
            monitor.join();
        }
    }

    public int getPort() {
        return port;
    }

    public void stop() {
        logger.info("stopping spring context");
        context.stop();
    }

    @Test
    public void startServer() throws Exception {
        start(new String[]{}, true);
    }

}