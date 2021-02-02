package com.example.demo.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.example.demo.dao.ManagerWithSuccess;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuthRejectHandler extends SimpleUrlAuthenticationFailureHandler{
	
	private ObjectMapper mapper;

    @Autowired
    void AuthSuccessHandler(MappingJackson2HttpMessageConverter messageConverter) {
        this.mapper = messageConverter.getObjectMapper();
    }
	
	@Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        ManagerWithSuccess mws = new ManagerWithSuccess(null, false);
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        mapper.writeValue(writer, mws);
        writer.flush();
    }

}
