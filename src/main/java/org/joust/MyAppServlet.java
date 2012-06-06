package org.joust;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyAppServlet extends HttpServlet {
	private static final long serialVersionUID = 708958264398342411L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("people", "[Real World People]");
		String jspPath = request.getRequestURI().replace("/joust/", "/");
		request.getRequestDispatcher(jspPath).forward(request, response);
	}
}