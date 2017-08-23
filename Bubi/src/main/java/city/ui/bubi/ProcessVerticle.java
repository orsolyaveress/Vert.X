package city.ui.bubi;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class ProcessVerticle extends AbstractVerticle{
	
	private Logger logger;
	
	
	@Override
	public void start() throws Exception {
		this.logger = LoggerFactory.getLogger(ProcessVerticle.class);
		vertx.eventBus().localConsumer("city.ui.bubi.ProcessVerticle", (Message<String> message) -> {
			parseAndWriteToConsole(message);
		});
	}
//
//	private String splitter(String stringTosplit) {
//        int i = 0;
//        while (stringTosplit.charAt(i) != '[') {
//            i++;
//        }
//        String newString = "";
//        while (stringTosplit.charAt(i) != ']') {
//            newString = newString + stringTosplit.charAt(i);
//            i++;
//        }
//        return newString;
//    }


	
	private void parseAndWriteToConsole(Message<String> buffer) {

        JsonObject rootJson = new JsonObject(buffer.body());
        JsonArray events = extractBikeStationEvents(rootJson);
        for (int i = 0; i < events.size(); ++i) {
            final JsonObject tempData = events.getJsonObject(i);

            System.out.println(i + ".Object ----------------------------------------");
            System.out.println("");
            System.out.println("id: " + tempData.getString("id"));
            System.out.println("lat: " + tempData.getDouble("lat"));
            System.out.println("lon: " + tempData.getDouble("lon"));
            System.out.println("name: " + tempData.getString("name"));
            System.out.println("code: " + tempData.getString("code"));
            System.out.println("type: " + tempData.getString("type"));
            System.out.println("bikes: " + tempData.getInteger("bikes"));
            System.out.println("spaces: " + tempData.getInteger("spaces"));
            System.out.println("");

    }
}

private JsonArray extractBikeStationEvents(JsonObject rootJson) {
    return rootJson.getJsonObject("data", new JsonObject()).getJsonArray("list", new JsonArray());

}

}
