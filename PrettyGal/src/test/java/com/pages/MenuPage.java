package com.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

@DefaultUrl("http://gabikis.wix.com/fashion")
public class MenuPage extends PageObject {

	@FindBy(css = "a[href*='customer-care']")
	private WebElementFacade customerCareMenuItem;

	public void click_customerCareMenuItem() {
		customerCareMenuItem.waitUntilVisible();
		customerCareMenuItem.click();
	}
}