package com.steps.serenity;

import java.io.IOException;

import javax.mail.MessagingException;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;

import com.helpers.IMAPHelper;
import com.pages.CustomerCarePage;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.steps.ScenarioSteps;

public class CustomerCareSteps extends ScenarioSteps {

	private static final long serialVersionUID = 1L;

	CustomerCarePage customerCarePage;
	
	IMAPHelper iMAPHelper = new IMAPHelper();
	int unreadEmails;

	@StepGroup
	public void fill_in_form(String name, String email, String subject, String message) {

		JavascriptExecutor jse = (JavascriptExecutor) getDriver();
		jse.executeScript("window.scrollBy(0,250)", "");

		fill_in_name(name);
		fill_in_email(email);
		fill_in_subject(subject);
		fill_in_message(message);
		press_sendButton();
	}

	@Step
	public void fill_in_name(String name) {
		customerCarePage.type_name(name);
	}

	@Step
	public void fill_in_email(String email) {
		customerCarePage.type_email(email);
	}

	@Step
	public void fill_in_subject(String subject) {
		customerCarePage.type_subject(subject);
	}

	@Step
	public void fill_in_message(String message) {
		customerCarePage.type_message(message);
	}

	@Step
	public void press_sendButton() {
		customerCarePage.click_sendButton();
	}

	@Step
	public void check_return_message_is(String response) {
		System.out.println(customerCarePage.getResponseMessage());
		Assert.assertTrue(response.contentEquals(customerCarePage.getResponseMessage()));
	}
	
	@Step 
	public void connect_and_get_unread_emails() throws MessagingException{
		iMAPHelper.connectToIMAP("imap.gmail.com", "scoalainformalait@gmail.com", "1nf0rmala");
		unreadEmails = iMAPHelper.getUnreadEmails();
	}
	
	@Step
	public void check_email_subject_is(String expectedSubject) throws Exception {
		
		boolean subjectFlag = false;
	
		for (int i = 0; i < unreadEmails; i++) {
			String actualSubject = iMAPHelper.getEmailSubject(i);
			if (expectedSubject.contentEquals(actualSubject)){
				subjectFlag = true;
				break;
			}
		}
		Assert.assertTrue("Could not find unread email with expected subject!!!", subjectFlag);
	}

	public void check_email_body_contains(String expectedContent) throws MessagingException, IOException {
		boolean bodyFlag = false;
	
		for (int i = 0; i < unreadEmails; i++) {
			String actualBody = iMAPHelper.getEmailBody(i);
			if (actualBody.contains(expectedContent)){
				bodyFlag = true;
				break;
			}
		}
		Assert.assertTrue("Could not find unread email with body containing string!!!", bodyFlag);
	}

	@Step
	public void no_unread_emails_expected() {
		Assert.assertEquals(0, unreadEmails);
		
	}

}