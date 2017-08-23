package city.ui.whisky;

import java.util.LinkedHashMap;
import java.util.Map;

import io.vertx.core.*;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

/**
 * Hello world!
 *
 */
public class MyFirstVerticle extends AbstractVerticle {

	@Override
	public void start(Future<Void> startFuture) throws Exception {
		
		createSomeData();
		
		Router router = Router.router(vertx);
		
		router.route("/").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response
				.putHeader("content-type", "text/html")
				.end("<h1>Hello world! :)</h1>");
		});
		
		router.get("/api/whiskies").handler(this::getAll);
		router.route("/api/whiskies*").handler(BodyHandler.create());
		router.post("/api/whiskies").handler(this::addOne);
		router.delete("/api/whiskies/:id").handler(this::deleteOne);
		router.get("/api/whiskies/:id").handler(this::getOne);
		router.put("/api/whiskies/:id").handler(this::updateOne);
		
		router.route("/assets/*").handler(StaticHandler.create("assets"));
		
		vertx
		.createHttpServer()
		.requestHandler(router::accept)
		.listen(
				config().getInteger("http.port", 1235),
				result -> {
					if (result.succeeded()) {
						startFuture.complete();
					} else {
						startFuture.fail(result.cause());
					}
				}
				);
	}
	
	private Map<Integer, Whisky> products = new LinkedHashMap<>();
	
	private void createSomeData() {
		Whisky bowmore = new Whisky("Bowmore 15 Years Laimrig", "Scotland, Islay");
		products.put(bowmore.getId(), bowmore);
		Whisky talisker = new Whisky("Talisker 57° North", "Scotland, Island");
		products.put(talisker.getId(), talisker);
	}
	
	private void getAll(RoutingContext routingContext) {
		routingContext.response()
		.putHeader("content-type", "application/json; charset=utf-8")
		.end(Json.encodePrettily(products.values()));
	}
	
	private void addOne(RoutingContext routingContext) {
		final Whisky whisky = Json.decodeValue(routingContext.getBodyAsString(), Whisky.class);
		products.put(whisky.getId(), whisky);
		routingContext.response()
		.setStatusCode(201)
		.putHeader("content-type", "application/json; charset=utf-8")
		.end(Json.encodePrettily(whisky));
	}
	
	private void deleteOne(RoutingContext routingContext) {
		String id = routingContext.request().getParam("id");
		if (id == null) {
			routingContext.response().setStatusCode(400).end();
		} else {
			Integer idAsInteger = Integer.valueOf(id);
			products.remove(idAsInteger);
		}
		routingContext.response().setStatusCode(204).end();
	}
	
	private void getOne(RoutingContext routingContext) {
		String id = routingContext.request().getParam("id");
		if (id == null) {
			routingContext.response().setStatusCode(400).end();
		} else {
			Integer idAsInteger = Integer.valueOf(id);
			Whisky whisky = products.get(idAsInteger);
			if (whisky == null) {
				routingContext.response().setStatusCode(404).end();
			} else {
				routingContext.response()
				.putHeader("content-type", "application/json; charset=utf-8")
				.end(Json.encodePrettily(whisky));
			}
		}	
	}
	
	private void updateOne(RoutingContext routingContext) {
		String id = routingContext.request().getParam("id");
		JsonObject json = routingContext.getBodyAsJson();
		if (id == null || json == null) {
			routingContext.response().setStatusCode(400).end();
		} else {
			Integer idAsInteger = Integer.valueOf(id);
			Whisky whisky = products.get(idAsInteger);
			if (whisky == null) {
				routingContext.response().setStatusCode(404).end();
			} else {
				whisky.setName(json.getString("name"));
				whisky.setOrigin(json.getString("origin"));
				routingContext.response()
				.putHeader("content-type", "application/json; charset=utf-8")
				.end(Json.encodePrettily(whisky));
			}
		}
	}
}

















