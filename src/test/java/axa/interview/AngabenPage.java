/*
 * Copyright (c) 2014 - 2018.  Element34 Solutions - All Rights Reserved
 * Unauthorized copying and redistribution of this file or parts thereof,
 * via any medium is strictly prohibited without explicit consent of Element34 Solutions GmbH.
 */

package axa.interview;

import axa.pages.AngabenPageVertragsdetailsSection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AngabenPage {

    //Wie kann man die AngabenSchadenSection in dieses Page Object einbinden?

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

    @FindBy(id="fl_hff_nationaliatet")
    private WebElement nationalitaet;

    @FindBy(id="fl_hff_plz")
    private WebElement plz;

    @FindBy(css="#fl_hff_geschlecht_option > label:nth-child(2)")
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

    @FindBy(css="#fl_hff_schaeden5jahre_option > label:nth-child(2)")
    private WebElement schadenJa;

    @FindBy(css="#fl_hff_schaeden5jahre_option > label:nth-child(4)")
    private WebElement schadenNein;

    @FindBy(id="angaben_weiter")
    private WebElement weiterbutton;

    public AngabenPage(WebDriver driver) {

        //TODO

        PageFactory.initElements(driver, this);
    }

    public void setZubehoer(String zusatzausruestung) throws InterruptedException {
        Thread.sleep(1000);
        zubehoer.clear();
        zubehoer.sendKeys(zusatzausruestung);
    }

    public void setKontrollschild(String kontrollschild) throws InterruptedException {
        switch (kontrollschild.toUpperCase()) {
            case "JA" :
                besKontrollschildJa.click();
                break;

            case "NEIN" :
                besKontrollschildNein.click();
                break;

            default:
                System.out.println("kontrollschild invalid");
        }
    }

    public void setLeasing(String leasing) throws InterruptedException {
        switch (leasing.toUpperCase()) {
            case "JA" :
                leasingJa.click();
                break;

            case "NEIN" :
                leasingNein.click();
                break;

            default:
                System.out.println("leasing invalid");
        }
    }

    public void enterKilometer(String kilometer) {

        kilometers.sendKeys(kilometer);
    }

    public void selectPurchaseYear(String year) throws InterruptedException {

        Select realSelect = new Select(kaufjahr);
        realSelect.selectByVisibleText(year);
    }

    public void setParkschaden(String parkschaden) throws InterruptedException {
        if (parkschaden != null) {
            switch (parkschaden.toUpperCase()) {
                case "JA" :
                    parkschadenJa.click();
                    break;

                case "NEIN" :
                    parkschadenNein.click();
                    break;

                default:
                    break;
            }
        }
    }

    public void setNotbremsassistent(String notbrems) throws InterruptedException {
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

    public void setGebDatum(String gebDatum) {
        geburtsdatum.sendKeys(gebDatum);
    }

    public void selectNationality(String nationality) throws InterruptedException {

        Select realSelect = new Select(nationalitaet);
        realSelect.selectByVisibleText(nationality);
    }

    public void enterPLZ(String plz) throws InterruptedException {
        this.plz.sendKeys(plz);
    }

    public void setGeschlecht(String geschlecht) throws InterruptedException {
        switch (geschlecht.toUpperCase()) {
            case "MÄNNLICH" :
                geschlechtMann.click();
                break;

            case "WEIBLICH" :
                geschlechtFrau.click();
                break;

            default:
                System.out.println("geschlecht invalid");
        }
    }

    public void setEntzug(String entzug) throws InterruptedException {

        switch (entzug.toUpperCase()) {
            case "JA" :
                entzugJa.click();
                break;

            case "NEIN" :
                entzugNein.click();
                break;

            default:
                System.out.println("entzug invalid");
        }
    }


    public void setBisherigerVersicherer(String bisherigerVersicherer, String versicherer, WebDriver driver) throws InterruptedException {

        switch (bisherigerVersicherer.toUpperCase()) {
            case "JA" :
                bisherigerVersichererJa.click();

                WebDriverWait wait = new WebDriverWait(driver, 10);
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("fl_hff_vorversicherer_name_content"))));

                Select realSelect = new Select(this.versicherer);
                realSelect.selectByVisibleText(versicherer);
                break;

            case "NEIN" :
                bisherigerVersichererNein.click();
                break;

            default:
                System.out.println("bisherigerVersichrer invalid");
        }
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



    public void clickWeiter() {
        weiterbutton.click();
    }
}