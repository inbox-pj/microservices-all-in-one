package com.usl.microservices.cartprocessing.utils;

import org.springframework.util.Assert;

public class APIContextHolder {

	private static final ThreadLocal<APIContext> USR_CTX = new ThreadLocal<APIContext>();

	public static final void setContext(APIContext context) {
		Assert.notNull(context, "Only non-null UserContext instances are permitted");
		USR_CTX.set(context);
	}

	public static final APIContext createEmptyContext() {
		return new APIContext();
	}

	public static final APIContext getContext() {
		APIContext context = USR_CTX.get();

		if (context == null) {
			context = createEmptyContext();
			setContext(context);

		}
		return USR_CTX.get();
	}
}
