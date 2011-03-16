package org.joust.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class TagTestController
{
	@RequestMapping("/*TagTest.mock")
	String render(HttpServletRequest request)
	{
		//		String viewUri = ControllerUtils.extractStringFromUriAfterLastMatch(request.getRequestURI(), "/");
		//		return new TagTestView(viewUri);
		return "redirect:/WEB-INF/mock/buttonTagTest.jsp";
	}
}