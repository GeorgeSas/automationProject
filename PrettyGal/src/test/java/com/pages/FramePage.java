package com.pages;

import net.serenitybdd.core.annotations.findby.By;
import net.thucydides.core.pages.PageObject;

public class FramePage extends PageObject {

	// shop
	public void switch_to_shop_frame() {
		getDriver().switchTo().frame(getDriver().findElement(By.cssSelector("iframe[src*='storefront/gallery']")));
	}

	// quick view and product view
	public void switch_to_quick_view_and_product_view_frame() {
		getDriver().switchTo().frame(getDriver().findElement(By.cssSelector("iframe[src*='storefront/product/']")));
	}

	// cart widget
	public void switch_to_cart_widget_frame() {
		getDriver().switchTo().frame(getDriver().findElement(By.cssSelector("iframe[src*='/storefront/cartwidgetPopup?cacheKiller']")));
	}

	// cart
	public void switch_to_cart_frame() {
		getDriver().switchTo().frame(getDriver().findElement(By.cssSelector("iframe[src*='/storefront/cart?cacheKiller']")));
	}

	// default content
	public void switch_to_default_content() {
		getDriver().switchTo().defaultContent();
	}
}