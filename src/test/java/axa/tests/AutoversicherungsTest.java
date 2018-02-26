/*
 * Copyright (c) 2014 - 2018.  Element34 Solutions - All Rights Reserved
 * Unauthorized copying and redistribution of this file or parts thereof,
 * via any medium is strictly prohibited without explicit consent of Element34 Solutions GmbH.
 */

package axa.tests;

import axa.utils.ExcelAdapter;
import axa.pages.AngabenPage;
import axa.pages.FahrzeugsuchePage;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


@Test
public class AutoversicherungsTest extends TestBase {


	@Test(dataProvider="data", dataProviderClass = ExcelAdapter.class, enabled = true)
	public void axaTest(String data_year, String data_month, String data_marke, String data_model,
                        String data_schaltung, String data_treibstoff, String data_ps, String specificModel,
                        String data_zusatzausruestung, String data_besKontrollschild, String data_leasing,
                        String data_kilometer, String data_kaufjahr, String data_parkschaden, String data_notbrems,
                        String data_gebDatum, String data_nationalitaet, String data_plz, String data_geschlecht,
                        String data_entzug, String data_bisherigeVersicherer, String data_versicherer, String data_kuendigung,
                        String data_schaden5Jahre) throws InterruptedException, MalformedURLException {

		DesiredCapabilities capability = DesiredCapabilities.chrome();
		capability.setCapability("e34:token", "72aa4d82");
        capability.setCapability("e34:l_testName", "Selenium Test");
        capability.setCapability("video", true);
        RemoteWebDriver driver = new RemoteWebDriver(new URL("https://vm-106.element34.net/wd/hub"), capability);
        //RemoteWebDriver driver = new ChromeDriver(capability);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        FahrzeugsuchePage fahrzeugsuche = new FahrzeugsuchePage(driver);
		fahrzeugsuche.loadPage(driver);
		fahrzeugsuche.selectYear(data_year);
		fahrzeugsuche.selectMonth(data_month);
		fahrzeugsuche.selectMarke(data_marke);
		fahrzeugsuche.selectTreibstoff(data_treibstoff);
		fahrzeugsuche.selectModell(driver, data_model);
		fahrzeugsuche.selectSchaltung(data_schaltung);
		fahrzeugsuche.selectPS(data_ps);
		fahrzeugsuche.clickSearchButton();

		fahrzeugsuche.selectSpecificModel(driver, specificModel);

		AngabenPage angaben = new AngabenPage(driver);
		angaben.setZubehoer(data_zusatzausruestung);
		angaben.setKontrollschild(data_besKontrollschild);
		angaben.setLeasing(data_leasing);
		angaben.enterKilometer(data_kilometer);
		angaben.selectPurchaseYear(data_kaufjahr);
		angaben.setParkschaden(data_parkschaden);
		angaben.setNotbremsassistent(data_notbrems);
		angaben.setGebDatum(data_gebDatum);
		angaben.selectNationality(data_nationalitaet);
		angaben.enterPLZ(data_plz);
		angaben.setGeschlecht(data_geschlecht);
		angaben.setEntzug(data_entzug);
		angaben.setBisherigerVersicherer(data_bisherigeVersicherer, data_versicherer);
		angaben.setKuendigung(data_kuendigung);
		angaben.setSchaden(data_schaden5Jahre);
		angaben.clickWeiter();
		Thread.sleep(3000);
        System.out.println("Video URL - http://vm-106.element34.net/videos/" + driver.getSessionId() + ".mp4");

		driver.quit();
	}

}
