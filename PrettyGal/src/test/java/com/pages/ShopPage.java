package com.pages;

import java.util.List;

import org.openqa.selenium.interactions.Actions;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class ShopPage extends PageObject {

	@FindBy(css = "a > img.media-item.active")
	private List<WebElementFacade> productImageList;

	@FindBy(css = "a.action.quickview")
	private List<WebElementFacade> quickViewList;

	@FindBy(css = ".curtain")
	private WebElementFacade curtain;

	public void move_mouse_over_nTH_product_and_click_quickView(int n) {
		Actions builder = new Actions(getDriver());
		builder.moveToElement(productImageList.get(n));
		builder.perform();

		quickViewList.get(n).click();
	}

	public void click_nTH_product(int n) {
		productImageList.get(n).click();
	}

	public void click_curtain() {
		curtain.click();
	}

}