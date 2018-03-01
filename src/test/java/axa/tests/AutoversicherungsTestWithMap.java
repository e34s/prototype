package axa.tests;

import axa.pages.AngabenPage;
import axa.pages.FahrzeugsuchePage;
import axa.pages.PraemiePage;
import axa.utils.ExcelAdapter;
import axa.utils.TestBase;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class AutoversicherungsTestWithMap extends TestBase{

    @Test(dataProvider="datamap", dataProviderClass = ExcelAdapter.class)
    public void newTest(Map<Object, Object> map) throws InterruptedException {

        RemoteWebDriver driver = (RemoteWebDriver) getDriver();
        FahrzeugsuchePage fahrzeugsuche = new FahrzeugsuchePage(driver);
        fahrzeugsuche.loadPage(driver);


        fahrzeugsuche.selectInv((String) map.get("inv"));
        fahrzeugsuche.selectMarke((String) map.get("marke"));
        fahrzeugsuche.selectTreibstoff((String) map.get("treibstoff"));
        fahrzeugsuche.selectModell(driver, (String) map.get("modell"));
        fahrzeugsuche.selectSchaltung((String) map.get("schaltung"));
        fahrzeugsuche.selectPS((String) map.get("ps"));
        fahrzeugsuche.clickSearchButton();

        fahrzeugsuche.selectSpecificModel(driver, (String) map.get("specificModel"));

        AngabenPage angaben = new AngabenPage(driver);
        angaben.setZubehoer((String) map.get("zusatzausruestung"));
        angaben.setKontrollschild((String) map.get("besKontrollschild"));
        angaben.setLeasing((String) map.get("leasing"));
        angaben.enterKilometer((String) map.get("kilometer"));
        angaben.selectPurchaseYear((String) map.get("kaufjahr"));
        angaben.setParkschaden((String) map.get("parkschaden"));
        angaben.setNotbremsassistent((String) map.get("notbrems"));
        angaben.setGebDatum((String) map.get("gebDatum"));
        angaben.selectNationality((String) map.get("nationalitaet"));
        angaben.enterPLZ((String) map.get("plz"));
        angaben.setGeschlecht((String) map.get("geschlecht"));
        angaben.setEntzug((String) map.get("entzug"));
        angaben.setBisherigerVersicherer((String) map.get("bisherigevers"), (String) map.get("versicherer"), driver);
        angaben.setKuendigung((String) map.get("kuendigung"));
        angaben.setSchaden(
                (String) map.get("schaden5Jahre"),
                (String) map.get("haftPflichtSchaden"),
                (String) map.get("schadenJahr"),
                (String) map.get("diebstahl"),
                (String) map.get("parkschaden"),
                (String) map.get("kollisionsschaden"),
                (String) map.get("kollisionsSchadenJahr"));


        angaben.clickWeiter();

        PraemiePage praemie = new PraemiePage(driver);
        praemie.selectKasko(
                (String) map.get("kasko"),
                (String) map.get("sbAusserKollision"),
                (String) map.get("sbKollision"),
                (String) map.get("sbTeilkasko"));

        praemie.selectErgaenzungen(
                (String) map.get("mobility"),
                (String) map.get("mitgefuehrteSachen"),
                (String) map.get("unfallversicherung"),
                (String) map.get("bonusschutz"),
                (String) map.get("crashrecorder"),
                driver);


        Thread.sleep(2500);
        Assert.assertEquals(praemie.getBasicPraemie(), (String) map.get("sollBasic"));
        Assert.assertEquals(praemie.getBasicCompact(), (String) map.get("sollCompact"));

        System.out.println("Video URL - http://vm-106.element34.net/videos/" + driver.getSessionId() + ".mp4");
        Thread.sleep(5000);

    }
}
