package com.features.search;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.steps.serenity.CartSteps;
import com.steps.serenity.NavigationSteps;
import com.steps.serenity.ProductSteps;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class AddToCartTest {

	@Managed(uniqueSession = true)
	public WebDriver webdriver;

	@Steps
	public NavigationSteps navigationSteps;

	@Steps
	public ProductSteps productSteps;

	@Steps
	public CartSteps cartSteps;

	@Test
	public void add_to_cart_using_quick_view_and_product_view() {
		navigationSteps.open_home_page_and_maximize_window();
		navigationSteps.click_the_shop_button_from_home_page();

		int qQV = 2; // quantity Quick View
		int qPV = 3; // quantity Product View
		int productIndex = 1;

		navigationSteps.open_quick_view_for_nTH_product(productIndex);
		productSteps.select_quantity(qQV);
		productSteps.add_to_cart();

		navigationSteps.click_on_curtain();

		navigationSteps.open_nTH_product(productIndex);
		productSteps.select_quantity(qPV);
		productSteps.add_to_cart();

		cartSteps.click_view_cart();

		cartSteps.check_that_the_number_of_product_types_in_cart_is(1);

		cartSteps.check_that_the_total_number_of_products_in_cart_is(qQV + qPV);

		navigationSteps.waitABit(2000); // Used during development to be able to view last action
	}
	
	//@Test
	public void remove_products_from_cart_until_empty() {
		navigationSteps.open_home_page_and_maximize_window();
		navigationSteps.click_the_shop_button_from_home_page();
		
		navigationSteps.open_quick_view_for_nTH_product(0);
		productSteps.add_to_cart();
		navigationSteps.click_on_curtain();
		
		navigationSteps.open_quick_view_for_nTH_product(1);
		productSteps.add_to_cart();
		navigationSteps.click_on_curtain();
		
		navigationSteps.open_quick_view_for_nTH_product(2);
		productSteps.add_to_cart();
		
		cartSteps.click_view_cart();
		
		// remove products
		cartSteps.remove_nTH_product_from_cart(0);
		cartSteps.remove_nTH_product_from_cart(0);
		cartSteps.remove_nTH_product_from_cart(0);

		//cartSteps.remove_all_product_from_cart();
		
		cartSteps.check_cart_is_empty_element();
		cartSteps.check_cart_subtotal_is("$0.00");

		navigationSteps.waitABit(2000); // Used during development to be able to view last action
	}

	//@Test
	public void add_to_cart_and_check_total_price() {
		navigationSteps.open_home_page_and_maximize_window();
		navigationSteps.click_the_shop_button_from_home_page();
		
		int productIndex = 1;
		int productQuantity = 5;

		navigationSteps.open_quick_view_for_nTH_product(productIndex);
		
		//TO-DO
		//float price = productSteps.getPrice();
		
		productSteps.select_quantity(productQuantity);
		productSteps.add_to_cart();
		cartSteps.click_view_cart();

		cartSteps.check_that_the_number_of_product_types_in_cart_is(1);

		//TO-DO
		//cartSteps.check_that_the_total_price_in_cart_is(productQuantity * price);

		navigationSteps.waitABit(2000); // Used during development to be able to view last action
	}
}