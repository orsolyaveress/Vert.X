package city.ui.bubi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public class Parser {

	public static List<BubiLocation> parseAndWriteToConsole(String buffer) {

		JsonObject rootJson = new JsonObject(buffer);
		JsonArray events = extractBikeStationEvents(rootJson);
		for (int i = 0; i < events.size(); ++i) {
			final JsonObject tempData = events.getJsonObject(i);	
			
		}
		List<BubiLocation> bubiLocationList = new ArrayList<>();
		try {
			bubiLocationList = createObjects(events);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (BubiLocation bubiLocation : bubiLocationList) {
			System.out.println(bubiLocation.toString());
		}
		return bubiLocationList;
	}
	
	private static List<BubiLocation> createObjects(JsonArray jsonArray) throws IOException {
		List<BubiLocation> bubiLocationList = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		for (int i=0; i<jsonArray.size(); i++) {
			final JsonObject tempData = jsonArray.getJsonObject(i);
			BubiLocation bubiLocation = mapper.readValue(tempData.toString(), BubiLocation.class);
			bubiLocationList.add(bubiLocation);
		}
		return bubiLocationList;
	}

	private static JsonArray extractBikeStationEvents(JsonObject rootJson) {
		return rootJson.getJsonObject("data", new JsonObject()).getJsonArray("list", new JsonArray());

	}

	
}
