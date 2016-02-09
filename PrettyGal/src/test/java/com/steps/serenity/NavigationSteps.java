package com.steps.serenity;

import com.pages.FramePage;
import com.pages.HomePage;
import com.pages.MenuPage;
import com.pages.ShopPage;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class NavigationSteps extends ScenarioSteps {

	private static final long serialVersionUID = 1L;

	HomePage homePage;
	FramePage framePage;
	ShopPage shopPage;
	MenuPage menuPage;

	@Step
	public void open_home_page_and_maximize_window() {
		homePage.open();
		getDriver().manage().window().maximize();
	}

	@Step
	public void click_the_shop_button_from_home_page() {
		homePage.click_shopNow_button();
	}

	@Step
	public void click_on_curtain() {
		framePage.switch_to_cart_widget_frame();
		shopPage.click_curtain();
		framePage.switch_to_default_content();
	}

	@Step
	public void open_quick_view_for_nTH_product(int n) {
		framePage.switch_to_shop_frame();
		shopPage.move_mouse_over_nTH_product_and_click_quickView(n);
		framePage.switch_to_default_content();
	}

	@Step
	public void open_nTH_product(int n) {
		framePage.switch_to_shop_frame();
		shopPage.click_nTH_product(n);
		framePage.switch_to_default_content();
	}

	@Step
	public void open_customer_care_from_menu() {
		menuPage.click_customerCareMenuItem();
	}

}