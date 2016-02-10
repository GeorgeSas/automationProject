package com.features.search;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.steps.serenity.DBSteps;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class DataTest {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public DBSteps dbSteps;

    @Test
    public void test_user_data() throws ClassNotFoundException, SQLException {
    	dbSteps.connect_to_database();
    	dbSteps.create_user("testUser", "testPassword", "test@mail.com");
    	
    	// TODO: other steps ...
    }

} 