package axa.tests;

import axa.pages.AngabenPage;
import axa.pages.FahrzeugsuchePage;
import axa.pages.PraemiePage;
import axa.utils.ExcelAdapter;
import axa.utils.StatusListener;
import axa.utils.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;

import static java.lang.Integer.valueOf;

@Listeners(StatusListener.class)
public class AutoversicherungsAXADataNEW extends TestBase{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeSuite
    public void openFile() throws IOException {

            String str = "Hello";
            BufferedWriter writer = new BufferedWriter(new FileWriter("result.txt"));
            writer.write(str);
            writer.close();
    }


    @Test(dataProvider="datamap", dataProviderClass = ExcelAdapter.class)
    public void newTest(Map<Object, Object> map) throws InterruptedException, IOException {

        RemoteWebDriver driver = (RemoteWebDriver) getDriver();
        System.out.println("TESTCASE: " + (String)map.get("Testfall")) ;
        logger.debug("TESTCASE: " + (String)map.get("Testfall"));

        //Fahrzeugsuche
        FahrzeugsuchePage fahrzeugsuche = new FahrzeugsuchePage(driver);
        fahrzeugsuche.loadPage((String)map.get("Fahrzeugtyp"), (String)map.get("Rahmenvertrag"), driver);

        String typ = (String)map.get("Fahrzeugtyp");

        //Auto
        if (typ.contentEquals("1.0")) {
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
            angaben.setGebDatum((String) map.get("Geburtsdatum häufigster Lenker"), false);
            angaben.selectNationality((String) map.get("Nationalität häufigster Lenker"));
            angaben.enterPLZ((String) map.get("Postleitzahl HL"));
            angaben.setGeschlecht(driver, (String) map.get("Geschlecht häufigster Lenker"));
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
                    (String) map.get("letztesHaftSchadenDatum"),
                    (String) map.get("Fahrzeugdiebstahl"),
                    (String) map.get("Parkschäden"),
                    (String) map.get("Vollkaskoschäden"),
                    (String) map.get("letztesKollisionSchadenDatum"));
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
                    (String) map.get("Produkt"),
                    (String) map.get("Selbstbehalt Haft"), //for Optima I think
                    (String) map.get("Kollision"),
                    (String) map.get("Teilkasko"),
                    (String) map.get("Selbstbehalt Teilkasko"),
                    (String) map.get("Selbstbehalt Kollision"),
                    (String) map.get("Selbstbehalt Teilkasko"));

            praemie.selectErgaenzungen(
                    (String) map.get("Produkt"),
                    (String) map.get("Mobilität"),
                    (String) map.get("Glasbruch"),
                    (String) map.get("Mitgeführte Sachen"),
                    (String) map.get("ParkschadenDeckung"),
                    (String) map.get("UnfallDeckung"),
                    (String) map.get("BonusschutzDeckung"),
                    (String) map.get("Telematik"),
                    driver);


            String product = (String) map.get("Produkt");

            Thread.sleep(5000);

