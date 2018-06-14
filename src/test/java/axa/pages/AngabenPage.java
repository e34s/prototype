/*
 * Copyright (c) 2014 - 2018.  Element34 Solutions - All Rights Reserved
 * Unauthorized copying and redistribution of this file or parts thereof,
 * via any medium is strictly prohibited without explicit consent of Element34 Solutions GmbH.
 */

package axa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class AngabenPage {

    private AngabenPageSchadenSection schadenSection;
    private AngabenPageVertragsdetailsSection vertragsdetailsSection;

    @FindBy(id="fl_zubehoer")
    private WebElement zubehoer;

    @FindBy(css="#fl_besonderes_kontrollschild_option > label:nth-child(2)")
    private WebElement besKontrollschildJa;

    @FindBy(css="#fl_besonderes_kontrollschild_option > label:nth-child(4)")
    private WebElement besKontrollschildNein;

    @FindBy(css="#fl_leasing_option > label:nth-child(2)")
    private WebElement leasingJa;

    @FindBy(css="#fl_leasing_option > label:nth-child(4)")
    private WebElement leasingNein;

    @FindBy(css="#fl_ueber45kmh_option > label:nth-child(2)")
    private WebElement unter46kmh;

    @FindBy(css="#fl_ueber45kmh_option > label:nth-child(4)")
    private WebElement ueber46kmh;


    @FindBy(id="fl_kilometer_jahr")
    private WebElement kilometers;

    @FindBy(id="fl_kaufjahr")
    private WebElement kaufjahr;

    @FindBy(css="#fl_parkschaden_option > label:nth-child(2)")
    private WebElement parkschadenJa;

    @FindBy(css="#fl_parkschaden_option > label:nth-child(4)")
    private WebElement parkschadenNein;

    @FindBy(css="#fl_notbremsassistenten_option > label:nth-child(2)")
    private WebElement notbremsassistentJa;

    @FindBy(css="#fl_notbremsassistenten_option > label:nth-child(4)")
    private WebElement notbremsassistentNein;

    @FindBy(id="fl_auto_hff_geburtsdatum")
    private WebElement geburtsdatum;

    @FindBy(id="fl_moto_hff_geburtsdatum")
    private WebElement geburtsdatumMotorrad;

    @FindBy(id="fl_hff_nationaliatet")
    private WebElement nationalitaet;

    @FindBy(id="fl_hff_plz")
    private WebElement plz;

    @FindBy(css = "#fl_hff_geschlecht_option > label:nth-child(2)")
    private WebElement geschlechtMann;

    @FindBy(css="#fl_hff_geschlecht_option > label:nth-child(4)")
    private WebElement geschlechtFrau;

    @FindBy(css="#fl_hff_fuehrerausweisentzug_option > label:nth-child(2)")
    private WebElement entzugJa;

    @FindBy(css="#fl_hff_fuehrerausweisentzug_option > label:nth-child(4)")
    private WebElement entzugNein;

    @FindBy(css="#fl_hff_vorversicherer_option > label:nth-child(2)")
    private WebElement bisherigerVersichererJa;

    @FindBy(css="#fl_hff_vorversicherer_option > label:nth-child(4)")
    private WebElement bisherigerVersichererNein;

    @FindBy(id="fl_hff_vorversicherer_name")
    private WebElement versicherer;

    @FindBy(css="#fl_hff_vorversicherer_kuendigung_option > label:nth-child(2)")
    private WebElement kuendigungJa;

    @FindBy(css="#fl_hff_vorversicherer_kuendigung_option > label:nth-child(4)")
    private WebElement kuendigungNein;

    @FindBy(css="#fl_bestehende_police_anpassen > label:nth-child(2)")
    private WebElement neueVersicherungAbschliessen;

    @FindBy(css="#fl_bestehende_police_anpassen > label:nth-child(4)")
    private WebElement bestehendenVertragAnpassen;


    @FindBy(css="#fl_hff_schaeden5jahre_option > label:nth-child(2)")
    private WebElement schadenJa;

    @FindBy(css="#fl_hff_schaeden5jahre_option > label:nth-child(4)")
    private WebElement schadenNein;

    @FindBy(id="angaben_weiter")
    private WebElement weiterbutton;

    public AngabenPage(WebDriver driver) {
        this.schadenSection = new AngabenPageSchadenSection(driver);
        this.vertragsdetailsSection = new AngabenPageVertragsdetailsSection(driver);
        PageFactory.initElements(driver, this);
    }

    public void setZubehoer(String zusatzausruestung) throws InterruptedException {
        System.out.println("Zusatzausrüstung: " + zusatzausruestung );


        Thread.sleep(2000);
        zusatzausruestung = zusatzausruestung.replaceAll("\\.0", "");

        zubehoer.clear();
        zubehoer.sendKeys(zusatzausruestung);
    }

    public void setKontrollschild(String kontrollschild) throws InterruptedException {
        System.out.println("Kontrollschild: " + kontrollschild );

        if (kontrollschild != null) {

            switch (kontrollschild.toUpperCase()) {
                case "JA":
                    besKontrollschildJa.click();
                    break;

                case "NEIN":
                    besKontrollschildNein.click();
                    break;

                default:
                    System.out.println("kontrollschild invalid");
            }
        }
    }

    public void setLeasing(String leasing) throws InterruptedException {
        System.out.println("Leasing: " + leasing );

        if (leasing != null) {

            switch (leasing.toUpperCase()) {
                case "JA":
                    leasingJa.click();
                    break;

                case "NEIN":
                    leasingNein.click();
                    break;

                default:
                    System.out.println("leasing invalid");
            }
        }
    }

    public void setGeschwindigkeit(String vmax) {

        vmax = vmax.replaceAll("\\.0", "");
        int speed = Integer.valueOf(vmax);

        try {
            if (speed <= 45.0) {
                unter46kmh.click();
            }
            else {
                ueber46kmh.click();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("VMAX is no displayed");
        }


    }

    public void enterKilometer(String kilometer) {
        System.out.println("Kilometer / Jahr: " + kilometer );


        kilometer = kilometer.replace(".0", "");
        kilometers.sendKeys(kilometer);
    }

    public void selectPurchaseYear(String year) throws InterruptedException {

        System.out.println("Kaufjahr: " + year );

        year = year.replaceAll("\\.0", "");

        Select realSelect = new Select(kaufjahr);
        realSelect.selectByVisibleText(year);
    }

    public void setParkschaden(String parkschaden) throws InterruptedException {

            //according to rules, Parkschaden is always NEIN
            //parkschadenNein.click();
    }


    public void setNotbremsassistent(String notbrems) throws InterruptedException {
        System.out.println("Notbremsassistent: " + notbrems );

        if (notbrems != null) {
            switch (notbrems.toUpperCase()) {
                case "JA" :
                    notbremsassistentJa.click();
                    break;

                case "NEIN" :
                    notbremsassistentNein.click();
                    break;

                default:
                    System.out.println("notbremsassistent invalid");
            }
        }
    }

    public void setGebDatum(String gebDatum, Boolean motorrad) {
        System.out.println("Geburtsdatum: " + gebDatum );

        //convert from dd-mm-yyyy to dd.mm.yyyy
        gebDatum = gebDatum.replace("-", ".");

        if (motorrad) {
            geburtsdatumMotorrad.sendKeys(gebDatum);
        }
        else {
            geburtsdatum.sendKeys(gebDatum);
        }
    }

    public void selectNationality(String nationality) throws InterruptedException {
        System.out.println("Nationalität: " + nationality );


        Map<String, String> map = new HashMap<String, String>();
        map.put("AD - Andorra", "Andorra");
        map.put("AL - Albanien", "Albanien");
        map.put("AS - AS - Samoa (amerikanischer Teil)", "Amerikanisch-Samoa");
        map.put("AT - Österreich", "Österreich");
        map.put("AU - Australien", "Australien");
        map.put("AX - Alandinseln", "Ålandinseln");
        map.put("BA - Bosnien-Herzegowina", "Bosnien-Herzegowina");
        map.put("BE - Belgien", "Belgien");
        map.put("BG - Bulgarien", "Bulgarien");
        map.put("BY - Belarus", "Weissrussland");
        map.put("CA - Kanada", "Kanada");
        map.put("CH - Schweiz", "Schweiz");
        map.put("CY - Zypern", "Zypern");
        map.put("CZ - Tschechien", "Tschechische Republik");
        map.put("DE - Deutschland", "Deutschland");
        map.put("DK - Dänemark", "Dänemark");
        map.put("EE - Estland", "Estland");
        map.put("ES - Spanien", "Spanien");
        map.put("FI - Finnland", "Finnland");
        map.put("FO - Färöer Inseln", "Färöer-Inseln");
        map.put("FR - Frankreich", "Frankreich");
        map.put("GB - Grossbritannien", "Grossbritannien");
        map.put("GG - Guernsey", "Guernsey");
        map.put("GI - Gibraltar", "Gibraltar");
        map.put("GR - Griechenland", "Griechenland");
        map.put("HR - Kroatien", "Kroatien");
        map.put("HU - Ungarn", "Ungarn");
        map.put("IE - Irland", "Irland");
        map.put("IL - Israel", "Israel");
        map.put("IM - Insel Man", "Insel Man");
        map.put("IS - Island", "Island");
        map.put("IT - Italien", "Italien");
        map.put("JE - Jersey", "Jersey");
        map.put("JP - Japan", "Japan");
        map.put("LI - Liechtenstein", "Liechtenstein");
        map.put("LT - Litauen", "Litauen");
        map.put("LU - Luxemburg", "Luxemburg");
        map.put("LV - Lettland", "Lettland");
        map.put("MC - Monaco", "Monaco");
        map.put("MD - Moldova", "Moldova");
        map.put("ME - Montenegro", "Montenegro");
        map.put("MK - Mazedonien", "Mazedonien");
        map.put("MT - Malta", "Malta");
        map.put("NA - Namibia (REST)", "Namibia");
        map.put("NF - Norfolk-Inseln", "Norfolkinsel");
        map.put("NL - Niederlande", "Niederlande");
        map.put("NO - Norwegen", "Norwegen");
        map.put("NZ - Neuseeland", "Neuseeland");
        map.put("PL - Polen", "Polen");
        map.put("PM - St. Pierre und Miquelon", "St. Pierre und Miquelon");
        map.put("PT - Portugal", "Portugal");
        map.put("RO - Rumänien", "Rumänien");
        map.put("RS - Serbien", "Serbien");
        map.put("RU - Russland", "Russische Föderation");
        map.put("SE - Schweden", "Schweden");
        map.put("SI - Slowenien", "Slowenien");
        map.put("SJ - Spitzbergen und Jan Mayen-Inseln", "Svalbard und Jan Mayen");
        map.put("SK - Slowakei", "Slowakei");
        map.put("SM - San Marino", "San Marino");
        map.put("TR - Türkei", "Türkei");
        map.put("UA - Ukraine", "Ukraine");
        map.put("US - Vereinigte Staaten von Amerika", "Vereinigte Staaten");
        map.put("VA - Vatikanstaat", "Vatikanstadt");
        map.put("XK - Kosovo", "Kosovo");

        String nation;
        nation = map.get(nationality);

        Select realSelect = new Select(nationalitaet);
        realSelect.selectByVisibleText(nation);
    }

    public void enterPLZ(String plz) throws InterruptedException {
        System.out.println("PLZ: " + plz );

        this.plz.sendKeys(plz);
    }

    public void setGeschlecht(WebDriver driver, String geschlecht) throws InterruptedException {
        System.out.println("Geschlecht: " + geschlecht );

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", geschlechtMann);


        if (geschlecht != null) {

            WebDriverWait wait = new WebDriverWait( driver, 10);
            wait.until(ExpectedConditions.visibilityOf(geschlechtMann));
            //wait.until(ExpectedConditions.visibilityOf(geschlechtFrau));

            switch (geschlecht.toUpperCase()) {
                case "MÄNNLICH":
                    geschlechtMann.click();
                    break;

                case "WEIBLICH":
                    geschlechtFrau.click();
                    break;

                default:
                    System.out.println("geschlecht invalid");
            }
        }
    }

    public void setEntzug(String entzug) throws InterruptedException {
        System.out.println("Ausweisentzug: " + entzug );

        if (entzug != null) {

            switch (entzug.toUpperCase()) {
                case "JA":
                    entzugJa.click();
                    break;

                case "NEIN":
                    entzugNein.click();
                    break;

                default:
                    System.out.println("entzug invalid");
            }
        }
    }


    public void setBisherigerVersicherer(String bisherigerVersicherer, String versicherer, WebDriver driver) throws InterruptedException {
        System.out.println("Bisherige Versicherer: " + bisherigerVersicherer );
        System.out.println("Versicherer: " + versicherer );



        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bisherigerVersichererNein);

        if (bisherigerVersicherer != null) {

            switch (bisherigerVersicherer.toUpperCase()) {
                case "JA":
                    bisherigerVersichererJa.click();

                    WebDriverWait wait = new WebDriverWait(driver, 10);
                    wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("fl_hff_vorversicherer_name_content"))));

                    Select realSelect = new Select(this.versicherer);
                    realSelect.selectByVisibleText(versicherer);
                    break;

                case "NEIN":
                    bisherigerVersichererNein.click();
                    break;

                default:
                    System.out.println("bisherigerVersichrer invalid");
            }
        }
    }

    public void setKuendigung(String kuendigung, RemoteWebDriver driver) throws InterruptedException {
        System.out.println("Kündigung: " + kuendigung );

        Thread.sleep(1000);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", kuendigungJa);

        if (kuendigung != null) {

            switch (kuendigung.toUpperCase()) {
                case "JA":
                    kuendigungJa.click();
                    break;

                case "NEIN":
                    kuendigungNein.click();
                    break;

                default:
                    System.out.println("kuendigung invalid");
            }
        }
    }

    public void mutationsFrage(String mutation) {
        if (mutation.contentEquals("neue")) {
            neueVersicherungAbschliessen.click();
        }
        else if (mutation.contentEquals("0")) {
            bestehendenVertragAnpassen.click();
        }
        else {
            System.out.println("INVALID Mutationsfrage");
        }
    }

    public void setSchaden(WebDriver driver, String schaden, String haftpflichtSchaden, String schadenJahr, String diebstahl, String parkschaden, String kollisionsSchaden, String kollisionsSchadenJahr) throws InterruptedException {

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", weiterbutton);

        System.out.println("Frühere Schäden: " + schaden);
        System.out.println("- Haftpflichtschaden: " + haftpflichtSchaden );
        System.out.println("- Schadenjahr: " + schadenJahr);
        System.out.println("- Diebstahl: " + diebstahl);
        System.out.println("- Parkschaden: " + parkschaden );
        System.out.println("- Kollisionsschaden: " + kollisionsSchaden );
        System.out.println("- Kollisions Schadenjahr: " + kollisionsSchadenJahr );


        if (schaden != null) {

            //scroll into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", schadenJa);


            switch (schaden.toUpperCase()) {
                case "JA":
                    schadenJa.click();

                    this.schadenSection.sethaftPflichtSchaden(haftpflichtSchaden);
                    if (haftpflichtSchaden.contentEquals("kein Schaden")) {
                        //do nothing
                    }
                    else {
                        this.schadenSection.setSchadenJahr(schadenJahr);
                    }

                    this.schadenSection.setDiebstahl(diebstahl);
                    this.schadenSection.setParkschaden(parkschaden);
                    this.schadenSection.setKollisionsSchaden(kollisionsSchaden);
                    this.schadenSection.setkollisionSchadenJahr(kollisionsSchadenJahr);
                    break;

                case "NEIN":
                    Thread.sleep(2000);
                    schadenNein.click();
                    break;

                default:
                    System.out.println("schaden invalid");
            }
        }
    }

    public void setVertragsdetails(String fzFuehrer, String gebDatum, String geschlecht, String nationalitaet, String plz, String kuendigung, String versBeginn, String vertragsDauer, WebDriver driver) throws InterruptedException {

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", weiterbutton);

        this.vertragsdetailsSection.setFahrzeugfuehrer(fzFuehrer);

        //additional information needed
        if (fzFuehrer.contentEquals("nicht der häufigste Fahrzeugführer")) {
            this.vertragsdetailsSection.setGebDatum(gebDatum);
            this.vertragsdetailsSection.setGeschlecht(driver, geschlecht);
            this.vertragsdetailsSection.setNationality(nationalitaet);
            this.vertragsdetailsSection.setPLZ(driver, plz);
            this.vertragsdetailsSection.setKuendigung(kuendigung);
            this.vertragsdetailsSection.setVersicherungsbeginn(versBeginn, driver);
            this.vertragsdetailsSection.setVertragsDauer(vertragsDauer);

        }
        else {
            this.vertragsdetailsSection.setVersicherungsbeginn(versBeginn, driver);
            this.vertragsdetailsSection.setVertragsDauer(vertragsDauer);
        }
    }

    public void clickWeiter(RemoteWebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", weiterbutton);

        weiterbutton.click();
    }

}
