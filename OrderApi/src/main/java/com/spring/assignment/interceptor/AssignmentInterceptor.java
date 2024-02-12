package com.spring.assignment.interceptor;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AssignmentInterceptor implements HandlerInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(AssignmentInterceptor.class);

	 
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler)
	throws Exception{
		
		System.out.println("++++++++++++Inside prehandle++++++++++++");
		
	  String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            logger.warn("Unauthorized access to {}", request.getRequestURI());
            return false;
	        }
        
	        // Perform authorization
	        String token = authHeader.substring(7);
	        if (!validateToken(token)) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
	            logger.warn("Access denied to {}", request.getRequestURI());
            return false;
        }
	        
	        // Log the request
	        logger.info("Incoming request to {}", request.getRequestURI());
	        
	        return true;
	    }
	    
	    private boolean validateToken(String token) {
        // Perform token validation logic here
	        return true;
	    
	}
//	
//	
	//post handle is used to log info about request and response, modify the response
	// cleanup like closing db connections
	// error handling
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,
			ModelAndView modelAndView)
	throws Exception{
		
		System.out.println("++++++++++++Inside posthandle++++++++++++");
		logger.info("Request URL: " + request.getRequestURL());
        logger.info("Request method: " + request.getMethod());
        logger.info("Request parameters: " + request.getParameterMap());

        // Log the response information
        logger.info("Response status: " + response.getStatus());
        logger.info("Response content type: " + response.getContentType());
		HandlerInterceptor.super.postHandle(request, response, handler,modelAndView);
		
	}
	//After completion is mainly used for cleanup,performance monitoring and error handling
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,
			Exception ex)
	throws Exception{
		
		System.out.println("++++++++++++Inside afterCompletion++++++++++++");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
		
	}
	
}
