package com.example.demo.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.dao.ManagerWithSuccess;
import com.example.demo.dao.Managers;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuthAcceptHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	private ObjectMapper mapper;

    @Autowired
    void AuthSuccessHandler(MappingJackson2HttpMessageConverter messageConverter) {
        this.mapper = messageConverter.getObjectMapper();
    }
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
    	clearAuthenticationAttributes(request);
    	response.setStatus(HttpServletResponse.SC_OK);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Managers user = userDetails.getManager();
        userDetails.setManager(user);
        ManagerWithSuccess mws = new ManagerWithSuccess(user, true);
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        mapper.writeValue(writer, mws);
        writer.flush();        
    }

}
