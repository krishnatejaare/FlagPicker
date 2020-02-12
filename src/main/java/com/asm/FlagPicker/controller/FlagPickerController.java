package com.asm.FlagPicker.controller;

import com.asm.FlagPicker.service.FlagPickerService;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import com.asm.FlagPicker.model.Continent;
import com.asm.FlagPicker.model.CountryFlag;
@Controller
public class FlagPickerController {

	private static Logger logger = Logger.getLogger(FlagPickerController.class);
	private static FlagPickerController INSTANCE;
	private static FlagPickerService flagPickerService;

	/**
	 * Getting the instance of FlagPickerController
	 */
	public static FlagPickerController getInstance() {
		if (INSTANCE == null) {
			synchronized (FlagPickerController.class) {
				if (INSTANCE == null) {
					logger.info("FlagPickerController instance created");
					INSTANCE = new FlagPickerController();
				}
			}
		}
		return INSTANCE;
	}
	/**
	 * Passing the FlagPickerService reference
	 */
	public static void initRefs(FlagPickerService flagPickerServiceRef){
		flagPickerService = flagPickerServiceRef;
  }

	@Autowired

	@GetMapping(path="/")
	public String index()
	{
		logger.debug("Begin Controller index()");
		return "index";
	}

	/**
	 * Rest api endpoint for getting the list of Continents
	 */
	@GetMapping(path="/continents")
	@ResponseBody
	public List<Continent> getAllContinents(ModelMap model)
	{
		List<Continent> allContinents = flagPickerService.getContinents();
		model.put("continents", allContinents);
		return allContinents;
	}

	/**
	 * Rest api endpoint for searching the continent in the list of Continents
	 */
	@GetMapping(path="/continents/{search}")
	@ResponseBody
	public List<Continent> searchContinents(@PathVariable String search)
	{
		return flagPickerService.searchContinent(search);
	}

	/**
	 * Rest api endpoint for getting the countries of a Continent
	 */
	@GetMapping(path="/continent/{continentName}/countries")
	@ResponseBody
	public List<CountryFlag> getCountriesForContinent(@PathVariable String continentName)
	{
		return flagPickerService.getCountriesForContinent(continentName);
	}

}
