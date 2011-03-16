package org.joust.controller;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import java.io.File;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.AbstractView;


public class TagTestView extends AbstractView
{
	private String viewName;

	public TagTestView(Object value)
	{
		this.viewName = String.valueOf(value);
	}

	@Override
	protected void renderMergedOutputModel(Map map, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String currentAbsoluteDirectory = request.getSession().getServletContext().getRealPath("");
		String mockDirectory = currentAbsoluteDirectory + "/WEB-INF/mock/";
		String fileReaderAbsolutePath = mockDirectory + "FileReader.groovy";
		String tagTestAbsolutePath = mockDirectory + this.viewName;
		ClassLoader parent = getClass().getClassLoader();
		GroovyClassLoader loader = new GroovyClassLoader(parent);
		Class groovyClass = loader.parseClass(new File(fileReaderAbsolutePath));
		GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
		Object[] args = { tagTestAbsolutePath };
		String jspText = String.valueOf(groovyObject.invokeMethod("getText", args));
		response.getOutputStream().write(jspText.getBytes());
	}
}