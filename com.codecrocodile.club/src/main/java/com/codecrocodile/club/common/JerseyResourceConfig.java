package com.codecrocodile.club.common;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.glassfish.jersey.server.validation.ValidationFeature;

public class JerseyResourceConfig extends ResourceConfig {
	/**
	 * Register JAX-RS application components.
	 */
	public JerseyResourceConfig() {
		this.packages(true, "com.codecrocodile");
		
		this.register(RequestContextFilter.class);
		this.register(JacksonFeature.class);  
		this.register(MultiPartFeature.class);
		// can also add bean validation here
		this.register(ValidationFeature.class);
		
		this.register(ValidationExceptionMapper.class);
		this.register(ExceptionMapperImpl.class);
		
//		this.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
		
//		this.register(componentClass)
		
//		register(RequestContextFilter.class);
//		register(PodcastRestService.class);
//		register(JacksonFeature.class);
//		register(LoggingResponseFilter.class);
//		register(CORSResponseFilter.class);
	}
}