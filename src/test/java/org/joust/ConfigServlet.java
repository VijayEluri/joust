package org.joust;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.common.io.Files;

public class ConfigServlet extends HttpServlet {
	private static final long serialVersionUID = -2196949632698566948L;
	private static Timer timer;
	private static String JSP_FILEPATH = "";
	private static String TAGS_FILEPATH = "";
	private static String HEAD_URL = "";
	public static String HEAD_HTML = "";
	private static String JETTY_HOME = "";
	private static String YAML_FILEPATH = "C:/code/joust/src/test/resources/yamls/people.yaml";

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		scheduleSync();
		request.setAttribute("jsp", JSP_FILEPATH);
		request.setAttribute("tags", TAGS_FILEPATH);
		request.setAttribute("head", HEAD_URL);
		request.setAttribute("yamlFilepath", YAML_FILEPATH);
		request.getRequestDispatcher("/config.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		JSP_FILEPATH = toUri(request.getParameter("jsp"));
		TAGS_FILEPATH = toUri(request.getParameter("tags"));
		String yaml = request.getParameter("yaml");
		String head = request.getParameter("head");
		if (!HEAD_URL.equals(head)) {
			setHead(head);
		}
		doGet(request, response);
	}

	private String toUri(String filePath) {
		filePath = filePath.replace("\\\\", "/").replace("\\", "/");
		return new File(filePath).toURI().toString();
	}

	void setHead(String head) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		HEAD_URL = head;
		if (HEAD_URL.trim().length() == 0) {
			HEAD_HTML = "";
		}
		WebClient webClient = new WebClient();
		HtmlPage page = webClient.getPage(HEAD_URL);
		DomNodeList<HtmlElement> doms = page.getElementsByTagName("head");
		for (HtmlElement element : doms.get(0).getElementsByTagName("link")) {
			String urlAttr = "href";
			if (element.hasAttribute(urlAttr)) {
				String path = toAbsolutePath(element.getAttribute(urlAttr));
				System.out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\""+path+"\" />");
			}
		}
		for (HtmlElement element : doms.get(0).getElementsByTagName("script")) {
			String urlAttr = "src";
			if (element.hasAttribute(urlAttr)) {
				String path = toAbsolutePath(element.getAttribute(urlAttr));
				System.out.println("<script src=\""+path+"\"></script>");
			}
		}
	}

	private String toAbsolutePath(String text) {
		if (text.startsWith(".")) {
			text = text.substring(1);
		}
		if (text.startsWith("/")) {
			text = text.substring(1);
		}
		if (!text.startsWith("http")) {
			text = HEAD_URL + "/" + text;
		}
		return text;
	}

	private void scheduleSync() {
		if (timer == null) {
			timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {
				public void run() {
					try {
						Files.copy(new File(JSP_FILEPATH), new File(JETTY_HOME));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}, 1000, 1000);
		}
	}
}