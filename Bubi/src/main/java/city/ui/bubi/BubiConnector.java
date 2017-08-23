package city.ui.bubi;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;

import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import java.util.Date;

public class BubiConnector extends AbstractVerticle {

	protected Logger logger;
	private HttpClient httpClient;

	@Override
	public void start(Future<Void> fut) {
		this.logger = LoggerFactory.getLogger(BubiConnector.class);
		httpClient = vertx.createHttpClient();

		getJsonFromUrl();
	}

	private void getJsonFromUrl() {

		String endPoint = "/bkk-utvonaltervezo-api/ws/oba/api/where/bicycle-rental";
		logger.info("querying from " + this + " to " + endPoint + " at " + new Date());

		httpClient.getNow(80, "futar.bkk.hu", endPoint, (HttpClientResponse response) -> {
			if (response.statusCode() >= 200 && response.statusCode() < 400) {
				response.exceptionHandler((Throwable ex) -> {
					logger.error("Inbound Verticle", ex);
				});
				response.bodyHandler((Buffer body) -> {
					vertx.eventBus().send("city.ui.bubi.ProcessVerticle", body.toString());
				});
			}
		});
	}

	@Override
	public void stop() {
		httpClient.close();
		this.vertx.close();
	}

}
