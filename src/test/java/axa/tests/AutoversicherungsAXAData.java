package axa.tests;

import axa.pages.AngabenPage;
import axa.pages.FahrzeugsuchePage;
import axa.pages.PraemiePage;
import axa.utils.ExcelAdapter;
import axa.utils.StatusListener;
import axa.utils.TestBase;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

@Listeners(StatusListener.class)
public class AutoversicherungsAXAData extends TestBase{

    @Test(dataProvider="datamap", dataProviderClass = ExcelAdapter.class)
    public void newTest(Map<Object, Object> map) throws InterruptedException {

        RemoteWebDriver driver = (RemoteWebDriver) getDriver();
//        EventFiringWebDriver driver = (EventFiringWebDriver) getDriver();


        //Fahrzeugsuche
        FahrzeugsuchePage fahrzeugsuche = new FahrzeugsuchePage(driver);
        fahrzeugsuche.loadPage(driver);
        fahrzeugsuche.selectInv((String) map.get("1.INV"));
        fahrzeugsuche.selectMarke((String) map.get("Marke"));
        fahrzeugsuche.selectTreibstoff((String) map.get("Treibstoff"));
        fahrzeugsuche.selectModell(driver, (String) map.get("Modell"));
        fahrzeugsuche.selectSchaltung((String) map.get("Schaltung"), driver);
        fahrzeugsuche.selectPS((String) map.get("ps"));
        fahrzeugsuche.clickSearchButton();

        fahrzeugsuche.selectSpecificModel(driver, (String) map.get("Typbz"));

        //Angaben
        AngabenPage angaben = new AngabenPage(driver);
        angaben.setZubehoer((String) map.get("Zubehör"));
        angaben.setKontrollschild((String) map.get("bes.Kontrollschild"));
        angaben.setLeasing((String) map.get("Leasing"));
        angaben.enterKilometer((String) map.get("Kilom/Jahr"));
        angaben.selectPurchaseYear((String) map.get("Kaufjahr"));
        angaben.setParkschaden((String) map.get("vorh. Parkschaden?"));
        angaben.setNotbremsassistent((String) map.get("notbrems"));
        angaben.setGebDatum((String) map.get("HFF Geb."));
        angaben.selectNationality((String) map.get("HFF Nati."));
        angaben.enterPLZ((String) map.get("HFF PLZ"));
        angaben.setGeschlecht((String) map.get("HFF Geschl."));
        angaben.setEntzug((String) map.get("HFF Entzug"));
        angaben.setBisherigerVersicherer((String) map.get("HFF Vorvers."), (String) map.get("HFF Vorvers.Name"), driver);
        angaben.setKuendigung((String) map.get("HFF Vorvers.Künd"), driver);
        angaben.setSchaden(
                driver,
                (String) map.get("Schäden letzte 5 J.?"),
                (String) map.get("haftPflichtSchaden"),
                (String) map.get("schadenJahr"),
                (String) map.get("diebstahl"),
                (String) map.get("parkschaden"),
                (String) map.get("kollisionsschaden"),
                (String) map.get("kollisionsSchadenJahr"));

        //Vertragsdetails
        angaben.setVertragsdetails(
                (String) map.get("Vers. ist"),
                (String) map.get("VN Geb."),
                (String) map.get("VN Geschl."),
                (String) map.get("VN Nati."),
                (String) map.get("VN PLZ"),
                (String) map.get("VN Künd."),
                (String) map.get("Vers.beginn"),
                (String) map.get("Dauer")
        );


        angaben.clickWeiter(driver);

        //Prämien
        PraemiePage praemie = new PraemiePage(driver);
        praemie.selectKasko(
                (String) map.get("Kasko"),
                (String) map.get("SB ohne Koll"),
                (String) map.get("SB Koll"),
                (String) map.get("sbTeilkasko"));

        praemie.selectErgaenzungen(
                (String) map.get("Mobilität"),
                (String) map.get("Mitgef.Sachen"),
                (String) map.get("Parkschaden"),
                (String) map.get("Unfall"),
                (String) map.get("Bonusschutz"),
                (String) map.get("Recorder"),
                driver);


        Thread.sleep(2500);
        Assert.assertEquals(praemie.getBasicPraemie(), (String) map.get("Prämie"));

//        System.out.println("Video URL - http://vm-106.element34.net/videos/" + (RemoteWebDriver)driver.getSessionId() + ".mp4");
        Thread.sleep(5000);

    }
}
