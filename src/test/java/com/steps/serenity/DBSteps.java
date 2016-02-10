package com.steps.serenity;

import java.sql.SQLException;

import com.helpers.Constants;
import com.helpers.JDBCHelper;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class DBSteps extends ScenarioSteps {

	private static final long serialVersionUID = 1L;

	JDBCHelper jDBCHelper = new JDBCHelper();

	@Step
	public void connect_to_database() throws ClassNotFoundException, SQLException {
		jDBCHelper.connectToDB(Constants.JDBC_DRIVER, Constants.DB_URL, Constants.USER, Constants.PASS);
	}

	@Step
	public void create_user(String user, String pass, String mail) throws SQLException {
		String query = "INSERT INTO user (`username`,`password`,`email`) VALUES ('" + user + "','" + pass + "','" + mail + "')";
		jDBCHelper.executeSQL(query);
	}

}