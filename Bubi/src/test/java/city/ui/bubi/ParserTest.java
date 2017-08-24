package city.ui.bubi;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import city.ui.bubi.BubiLocation.Builder;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.TestContext;

public class ParserTest {
		
		@Test
	    public void parseAndWriteToConsoleTest() throws IOException {
	        String inputString = "{\"version\":2,\"status\":\"OK\",\"code\":200,\"text\":\"OK\",\"currentTime\":1503561527516,\"data\":{\"list\":[{\"id\":\"1758855\",\"lat\":47.533687300000004,\"lon\":19.0355124,\"name\":\"Becsi ut- Obudai egyetem\",\"code\":\"0303\",\"type\":\"7inch\",\"bikes\":14,\"spaces\":24},{\"id\":\"250969\",\"lat\":47.503449,\"lon\":19.0608268,\"name\":\"Andrassy ut-Nagymezo utca\",\"code\":\"0609\",\"type\":\"character\",\"bikes\":17,\"spaces\":22}]}};\n";
	        List<BubiLocation> expectedList = new ArrayList<>();


	        BubiLocation.Builder builder1 = new BubiLocation.Builder()
	                .setId(1758855)
	                .setLat(47.533687300000004)
	                .setLon(19.0355124)
	                .setName("Becsi ut- Obudai egyetem")
	                .setCode("0303")
	                .setType("7inch")
	                .setBikes(14)
	                .setSpaces(24);

	        BubiLocation.Builder builder2 = new BubiLocation.Builder()
	                .setId(250969)
	                .setLat(47.503449)
	                .setLon(19.0608268)
	                .setName("Andrassy ut-Nagymezo utca")
	                .setCode("0609")
	                .setType("character")
	                .setBikes(17)
	                .setSpaces(22);

	        BubiLocation b1 = builder1.build();
	        BubiLocation b2 = builder2.build();

	        expectedList.add(b1);
	        expectedList.add(b2);

	        assertEquals(Parser.parseAndWriteToConsole(inputString),expectedList);


	    }

    }
	

