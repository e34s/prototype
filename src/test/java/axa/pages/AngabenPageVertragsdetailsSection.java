/*
 * Copyright (c) 2014 - 2018.  Element34 Solutions - All Rights Reserved
 * Unauthorized copying and redistribution of this file or parts thereof,
 * via any medium is strictly prohibited without explicit consent of Element34 Solutions GmbH.
 */

package axa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;


public class AngabenPageVertragsdetailsSection {

    @FindBy(id="fl_vn_option")
    private WebElement fzFuehrer;

    @FindBy(id="fl_vn_andere_geburtsdatum")
    private WebElement vnGeburtstag;

    @FindBy(css="#fl_vn_andere_geschlecht_option > label:nth-child(2)")
    private WebElement vnGeschlechtMann;

    @FindBy(css="#fl_vn_andere_geschlecht_option > label:nth-child(4)")
    private WebElement vnGeschlechtFrau;

    @FindBy(id="fl_vn_andere_nationaliatet")
    private WebElement nationalitaet;

    @FindBy(id="fl_vn_andere_plz")
    private WebElement plz;

    @FindBy(css="#fl_vn_andere_versicherer_kuendigung_option > label:nth-child(2)")
    private WebElement kuendigungJa;

    @FindBy(css="#fl_vn_andere_versicherer_kuendigung_option > label:nth-child(4)")
    private WebElement kuendigungNein;

    //this is the regular case
    @FindBy(id="fl_versicherungsbeginn")
    private WebElement versBeginn;

    //when Rahmenvertrag is used
    @FindBy(id="fl_versicherungsbeginn_other")
    private WebElement versBeginnOther;


    @FindBy(css="#fl_vertragsdauer_option > label:nth-child(2)")
    private WebElement dauer1Jahr;

    @FindBy(css="#fl_vertragsdauer_option > label:nth-child(4)")
    private WebElement dauer3Jahre;

    public AngabenPageVertragsdetailsSection(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setFahrzeugfuehrer(String fahrzeugFuehrer) throws InterruptedException {

        System.out.println("Fahrzeugführer: " + fahrzeugFuehrer);


        if(!fahrzeugFuehrer.contentEquals("NA")) {

            //not ideal but it is coming like this from the Excel sheet
            if (fahrzeugFuehrer.contentEquals("nicht der häufigste Fahrzeugführer")) {
                Select realSelect = new Select(fzFuehrer);
                realSelect.selectByVisibleText("Nicht der häufigste Fahrzeugführer");
            }
        }
    }

    public void setGebDatum(String gebDatum) {
        System.out.println("VN Geburtsdatum: " + gebDatum );


        //convert to dd.mm.yyyy
        gebDatum = gebDatum.replace("-", ".");
        vnGeburtstag.sendKeys(gebDatum);
    }

    public void setGeschlecht(WebDriver driver, String geschlecht) throws InterruptedException {
        System.out.println("VN Geschlecht " + geschlecht);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", vnGeschlechtMann);


        WebDriverWait wait = new WebDriverWait( driver, 10);
        wait.until(ExpectedConditions.visibilityOf(vnGeschlechtMann));


        switch (geschlecht) {
            case "männlich" :
                vnGeschlechtMann.click();
                break;

            case "weiblich" :
                vnGeschlechtFrau.click();
                break;

            default:
                System.out.println("geschlecht invalid");
        }
    }


    public void setNationality(String nationality) throws InterruptedException {

        System.out.println("VN Nationalität: " + nationality );


        //TODO: refactor as this is copy / paste / duplicate code
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
        map.put("BY - Belarus", "Belarus");
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
        map.put("SJ - Spitzbergen und Jan Mayen-Inseln", "Norwegen");
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

    public void setPLZ(WebDriver driver, String postleitzahl) throws InterruptedException {
        System.out.println("VN PLZ: " + postleitzahl );


        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", plz);

        plz.sendKeys(postleitzahl);
    }

    public void setKuendigung(String kuendigung) throws InterruptedException {
        System.out.println("Kündigung: " + kuendigung );


        switch (kuendigung.toUpperCase()) {
            case "JA" :
                kuendigungJa.click();
                break;

            case "NEIN" :
                kuendigungNein.click();
                break;

            default:
                System.out.println("kuendigung invalid");
        }
    }


    public void setVersicherungsbeginn(String versicherungsBeginn, WebDriver driver) throws InterruptedException {
        System.out.println("Versicherungsbeginn: " + versicherungsBeginn );


        if (versicherungsBeginn != null) {
            versicherungsBeginn = versicherungsBeginn.replace("-", ".");
            Thread.sleep(1000);

            //check LINKID, if 1000 or 8000 then no Rahmenvertrag -> use versBeginn
            //otherwise use versBeginnOther
            String url = driver.getCurrentUrl();
            if (url.contains("LINKID=1000") || url.contains("LINKID=8000")) {
                versBeginn.clear();
                versBeginn.sendKeys(versicherungsBeginn);
            }
            else {
                versBeginnOther.clear();
                versBeginnOther.sendKeys(versicherungsBeginn);
            }
            Thread.sleep(2000);
        }
    }

    public void setVertragsDauer(String vertragsDauer) throws InterruptedException {
        System.out.println("Vertragsdauer: " + vertragsDauer );


        if (vertragsDauer != null) {

            if (vertragsDauer.contentEquals("1")) {
                dauer1Jahr.click();
            }
            else if (vertragsDauer.contentEquals("3")) {
                dauer3Jahre.click();
            }
            else {
                System.out.println("Vertragsdauer INVALID");
            }
        }
    }
}
