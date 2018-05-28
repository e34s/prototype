package axa.tests;

import axa.pages.AngabenPage;
import axa.pages.FahrzeugsuchePage;
import axa.pages.PraemiePage;
import axa.utils.ExcelAdapter;
import axa.utils.StatusListener;
import axa.utils.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

@Listeners(StatusListener.class)
public class AutoversicherungsAXADataNEW extends TestBase{

    @Test(dataProvider="datamap", dataProviderClass = ExcelAdapter.class)
    public void newTest(Map<Object, Object> map) throws InterruptedException {

        RemoteWebDriver driver = (RemoteWebDriver) getDriver();
        System.out.println("TESTCASE: " + (String)map.get("Testfall")) ;

        //Fahrzeugsuche
        FahrzeugsuchePage fahrzeugsuche = new FahrzeugsuchePage(driver);
        fahrzeugsuche.loadPage((String)map.get("Fahrzeugtyp"), (String)map.get("Rahmenvertrag"), driver);
        fahrzeugsuche.selectInv((String) map.get("Erstinverkehrssetzung"));
        fahrzeugsuche.selectMarke((String) map.get("Marke"));

        //TYPTREIB only for cars
        if (((String) map.get("Fahrzeugtyp")).contentEquals("1.0")) {
            fahrzeugsuche.selectTreibstoff((String) map.get("TYPTREIB"));
        }

        fahrzeugsuche.selectModell(driver, (String) map.get("Modell"));
        fahrzeugsuche.selectSchaltung((String) map.get("Schaltung"), driver);
        fahrzeugsuche.selectPS((String) map.get("Leistung"));
        fahrzeugsuche.clickSearchButton();


        System.out.println("TYP: " + (String) map.get("TYPBZ"));
        fahrzeugsuche.selectSpecificModel(driver, (String) map.get("TYPBZ"));

//        //Angaben
        AngabenPage angaben = new AngabenPage(driver);
        angaben.setZubehoer((String) map.get("Zubehör"));
        angaben.setKontrollschild((String) map.get("Besonderes Kontrollschild"));
        angaben.setLeasing((String) map.get("Leasing"));
        angaben.enterKilometer((String) map.get("Kilometerleistung"));
        angaben.selectPurchaseYear((String) map.get("Kaufjahr"));
        angaben.setParkschaden((String) map.get("vorh. Parkschaden?"));
//        angaben.setNotbremsassistent((String) map.get("notbrems"));
        angaben.setGebDatum((String) map.get("Geburtsdatum häufigster Lenker"));
        angaben.selectNationality((String) map.get("Nationalität häufigster Lenker"));
        angaben.enterPLZ((String) map.get("Postleitzahl HL"));
        angaben.setGeschlecht((String) map.get("Geschlecht häufigster Lenker"));
        angaben.setEntzug((String) map.get("Ausweisentzug häufigster Lenker"));
        angaben.setBisherigerVersicherer((String) map.get("Vorversichererfrage häufigster Lenker"), (String) map.get("Name Vorversicherer"), driver);


        //nur Abfragen wenn Vorversicherer besteht
        if (((String) map.get("Vorversichererfrage häufigster Lenker")).toUpperCase().contentEquals("JA")) {
            angaben.setKuendigung((String) map.get("Kündigung durch Versicherer"), driver);
        }


        //only works up to entering "Jahr des Schadens"-> waiting for Uta
        angaben.setSchaden(
                driver,
                (String) map.get("Schäden letzte 5 Jahre"),
                (String) map.get("Haftpflichtschäden Anzahl"),
                (String) map.get("schadenJahr"),
                (String) map.get("Fahrzeugdiebstaehle"),
                (String) map.get("Parkschaeden"),
                (String) map.get("kollisionsschaden"),
                (String) map.get("kollisionsSchadenJahr"));
//                //TODO: scroll down on page
//
        //Vertragsdetails
        angaben.setVertragsdetails(
                (String) map.get("Versicherungsnehmer ist"),
                (String) map.get("Geburtsdatum Versicherungsnehmer"),
                (String) map.get("Geschlecht Versicherungsnehmer"),
                (String) map.get("Nationalität Versicherungsnehmer"),
                (String) map.get("Postleitzahl Versicherungsnehmer"),
                (String) map.get("Kündigung durch Vorversicherer"),
                (String) map.get("Berechnungsdatum"),
                (String) map.get("Dauer"),
                driver
        );


        angaben.clickWeiter(driver);

        //TODO: check if OPTIMA has different options than basic and compact. May need a separate Prämienpage if the options are different
//
        //Prämien
        PraemiePage praemie = new PraemiePage(driver);
        praemie.selectKasko(
                (String) map.get("Teilkasko"),
                (String) map.get("SB ohne Koll"),
                (String) map.get("SB Koll"),
                (String) map.get("Selbstbehalt Teilkasko"));

        praemie.selectErgaenzungen(
                (String) map.get("Mobilität"),
                (String) map.get("Mitgeführte Sachen"),
                (String) map.get("Teilkasko"),
                (String) map.get("Parkschäden"),
                (String) map.get("UnfallDeckung"),
                (String) map.get("BonusschutzDeckung"),
                (String) map.get("Telematik"),
                driver);


        Thread.sleep(2500);
        Assert.assertEquals(praemie.getBasicPraemie(), (String) map.get("Bruttoprämie"));

        Thread.sleep(5000);

    }
}
