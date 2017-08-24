package city.ui.bubi;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class MainVerticle extends AbstractVerticle {

	private final Logger logger = LoggerFactory.getLogger(MainVerticle.class);

	@Override
	public void start() throws InterruptedException {

		/** Count of services. */
		final AtomicInteger serviceCount = new AtomicInteger();
		DeploymentOptions deploymentOptions = new DeploymentOptions();
        deploymentOptions.setConfig(config());
		/** List of verticles that we are starting. */
		final List<AbstractVerticle> verticles = Arrays.asList(new BubiConnector(), new ProcessVerticle());

		verticles.stream().forEach(verticle -> vertx.deployVerticle(verticle,deploymentOptions ,deployResponse -> {

			if (deployResponse.failed()) {
				logger.error("Unable to deploy verticle " + verticle.getClass().getSimpleName(),
						deployResponse.cause());
			} else {
				logger.info(verticle.getClass().getSimpleName() + " deployed");
				serviceCount.incrementAndGet();
			}
		}));
	}

	public static void main(final String... args) {
		final Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new MainVerticle());
	}
}
