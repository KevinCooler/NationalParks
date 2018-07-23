package com.techelevator.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.DAO.ParkDAO;
import com.techelevator.DAO.SurveyDAO;
import com.techelevator.DAO.WeatherDAO;
import com.techelevator.Object.Park;
import com.techelevator.Object.Survey;
import com.techelevator.Object.Weather;

@SessionAttributes("celsius")

@Controller
public class NPGeekController {
	
	@Autowired
	private ParkDAO parkDAO;
	
	@Autowired 
	private WeatherDAO weatherDAO;
	
	@Autowired 
	private SurveyDAO surveyDAO;
	
	@RequestMapping("/")
	public String displayHomePage(ModelMap map) {
		map.addAttribute("parks", parkDAO.getParkList());
		return "homePage";
	}
	
	@RequestMapping("/parkDetail")
	public String displayParkDetail(@RequestParam(required = false) String convert, 
			@RequestParam String parkCode, ModelMap map) {
		if(!map.containsAttribute("celsius"))
			map.addAttribute("celsius", false);
		
		if(convert != null)
			map.addAttribute("celsius", Boolean.parseBoolean(convert));
		
		map.addAttribute("park", parkDAO.getParkByID(parkCode));
		List<Weather> weatherForecast = weatherDAO.getWeatherListByParkCode(parkCode, (Boolean)map.get("celsius"));
		map.addAttribute("weatherForecast", weatherForecast);
		
		return "parkDetail";
	}
	
	@RequestMapping("/survey")
	public String displaySurveyForm(Model model, ModelMap map) {
		if(!model.containsAttribute("survey")) {
			model.addAttribute("survey", new Survey());
		}
		map.addAttribute("parks", parkDAO.getParkList());
		return "surveyForm";
	}
	
	@RequestMapping(path="/submitSurvey", method=RequestMethod.POST)
	public String submitSurvey(@Valid@ModelAttribute("survey") Survey survey, BindingResult result, ModelMap map) {
		if(result.hasErrors()) {
			map.addAttribute("parks", parkDAO.getParkList());
			return "surveyForm";
		}
		surveyDAO.save(survey);
		return "redirect: surveyResults";
	}
	
	@RequestMapping("/surveyResults")
	public String displaySurveyResults(ModelMap map) {
		Map<String, Integer> results = surveyDAO.getSurveyCounts();
		Map<Park, Integer> fullResults = new LinkedHashMap<Park, Integer>();
		
		for (String parkCode : results.keySet())
			fullResults.put(parkDAO.getParkByID(parkCode), results.get(parkCode));
		
		map.addAttribute("results", fullResults);
		
		return "surveyResults";
	}
}