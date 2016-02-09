package com.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

@DefaultUrl("http://gabikis.wix.com/fashion")
public class HomePage extends PageObject {

	@FindBy(css = "a#i2epz4bn_1link")
	private WebElementFacade shopNowButton;

	public void click_shopNow_button() {
		shopNowButton.waitUntilVisible();
		shopNowButton.click();
	}
}