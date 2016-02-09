package com.steps.serenity;

import com.pages.FramePage;
import com.pages.ProductPage;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class ProductSteps extends ScenarioSteps {

	private static final long serialVersionUID = 1L;

	ProductPage productPage;
	FramePage framePage;

	@Step
	public void select_quantity(int n) {
		framePage.switch_to_quick_view_and_product_view_frame();
		productPage.click_quantityButton();
		productPage.select_quantity(n);
		framePage.switch_to_default_content();
	}

	@Step
	public void add_to_cart() {
		framePage.switch_to_quick_view_and_product_view_frame();
		productPage.click_addToCartButton();
		framePage.switch_to_default_content();
	}

}