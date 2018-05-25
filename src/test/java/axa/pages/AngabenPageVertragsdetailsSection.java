/*
 * Copyright (c) 2014 - 2018.  Element34 Solutions - All Rights Reserved
 * Unauthorized copying and redistribution of this file or parts thereof,
 * via any medium is strictly prohibited without explicit consent of Element34 Solutions GmbH.
 */

package axa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.Map;


public class AngabenPageVertragsdetailsSection {

    @FindBy(id="fl_vn_option")
    private WebElement fzFuehrer;

    @FindBy(id="fl_vn_andere_geburtsdatum")
    private WebElement vnGeburtstag;

    @FindBy(css="#fl_vn_andere_geschlecht_option > label:nth-child(4)")
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


    @FindBy(id="fl_versicherungsbeginn")
    private WebElement versBeginn;

    @FindBy(css="#fl_vertragsdauer_option > label:nth-child(2)")
    private WebElement dauer1Jahr;

    @FindBy(css="#fl_vertragsdauer_option > label:nth-child(4)")
    private WebElement dauer3Jahre;

    public AngabenPageVertragsdetailsSection(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setFahrzeugfuehrer(String fahrzeugFuehrer) throws InterruptedException {
        if(!fahrzeugFuehrer.contentEquals("NA")) {
            Select realSelect = new Select(fzFuehrer);
            realSelect.selectByVisibleText(fahrzeugFuehrer);
        }
    }

    public void setGebDatum(String gebDatum) {
        //convert to dd.mm.yyyy
        gebDatum = gebDatum.replace("-", ".");
        vnGeburtstag.sendKeys(gebDatum);
    }

    public void setGeschlecht(String geschlecht) throws InterruptedException {
        switch (geschlecht.toUpperCase()) {
            case "MÄNNLICH" :
                vnGeschlechtMann.click();
                break;

            case "WEIBLICH" :
                vnGeschlechtFrau.click();
                break;

            default:
                System.out.println("geschlecht invalid");
        }
    }


    public void setNationality(String nationality) throws InterruptedException {


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

    public void setPLZ(String postleitzahl) throws InterruptedException {
        plz.sendKeys(postleitzahl);
    }

    public void setKuendigung(String kuendigung) throws InterruptedException {

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


    public void setVersicherungsbeginn(String versicherungsBeginn) throws InterruptedException {
        if (versicherungsBeginn != null) {
            versicherungsBeginn = versicherungsBeginn.replace("-", ".");
            versBeginn.clear();
            Thread.sleep(1000);
            versBeginn.sendKeys(versicherungsBeginn);
            Thread.sleep(2000);
        }
    }

    public void setVertragsDauer(String vertragsDauer) throws InterruptedException {
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
