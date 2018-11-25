package com.microservice.filters;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class PreFilter extends ZuulFilter {
	
	private static final Logger logger = LoggerFactory.getLogger(PreFilter.class);

	@Override
	public Object run() throws ZuulException {
		logger.info("Inside Pre Filter");
		RequestContext ctx = RequestContext.getCurrentContext();
	    HttpServletRequest request = ctx.getRequest();
	 
	    logger.info("Request Method : " + request.getMethod() + " Request URL : " + request.getRequestURL().toString());
		return null;
	}

	/*The filter executes only if the request has param named mobile 
	e.g: http://localhost:9999/toll-system/toll-rate/charges?stationId=30&source=mobile
	*/	
	@Override
	public boolean shouldFilter() {
		
		boolean isMobile = false;
		
		RequestContext ctx = getCurrentContext();
		String param = ctx.getRequest().getParameter("source");
		if(param !=null && param.equals("mobile")) {
			isMobile = true;
		}
		return isMobile;
	}

	@Override
	public int filterOrder() {		
		return 1;
	}

	@Override
	public String filterType() {		
		return "pre";
	}

}
