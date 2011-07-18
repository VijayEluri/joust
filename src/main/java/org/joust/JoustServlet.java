package org.joust;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JoustServlet extends HttpServlet
{
	private static final long serialVersionUID = 708958264398342411L;

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute("message", "servletToJsp");

		try {
			String viewName = extractStringFromUriAfterLastMatch(request.getRequestURI(), "/");
			//			Map model = null;
			//			GroovyShell shell = new GroovyShell();
			//			String currentAbsoluteDirectory = request.getSession().getServletContext().getRealPath("");
			//			String mockAbsolutePath = currentAbsoluteDirectory + "/WEB-INF/mock/" + viewName;
			//			try {
			//				model = (Map) shell.evaluate(new File(mockAbsolutePath));
			//			}
			//			catch (Exception e) {
			//				throw new RuntimeException(e);
			//			}

			request.getRequestDispatcher(request.getRequestURI().replaceFirst("/joust/", "/")).forward(request, response);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String extractStringFromUriAfterLastMatch(String uri, String match)
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