package com.dota.chinanaive.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dota.chinanaive.entity.HeroRecord;
import com.dota.chinanaive.entity.MatchHistory;
import com.dota.chinanaive.entity.MatchHistoryResult;
import com.dota.chinanaive.entity.MatchHistoryResult.Result.Match;
import com.dota.chinanaive.service.DataService;
import com.dota.chinanaive.utils.DataConvertUtils;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
  private DataService dataSrvc;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/")
	public ModelAndView  home() {
		logger.info("enter home");
		ModelAndView mv = new ModelAndView("home");
		List<String> first = new ArrayList<String>();
		List<String> second = new ArrayList<String>();
		first.add("88");//TS
		second.add("8");//SF
		List<HeroRecord> hrs = dataSrvc.match(true, first, second);
		return mv;
	}
	
}
