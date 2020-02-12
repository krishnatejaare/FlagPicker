package com.asm.FlagPicker.service;

import com.asm.FlagPicker.controller.FlagPickerController;
import com.asm.FlagPicker.model.Continent;
import com.asm.FlagPicker.model.CountryFlag;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class FlagPickerService {

	private static Logger logger = Logger.getLogger(FlagPickerService.class);
	public static FlagPickerController flagPickerController;

	public FlagPickerService() {
		initFlagPickerController();
	}

	private void initFlagPickerController() {
		flagPickerController = FlagPickerController.getInstance();
		flagPickerController.initRefs(this);
	}

	private static List<Continent> list = new ArrayList<>();

	public List<Continent> getContinents()
	{
		logger.debug("Begin Service getContinentList()");
		try
		{
			if (list == null || list.isEmpty() )
			{
			    list = fileReader();
			}
			logger.debug("Continents : " + list);
		}
		catch(Exception ex)
		{
			logger.debug("Exception :: ", ex);
		}

		return list;
	}

	private List<Continent> fileReader()
	{
		List<Continent> continentList = new ArrayList<>();
		try {
	        Resource resource = new ClassPathResource("continents.json");
	        InputStream resourceInputStream = resource.getInputStream();
	        if (resourceInputStream == null)
	        {
	            logger.debug("JSON File could not be loaded.");
	        }
	        else
	        {
	     		ObjectMapper objectMapper = new ObjectMapper();
	     		try {
	     	        TypeFactory typeFactory = objectMapper.getTypeFactory();
	     	        CollectionType collectionType = typeFactory.constructCollectionType(
	     	                                            List.class, Continent.class);
	     	        continentList = objectMapper.readValue(resourceInputStream, collectionType);
	     	        logger.debug("Converted JSON to Object\n"+continentList);

	     	    } catch (IOException exception) {
	     	        logger.debug("Exception while Parsing JSON", exception);
	     	    }

	        }

	    } catch (Exception e) {
	        logger.debug("JSON File could not be loaded!");
	    }

		return continentList;
	}

	public List<Continent> searchContinent(String searchString)
	{
		List<Continent> filteredList = new ArrayList<>();
		try {
				if ( list == null || list.isEmpty() )
				{
					list = getContinents();
				}

				filteredList =
						list.stream()
						   .filter(continent -> continent.getContinent().toLowerCase().contains(searchString.toLowerCase()))
						   .collect(Collectors.toList());
			} catch (Exception e) {
				e.printStackTrace();
			}
		return filteredList;
	}

	public List<CountryFlag> getCountriesForContinent(String continentName)
	{
		List<CountryFlag> countriesList = new ArrayList<>();
		try {
			if ( list == null || list.isEmpty() )
			{
				list = getContinents();
			}
			Continent filteredContinent = list.stream()
					.filter(continent -> continent.getContinent().toLowerCase().contains(continentName.toLowerCase())).findAny().orElse(null);
			if (filteredContinent!=null) {
				countriesList = filteredContinent.getCountries();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countriesList;
	}


}
