package com.dota.chinanaive.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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

		return mv;
	}
	
	
	@RequestMapping(value = "/result")
  public ModelAndView  result(final HttpServletRequest request) {
    ModelAndView mv = new ModelAndView("result");
    String rad1 = request.getParameter("rad1");
    String rad2 = request.getParameter("rad2");
    String dire1 = request.getParameter("dire1");
    String dire2 = request.getParameter("dire2");
    
    String aid = request.getParameter("aid");
    
    List<String> first = new ArrayList<String>();
    List<String> second = new ArrayList<String>();
    if(StringUtils.isNotEmpty(rad1)) {
      first.add(rad1);
    }
    if(StringUtils.isNotEmpty(rad2)) {
      first.add(rad2);
    }
    if(StringUtils.isNotEmpty(dire1)) {
      second.add(dire1);
    }
    if(StringUtils.isNotEmpty(dire2)) {
      second.add(dire2);
    }
    List<HeroRecord> hrs = dataSrvc.match(true, first, second);
    List<HeroRecord> userHistory = 
        DataConvertUtils.getHeroFreqFromMH(Long.valueOf(aid), 
            dataSrvc.getMatchHistory("&account_id=" + aid));
    dataSrvc.calculateScore(hrs, userHistory);
    return mv;
  }
}
