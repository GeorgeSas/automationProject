package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class ProductPage extends PageObject {

	@FindBy(css = "a.select2-choice")
	private WebElementFacade quantityButton;

	@FindBy(css = "li[id*='ui-select-choices-row']")
	private List<WebElement> quantityOptionsList;

	@FindBy(css = "button.button-primary.button-add-to-cart")
	private WebElementFacade addToCartButton;

	public void click_quantityButton() {
		quantityButton.waitUntilPresent();
		quantityButton.click();
	}

	public void select_quantity(int n) {
		for (WebElement option : quantityOptionsList) {
			if (Integer.parseInt(option.findElement(By.cssSelector("span")).getText()) == n) {
				option.click();
				break;
			}
		}
	}

	public void click_addToCartButton() {
		addToCartButton.waitUntilPresent();
		addToCartButton.click();
	}
}