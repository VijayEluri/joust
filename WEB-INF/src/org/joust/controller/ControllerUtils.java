package org.joust.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class ControllerUtils
{
	public static Map getValuesFromParameters(Map parameterMap)
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