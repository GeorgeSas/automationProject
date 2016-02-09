package com.pages;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class CartPage extends PageObject {

	@FindBy(css = "footer > a.button-primary")
	private WebElementFacade viewCartButton;

	@FindBy(css = "li.product.has-hr")
	private List<WebElementFacade> productList;

	@FindBy(css = "input.product-quantity-input")
	private List<WebElementFacade> quantityList;

	@FindBy(css = "a.product-remove")
	private List<WebElementFacade> removeItemButtonList;

	@FindBy(css = "div.products-empty")
	private WebElementFacade cartIsEmptyMessage;

	@FindBy(css = "span[data-hook='cart-subtotal']")
	private WebElementFacade cartSubtotal;

	public void click_viewCartButton() {
		viewCartButton.click();
	}

	public int count_types_of_product() {
		return productList.size();
	}

	public void remove_nTH_item(int n) {
		removeItemButtonList.get(n).click();
		waitABit(1000);
	}

	public boolean is_cart_empty_message() {
		return cartIsEmptyMessage.isVisible();
	}

	public String get_cart_subtotal() {
		return cartSubtotal.getText();
	}

	public int get_product_quantity_from_index(int index) {

		WebElementFacade productQuantityElement = quantityList.get(index);
		String productQuantityStr = productQuantityElement.getValue();
		int productQuantity = Integer.parseInt(productQuantityStr);
		return productQuantity;

		// the lines above are equivalent to the following:
		// return Integer.parseInt(quantityList.get(index).getValue());
	}

}