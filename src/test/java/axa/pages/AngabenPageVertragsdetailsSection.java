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

        Select realSelect = new Select(nationalitaet);
        realSelect.selectByVisibleText(nationality);
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
            versBeginn.clear();
            Thread.sleep(1000);
            versBeginn.sendKeys(versicherungsBeginn);
            Thread.sleep(2000);

//            versBeginn.click();
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
