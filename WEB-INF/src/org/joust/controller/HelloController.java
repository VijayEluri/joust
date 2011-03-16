package org.joust.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.joust.model.PokerModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController
{
	@RequestMapping("/hello.spr")
	public ModelAndView view()
    {
		ModelAndView mav = new ModelAndView("jsp/hello");
		mav.addObject("helloMsg", "Hello from HelloController real data.");
		Map model = new LinkedHashMap();
		ArrayList listOfGames = new ArrayList();
		Map innerModel = new LinkedHashMap();
		innerModel.put("Name", "1234");
		innerModel.put("Link", "Jack of Diamonds");
		innerModel.put("HighScoreName", "Abc");
		innerModel.put("HighScore", "Def");
		PokerModel pokerModel = new PokerModel();
		pokerModel.setCards("Queen, Ace, Jack");
		innerModel.put("Obj", pokerModel);
		listOfGames.add(innerModel);	
		model.put("Games", listOfGames);		
		mav.addObject("model", model);
        return mav;
    }
}