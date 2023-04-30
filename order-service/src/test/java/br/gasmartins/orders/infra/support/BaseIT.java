package br.gasmartins.orders.infra.support;


import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

@ContextConfiguration(initializers = {BaseIT.Initializer.class})
public class BaseIT {

    private static GenericContainer<?> container;
    private static final String REDIS_IMAGE = "redis";
    private static final int REDIS_PORT = 6379;

    public static void startRedis() {
        //System.setProperty("org.testcontainers.dockerclient.podman.PodmanClientProviderStrategy.priority", "1");
        //System.setProperty("testcontainers.provider", "podman");
        //It's not working with DockerComposeContainer and Podman
        /*container= new DockerComposeContainer(new File("src/test/resources/redis-compose.yml"))
                                .withExposedService(REDIS_SERVICE, REDIS_PORT,  Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(30)))
                                .withExposedService(REDIS_INSIGHT_SERVICE, REDIS_INSIGHT_PORT,  Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(30)))
                                .withLocalCompose(true);

        container.start();*/

        container = new GenericContainer<>(DockerImageName.parse(REDIS_IMAGE))
                        .withCommand("redis-server --requirepass redis")
                        .withExposedPorts(REDIS_PORT)
                        .waitingFor(Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(30)));
        container.start();
        Runtime.getRuntime().addShutdownHook(new Thread(container::stop));
    }


    private static String getUrl() {
        return getHost() + ":" + getPort();
    }

    private static Integer getPort() {
        return container.getFirstMappedPort();
    }

    private static String getHost() {
        return container.getHost();
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.redis.host=" + getHost(),
                      "spring.redis.port=" + getPort()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
