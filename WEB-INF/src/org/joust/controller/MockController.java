package org.joust.controller;

import groovy.lang.GroovyShell;
import java.io.File;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MockController
{
	//1. works if genericize viewresolver prefix so can go to mock/blah.jsp
	//2. doesn't work with view write the text of blah.jsp
	//3. works if had a jsp wrapper page with <jsp:include page="/WEB-INF/mock/${param.tag}"/> ?tag=blah.jsp
	
	@RequestMapping("/*.mock")
	ModelAndView render(HttpServletRequest request)
	{
		String viewName = ControllerUtils.extractStringFromUriAfterLastMatch(request.getRequestURI(), "/");
		Map model = null;
		GroovyShell shell = new GroovyShell();
		String currentAbsoluteDirectory = request.getSession().getServletContext().getRealPath("");
		String mockAbsolutePath = currentAbsoluteDirectory + "/WEB-INF/mock/" + viewName;
		try {
			model = (Map) shell.evaluate(new File(mockAbsolutePath));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		model.putAll(ControllerUtils.getValuesFromParameters(request.getParameterMap()));
		ModelAndView mav = new ModelAndView("jsp/"+viewName.replace(".mock", ""), model);
		return mav;
	}
}