            doAssert(map, praemie, product);

        }



        //Motorrad
        if (typ.contentEquals("2.0")) {
            fahrzeugsuche.selectInv((String) map.get("Erstinverkehrssetzung"));
            fahrzeugsuche.selectMarke((String) map.get("Marke"));
            fahrzeugsuche.selectModell(driver, (String) map.get("Modell"));
            fahrzeugsuche.clickSearchButton();
            fahrzeugsuche.selectSpecificModel(driver, (String) map.get("TYPBZ"));

            AngabenPage angaben = new AngabenPage(driver);
            angaben.setZubehoer((String) map.get("Zubehör"));
            angaben.setKontrollschild((String) map.get("Besonderes Kontrollschild"));
            angaben.setLeasing((String) map.get("Leasing"));
            angaben.setGeschwindigkeit((String) map.get("vmax"));
            angaben.selectPurchaseYear((String) map.get("Kaufjahr"));

            angaben.setGebDatum((String) map.get("Geburtsdatum häufigster Lenker"), true);
            angaben.selectNationality((String) map.get("Nationalität häufigster Lenker"));
            angaben.enterPLZ((String) map.get("Postleitzahl HL"));
            angaben.setGeschlecht(driver, (String) map.get("Geschlecht häufigster Lenker"));
            angaben.setEntzug((String) map.get("Ausweisentzug häufigster Lenker"));
            angaben.setBisherigerVersicherer((String) map.get("Vorversichererfrage häufigster Lenker"), (String) map.get("Name Vorversicherer"), driver);

            //neue Versicherung or keep old one
            angaben.mutationsFrage((String) map.get("Mutationsfrage"));

            angaben.setSchaden(
                    driver,
                    (String) map.get("Schäden letzte 5 Jahre"),
                    (String) map.get("Haftpflichtschäden Anzahl"),
                    (String) map.get("letztesHaftSchadenDatum"),
                    (String) map.get("Fahrzeugdiebstahl"),
                    (String) map.get("Parkschäden"),
                    (String) map.get("Vollkaskoschäden"),
                    (String) map.get("letztesKollisionSchadenDatum"));

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

            //Prämien
            PraemiePage praemie = new PraemiePage(driver);
            praemie.selectKasko(
                    (String) map.get("Produkt"),
                    (String) map.get("Selbstbehalt Haft"), //for Optima I think
                    (String) map.get("Kollision"),
                    (String) map.get("Teilkasko"),
                    (String) map.get("Selbstbehalt Teilkasko"),
                    (String) map.get("Selbstbehalt Kollision"),
                    (String) map.get("Selbstbehalt Teilkasko"));

            praemie.selectErgaenzungen(
                    (String) map.get("Produkt"),
                    (String) map.get("Mobilität"),
                    (String) map.get("Glasbruch"),
                    (String) map.get("Mitgeführte Sachen"),
                    (String) map.get("ParkschadenDeckung"),
                    (String) map.get("UnfallDeckung"),
                    (String) map.get("BonusschutzDeckung"),
                    (String) map.get("Telematik"),
                    driver);


            String product = (String) map.get("Produkt");

            Thread.sleep(5000);

            doAssert(map, praemie, product);

        }

    }

    private void doAssert(Map<Object, Object> map, PraemiePage praemie, String product) throws IOException {

        DecimalFormat df = new DecimalFormat("#.##");

        String premium = "";
        switch (product) {
            case "Basic":
                premium = praemie.getBasicPraemie();
                break;
            case "Compact":
                premium = praemie.getCompactPrämie();
                break;
            case "Optima":
                premium = praemie.getOptimaPrämie();
                break;
        }

        premium = premium.replace("CHF ", "");
        Double actual = Double.valueOf(premium);


        String exp = (String) map.get("Bruttoprämie");
        Double expected = Double.valueOf(exp);


        Double diff = actual - expected;
        String testcase = (String) map.get("Testfall");

        if (diff <= 1.0 && diff >= -1.0) {
            writeResults(testcase, actual, expected,true );
            Assert.assertTrue(true);
        }
        else {
            writeResults(testcase, actual, expected,false );
            Assert.assertEquals(actual, expected);
        }



/*        if (product.contentEquals("Basic")) {
            System.out.println("Basic Prämie");
            Assert.assertEquals(praemie.getBasicPraemie(), (String) map.get("Bruttoprämie"));
        }
        else if (product.contentEquals("Compact")) {
            System.out.println("Compact Prämie");
            Assert.assertEquals(praemie.getCompactPrämie(), (String) map.get("Bruttoprämie"));
        }
        else if (product.contentEquals("Optima")) {
            System.out.println("Optima Prämie");
            Assert.assertEquals(praemie.getOptimaPrämie(), (String) map.get("Bruttoprämie"));
        }*/
    }

    public void writeResults(String testcase, Double actual, Double expected, Boolean pass) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt", true));
        writer.append('\n');
        writer.append("Testfall: " + testcase + "  --- Pass: " + pass.toString() + " --- Actual: " +actual.toString() + " --- Expected:  " + expected.toString());

        writer.close();
    }
}
