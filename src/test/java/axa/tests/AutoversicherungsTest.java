/*
 * Copyright (c) 2014 - 2018.  Element34 Solutions - All Rights Reserved
 * Unauthorized copying and redistribution of this file or parts thereof,
 * via any medium is strictly prohibited without explicit consent of Element34 Solutions GmbH.
 */

package axa.tests;

import axa.pages.PraemiePage;
import axa.utils.ExcelAdapter;
import axa.pages.AngabenPage;
import axa.pages.FahrzeugsuchePage;
import axa.utils.StatusListener;
import axa.utils.TestBase;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.MalformedURLException;


@Listeners(StatusListener.class)
public class AutoversicherungsTest extends TestBase {


	@Test(dataProvider="data", dataProviderClass = ExcelAdapter.class, enabled = true)
	public void axaTest(String data_year, String data_month, String data_marke, String data_model,
                        String data_schaltung, String data_treibstoff, String data_ps, String specificModel,
                        String data_zusatzausruestung, String data_besKontrollschild, String data_leasing,
                        String data_kilometer, String data_kaufjahr, String data_parkschaden, String data_notbrems,
                        String data_gebDatum, String data_nationalitaet, String data_plz, String data_geschlecht,
                        String data_entzug, String data_bisherigeVersicherer, String data_versicherer, String data_kuendigung,
                        String data_schaden5Jahre, String data_haftPflichtSchaden, String data_schadenJahr, String data_Diebstahl, String data_parkSchaden, String data_kollisionsSchaden, String data_kollisionsSchadenJahr,
						String data_kasko, String data_selbstbehaltAusserKollision, String data_selbstbehaltKollision, String data_selbstbehaltTeilkasko,
						String data_mobility, String data_mitgefuehrteSachen, String data_unfallVersicherung, String data_bonusSchutz, String data_crashRecorder,
						String data_sollBasic, String data_sollCompact) throws InterruptedException, MalformedURLException {

//		DesiredCapabilities capability = DesiredCapabilities.chrome();
//		capability.setCapability("e34:token", "72aa4d82");
//        capability.setCapability("e34:l_testName", "Selenium Test");
//        capability.setCapability("video", true);
//        RemoteWebDriver driver = new ChromeDriver(capability);

		RemoteWebDriver driver = (RemoteWebDriver) getDriver();

        FahrzeugsuchePage fahrzeugsuche = new FahrzeugsuchePage(driver);
		fahrzeugsuche.loadPage(driver);
		fahrzeugsuche.selectInv(data_year);
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
		angaben.setBisherigerVersicherer(data_bisherigeVersicherer, data_versicherer, driver);
		angaben.setKuendigung(data_kuendigung);
		angaben.setSchaden(data_schaden5Jahre, data_haftPflichtSchaden, data_schadenJahr, data_Diebstahl, data_parkSchaden, data_kollisionsSchaden, data_kollisionsSchadenJahr);
		angaben.clickWeiter();

		PraemiePage praemie = new PraemiePage(driver);
		praemie.selectKasko(data_kasko, data_selbstbehaltAusserKollision, data_selbstbehaltKollision, data_selbstbehaltTeilkasko);

		praemie.selectErgaenzungen(data_mobility, data_mitgefuehrteSachen, data_unfallVersicherung, data_bonusSchutz, data_crashRecorder, driver);

		Thread.sleep(2500);
		Assert.assertEquals(praemie.getBasicPraemie(), data_sollBasic);
		Assert.assertEquals(praemie.getBasicCompact(), data_sollCompact);

		System.out.println("Video URL - http://vm-106.element34.net/videos/" + driver.getSessionId() + ".mp4");
		driver.quit();
	}

}
