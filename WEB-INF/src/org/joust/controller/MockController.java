package org.joust.controller;

import groovy.lang.GroovyShell;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MockController
{
	@RequestMapping("/*.mock")
	public ModelAndView render(HttpServletRequest request) 
	{
		String jspName = extractStringFromUriAfterLastMatch(request.getRequestURI(), "/");
		jspName = "/"+jspName.replace(".mock", ".jsp");
		Map model = null;
        GroovyShell shell = new GroovyShell();
        try {
            model = (Map) shell.evaluate(new File(request.getSession().getServletContext().getRealPath("")+"/WEB-INF/mock"+jspName+".mock"));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }   
        ModelAndView mav = new ModelAndView(jspName, model);
		return mav;
	}

	String extractStringFromUriAfterLastMatch(String uri, String match)
	{
		String extractedText = "";
		String[] tokens = uri.split(match);
		try {
			extractedText = URLDecoder.decode(tokens[tokens.length-1], "UTF-8");
		} 
		catch (UnsupportedEncodingException e) {
			//ignore and let blow up later
		}
		return extractedText;
	}
}