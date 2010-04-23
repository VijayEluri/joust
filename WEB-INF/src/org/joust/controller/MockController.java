package org.joust.controller;

import groovy.lang.GroovyShell;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MockController
{
	@RequestMapping("/*.mock")
	ModelAndView render(HttpServletRequest request)
	{
		String viewName = extractStringFromUriAfterLastMatch(request.getRequestURI(), "/");
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

		model.putAll(getValuesFromParameters(request.getParameterMap()));
		ModelAndView mav = new ModelAndView(viewName.replace(".mock", ""), model);
		return mav;
	}

	Map getValuesFromParameters(Map parameterMap)
	{
		Map newMap = new HashMap();

		Set<Map.Entry<String, String[]>> entrySet = parameterMap.entrySet();
		for (Map.Entry<String, String[]> entry : entrySet) {

			String value = entry.getValue()[0];

			if ((value.equalsIgnoreCase("true")) || (value.equalsIgnoreCase("false"))) {
				newMap.put(entry.getKey(), Boolean.parseBoolean(value));
			}
			else {
				newMap.put(entry.getKey(), value);
			}
		}

		return newMap;
	}

	String extractStringFromUriAfterLastMatch(String uri, String match)
	{
		String extractedText = "";
		String[] tokens = uri.split(match);
		try {
			extractedText = URLDecoder.decode(tokens[tokens.length - 1], "UTF-8");
		}
		catch (UnsupportedEncodingException ignoreAndLetBlowUpLater) {
		}

		return extractedText;
	}
}