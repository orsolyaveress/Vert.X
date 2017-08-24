package city.ui.bubi;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;

import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import java.util.Date;

public class BubiConnector extends AbstractVerticle {

	protected Logger logger;
	private HttpClient httpClient;
	private String outputAddress;

	@Override
	public void start(Future<Void> fut) {
		this.logger = LoggerFactory.getLogger(BubiConnector.class);
		httpClient = vertx.createHttpClient();

		this.outputAddress = config().getJsonArray("verticles").getJsonObject(0).getString("outputAddress");
		
		getJsonFromUrl();
	}

	private void getJsonFromUrl() {

		String endPoint = config().getJsonArray("verticles").getJsonObject(0).getString("pathTemplate");
		String host = config().getJsonArray("verticles").getJsonObject(0).getString("host");
		Integer port = config().getJsonArray("verticles").getJsonObject(0).getInteger("port");
		logger.info("querying from " + this + " to " + endPoint + " at " + new Date());

		httpClient.getNow(port, host, endPoint, (HttpClientResponse response) -> {
			if (response.statusCode() >= 200 && response.statusCode() < 400) {
				response.exceptionHandler((Throwable ex) -> {
					logger.error("Inbound Verticle", ex);
				});
				response.bodyHandler((Buffer body) -> {
					vertx.eventBus().send(outputAddress, body.toString());
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
