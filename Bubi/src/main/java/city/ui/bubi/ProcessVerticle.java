package city.ui.bubi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import io.vertx.core.logging.LoggerFactory;

public class ProcessVerticle extends AbstractVerticle {

	private String inputAddress;
	
	@Override
	public void start() throws Exception {
		LoggerFactory.getLogger(ProcessVerticle.class);
		this.inputAddress = config().getJsonArray("verticles").getJsonObject(1).getString("inputAddress");
		vertx.eventBus().localConsumer(inputAddress, (Message<String> message) -> {
			String messageString = message.body().toString();
			Parser.parseAndWriteToConsole(messageString);
		});
	}


//	private void parseAndWriteToConsole(Message<String> buffer) {
//
//		JsonObject rootJson = new JsonObject(buffer.body());
//		JsonArray events = extractBikeStationEvents(rootJson);
//		for (int i = 0; i < events.size(); ++i) {
//			final JsonObject tempData = events.getJsonObject(i);
//
//			System.out.println(i + ".Object ----------------------------------------");
//			System.out.println("");
//			System.out.println("id: " + tempData.getString("id"));
//			System.out.println("lat: " + tempData.getDouble("lat"));
//			System.out.println("lon: " + tempData.getDouble("lon"));
//			System.out.println("name: " + tempData.getString("name"));
//			System.out.println("code: " + tempData.getString("code"));
//			System.out.println("type: " + tempData.getString("type"));
//			System.out.println("bikes: " + tempData.getInteger("bikes"));
//			System.out.println("spaces: " + tempData.getInteger("spaces"));
//			System.out.println("");
//
//		}
//	}
	
	
}
