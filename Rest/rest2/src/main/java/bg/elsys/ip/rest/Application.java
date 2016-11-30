package bg.elsys.ip.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class Application extends ResourceConfig {
	private static final int MAX_ENTITY_SIZE = 1024 * 1024 * 5;
	public Application() {
		register(JacksonFeature.class);
		
		//LOG all requests and responses
		register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME), Level.INFO,
		LoggingFeature.Verbosity.PAYLOAD_ANY, MAX_ENTITY_SIZE));
	}
}