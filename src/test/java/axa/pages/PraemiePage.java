/*
 * Copyright (c) 2014 - 2018.  Element34 Solutions - All Rights Reserved
 * Unauthorized copying and redistribution of this file or parts thereof,
 * via any medium is strictly prohibited without explicit consent of Element34 Solutions GmbH.
 */

package axa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PraemiePage {

    @FindBy(id="lp_praemie_top-mf_basic")
    private WebElement praemiebasic;

    @FindBy(id="lp_praemie_top-mf_compact")
    private WebElement praemiecompact;

    @FindBy(id="lp_kasko_option_basic")
    private WebElement kasko;

    @FindBy(id="lp_vollkasko_selbstbehalt-mf_basic")
    private WebElement selbstbehaltAusserKollision;

    @FindBy(id="lp_vollkasko_kollision_selbstbehalt-mf_basic")
    private WebElement selbstbehaltKollision;

    @FindBy(id="lp_teilkasko_selbstbehalt-mf_basic")
    private WebElement selbstbehaltTeilkasko;

    @FindBy(id="lp_mobilitaet-mf_basic")
    private WebElement mobility;

    @FindBy(id="lp_mitgefuehrte_sachen-mf_basic")
    private WebElement mitgefuehrteSachen;

    @FindBy(id="lp_unfall_option-mf_basic")
    private WebElement unfallVersicherung;

    @FindBy(id="lp_bonusschutz_option-mf_basic")
    private WebElement bonusschutz;

    @FindBy(id="lp_crashrecorder_option-mf_basic")
    private WebElement crashrecorder;



    public PraemiePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    //Prämien auslesen
    public String getBasicPraemie() {
        return praemiebasic.getText();
    }

    public String getBasicCompact() {
        return praemiecompact.getText();
    }




    //Kasko
    public void selectKasko(String data_kasko, String data_selbstbehaltAusserKollision, String data_selbstbehaltKollision, String data_selbstbehaltTeilkasko) throws InterruptedException {


        Select realSelect = new Select(kasko);
        realSelect.selectByVisibleText(data_kasko);


        if (data_kasko.contentEquals("Vollkasko")) {
            this.selectSelbstbehaltausserKollision(data_selbstbehaltAusserKollision);
            this.selectSelbstbehaltKollision(data_selbstbehaltKollision);
        }
        else if (data_kasko.contentEquals("Teilkasko")) {
            this.selectTeilkaskoSelbstbehalt(data_selbstbehaltTeilkasko);
        }
    }

    public void selectSelbstbehaltausserKollision(String data_selbstbehalt) throws InterruptedException {

        Select realSelect = new Select(selbstbehaltAusserKollision);
        realSelect.selectByVisibleText(data_selbstbehalt);
    }

    public void selectSelbstbehaltKollision(String data_selbstbehalt) throws InterruptedException {

        if(!data_selbstbehalt.contentEquals("NA")) {
            Select realSelect = new Select(selbstbehaltKollision);
            realSelect.selectByVisibleText(data_selbstbehalt);
        }
    }

    public void selectTeilkaskoSelbstbehalt(String data_selbstbehalt) throws InterruptedException {

        if(!data_selbstbehalt.contentEquals("NA")) {
            Select realSelect = new Select(selbstbehaltTeilkasko);
            realSelect.selectByVisibleText(data_selbstbehalt);
        }
    }


    //Ergänzungen
    public void selectMobility(String data_mobility) throws InterruptedException {

        if(!data_mobility.contentEquals("NA")) {
            Select realSelect = new Select(mobility);
            realSelect.selectByVisibleText(data_mobility);
        }
    }

    public void selectMitgefuehrteSachen(String data_mitgefuehrteSachen, WebDriver driver) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, 10);

        if(!data_mitgefuehrteSachen.contentEquals("NA")) {
            Select realSelect = new Select(mitgefuehrteSachen);
            realSelect.selectByVisibleText(data_mitgefuehrteSachen);
            if (data_mitgefuehrteSachen.contentEquals("ja")) {
                wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("lp_mitgefuehrte_sachen_leistungstext_basic"))));
            }
        }
    }

    public void selectUnfallversicherung(String data_unfallversicherung) throws InterruptedException {

        if(!data_unfallversicherung.contentEquals("NA")) {
            Select realSelect = new Select(unfallVersicherung);
            realSelect.selectByVisibleText(data_unfallversicherung);
        }
    }

    public void selectBonusschutz(String data_bonusschutz) throws InterruptedException {

        if(!data_bonusschutz.contentEquals("NA")) {
            Select realSelect = new Select(bonusschutz);
            realSelect.selectByVisibleText(data_bonusschutz);
        }
    }

    public void selectCrashrecorder(String data_crashRecorder) throws InterruptedException {

        if(!data_crashRecorder.contentEquals("NA")) {
            Select realSelect = new Select(crashrecorder);
            realSelect.selectByVisibleText(data_crashRecorder);
        }
    }


    public void selectErgaenzungen(String data_mobility, String data_mitgefuehrteSachen, String data_unfallVersicherung, String data_bonusSchutz, String data_crashRecorder, WebDriver driver ) throws InterruptedException {

        this.selectMobility(data_mobility);
        this.selectMitgefuehrteSachen(data_mitgefuehrteSachen, driver);
        this.selectUnfallversicherung(data_unfallVersicherung);
        this.selectBonusschutz(data_bonusSchutz);
        this.selectCrashrecorder(data_crashRecorder);
    }

}
