package com.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class CustomerCarePage extends PageObject {

	@FindBy(css = "input[name='Name']")
	private WebElementFacade nume;

	@FindBy(css = "input[name='Email']")
	private WebElementFacade email;

	@FindBy(css = "input[name='Subject']")
	private WebElementFacade subject;

	@FindBy(css = "textarea[name='Message']")
	private WebElementFacade message;

	@FindBy(css = "button.s20submit")
	private WebElementFacade sendButton;

	@FindBy(css = "div.s20wrapper span")
	private WebElementFacade returnMessage;

	public void type_name(String value) {
		nume.waitUntilVisible();
		nume.type(value);
	}

	public void type_email(String value) {
		email.type(value);
	}

	public void type_subject(String value) {
		subject.type(value);
	}

	public void type_message(String value) {
		message.type(value);
	}

	public void click_sendButton() {
		sendButton.click();
	}

	public String getResponseMessage() {
		//returnMessage.waitUntilVisible();
		waitABit(2000);
		return returnMessage.getTextValue();
	}

}