package com.mintest.board.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mintest.board.service.UserService;
import com.mintest.board.vo.UserVo;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/* 초기화면 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("초기 화면");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	/* 비동기 방식 현재시간 새로고침 */
	/*ajax의 한글 깨짐 현상 해결 방법 : produces = "application/text; charset=utf8" ,
	  json 이면 produces = "application/sjon; charset=utf8" */
	@ResponseBody
	@RequestMapping(value = "/curTime", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public String curTime(Locale locale) {
		logger.info("현재시간 비동기방식 새로고침");
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		return formattedDate;
	}
	
	
}
