package com.msdb5.mvc;


import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MSDB5WebController {
	
	@ModelAttribute("elencoFormati")
	public List<String> getElencoFormati() {

		List<String> elencoFormati = new ArrayList<String>();

		return elencoFormati;
	}

	@RequestMapping(value = {"/", "back"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String showIndexPage(Model model) {

		return "index";
	}
}