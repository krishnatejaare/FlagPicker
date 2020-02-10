package com.asm.FlagPicker.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.asm.FlagPicker.FlagPickerApplication;
import com.asm.FlagPicker.model.Continent;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=FlagPickerApplication.class)
public class FlagPickerServiceTest {

	@Autowired
	FlagPickerService flagPickerService;

	@Test
	public void testGetAllContinents()
	{
		List<Continent> allContinents = flagPickerService.getContinents();
		Assert.assertNotNull(allContinents);
		Assert.assertTrue(allContinents.size() > 0);
	}

	@Test
	public void testSearchContinent()
	{
		String searchString = "eur";
		List<Continent> srchContinents = flagPickerService.searchContinent(searchString);
		Assert.assertNotNull(srchContinents);
		Assert.assertTrue(srchContinents.size() == 1);
	}

	@Test
	public void testSearchContinents()
	{
		String searchString = "a";
		List<Continent> srchContinents = flagPickerService.searchContinent(searchString);
		Assert.assertNotNull(srchContinents);
		Assert.assertTrue(srchContinents.size() == 4);
	}

}
