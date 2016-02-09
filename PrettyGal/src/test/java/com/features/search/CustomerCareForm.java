package com.features.search;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.steps.serenity.CustomerCareSteps;
import com.steps.serenity.NavigationSteps;
import com.steps.serenity.ProductSteps;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src\\test\\resources\\testData\\customerCareData.csv")
public class CustomerCareForm {

	@Managed(uniqueSession = true)
	public WebDriver webdriver;

	String name, email, subject, message, response;

	@Qualifier
	public String getResponse() {
		return response;
	}

	@Steps
	public NavigationSteps navigationSteps;

	@Steps
	public ProductSteps productSteps;

	@Steps
	public CustomerCareSteps customerCareSteps;

	//@Test
	public void customer_care_form_test() throws Exception {
		navigationSteps.open_home_page_and_maximize_window();
		navigationSteps.open_customer_care_from_menu();

		customerCareSteps.fill_in_form(name, email, subject, message);

		customerCareSteps.check_return_message_is(response);
		
		if (response.contains("Message received")){
			navigationSteps.waitABit(7000); // wait 7 seconds
			customerCareSteps.connect_and_get_unread_emails();
			customerCareSteps.check_email_subject_is("New message via your Wix website, from  " + email);
			customerCareSteps.check_email_body_contains(name);
			customerCareSteps.check_email_body_contains(email);
			customerCareSteps.check_email_body_contains(subject);
			customerCareSteps.check_email_body_contains(message);
		}
		else {
			navigationSteps.waitABit(10000); // wait 10 seconds
			customerCareSteps.connect_and_get_unread_emails();
			customerCareSteps.no_unread_emails_expected();
		}

	}

}