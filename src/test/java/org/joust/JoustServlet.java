package org.joust;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yaml.snakeyaml.Yaml;

public class JoustServlet extends HttpServlet {
	private static final long serialVersionUID = -4701849850769353437L;

	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String yamls = request.getParameter("yaml");
		if (yamls != null) {
			String[] yamlFiles = yamls.split(",");
			for (String yamlFile : yamlFiles) {
				Map<String, Object> data = getData(yamlFile);
				for (String key : data.keySet()) {
					request.setAttribute(key, data.get(key));
				}
			}
		}
		String jspPath = request.getRequestURI().replace("/joust/", "/");
		request.setAttribute("viewName", jspPath);
		request.getRequestDispatcher("/joustLayout.jsp").forward(request, response);
	}

	public static Map<String, Object> getData(String yamlFile) {
		InputStream source = JoustServlet.class.getResourceAsStream("/yamls/" + yamlFile);
		Map<String, Object> data = new HashMap();
		if (source != null) {
			data = (Map<String, Object>) new Yaml().load(source);
		}
		return data;
	}
}