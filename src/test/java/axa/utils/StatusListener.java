/*
 * Copyright (c) 2014 - 2017.  Element34 Solutions - All Rights Reserved
 * Unauthorized copying and redistribution of this file or parts thereof,
 * via any medium is strictly prohibited without explicit consent of Element34 Solutions GmbH.
 */

package axa.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.lang.reflect.Field;

import static axa.utils.Helpers.screenshot;

public class StatusListener implements ITestListener {

	public void onFinish(ITestContext arg0) {
		System.out.println("[FINISHED]");

	}

	public void onStart(ITestContext ctx) {
		System.out.println("[STARTING TESTS]");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	}

	public void onTestFailure(ITestResult result) {

			Object currentClass = result.getInstance();
			WebDriver webDriver = ((TestBase)currentClass).getDriver();


		if (webDriver != null)
		{
			System.out.println("[FAILED] - TAKING SCREENSHOT");
			screenshot((RemoteWebDriver) webDriver);
//			screenshot((EventFiringWebDriver) webDriver);

		}

		}

	public void onTestSkipped(ITestResult arg0) {
	}

	public void onTestStart(ITestResult result) {
		System.out.println("[starting] " + result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("PASSED: " + result.getName());
		System.out.println("Duration " + result.getName() +": "  + (result.getEndMillis() - result.getStartMillis()) + " ms");


	}
}
