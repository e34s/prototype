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

    @FindBy(id="lp_parkschaden-mf_basic")
    private WebElement parkschaden;

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
    public void selectKasko(String kasko, String selbstbehaltAusserKollision, String selbstbehaltKollision, String selbstbehaltTeilkasko) throws InterruptedException {


        Select realSelect = new Select(this.kasko);
        realSelect.selectByVisibleText(kasko);


        if (kasko.contentEquals("Vollkasko")) {
            this.selectSelbstbehaltausserKollision(selbstbehaltAusserKollision);
            this.selectSelbstbehaltKollision(selbstbehaltKollision);
        }
        else if (kasko.contentEquals("Teilkasko")) {
            this.selectTeilkaskoSelbstbehalt(selbstbehaltTeilkasko);
        }
    }

    public void selectSelbstbehaltausserKollision(String selbstbehalt) throws InterruptedException {

        Select realSelect = new Select(selbstbehaltAusserKollision);
        realSelect.selectByVisibleText(selbstbehalt);
    }

    public void selectSelbstbehaltKollision(String selbstbehalt) throws InterruptedException {

        if(!selbstbehalt.contentEquals("NA")) {
            Select realSelect = new Select(selbstbehaltKollision);
            realSelect.selectByVisibleText(selbstbehalt);
        }
    }

    public void selectTeilkaskoSelbstbehalt(String selbstbehalt) throws InterruptedException {

        if(!selbstbehalt.contentEquals("NA")) {
            Select realSelect = new Select(selbstbehaltTeilkasko);
            realSelect.selectByVisibleText(selbstbehalt);
        }
    }


    //Ergänzungen
    public void selectMobility(String mobility) throws InterruptedException {

        if(!mobility.contentEquals("NA")) {
            Select realSelect = new Select(this.mobility);
            realSelect.selectByVisibleText(mobility);
        }
    }

    public void selectMitgefuehrteSachen(String mitgefuehrteSachen, WebDriver driver) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, 10);

        if(!mitgefuehrteSachen.contentEquals("NA")) {
            Select realSelect = new Select(this.mitgefuehrteSachen);
            realSelect.selectByVisibleText(mitgefuehrteSachen);
            if (mitgefuehrteSachen.contentEquals("ja")) {
                wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("lp_mitgefuehrte_sachen_leistungstext_basic"))));
            }
        }
    }

    public void selectParkschaden(String pSchaden) throws InterruptedException {

        if(!pSchaden.contentEquals("NA")) {
            Select realSelect = new Select(parkschaden);
            realSelect.selectByVisibleText(pSchaden);
        }
    }



    public void selectUnfallversicherung(String unfallversicherung) throws InterruptedException {

        if(!unfallversicherung.contentEquals("NA")) {
            Select realSelect = new Select(unfallVersicherung);
            realSelect.selectByVisibleText(unfallversicherung);
        }
    }

    public void selectBonusschutz(String bonusschutz) throws InterruptedException {

        if(!bonusschutz.contentEquals("NA")) {
            Select realSelect = new Select(this.bonusschutz);
            realSelect.selectByVisibleText(bonusschutz);
        }
    }

    public void selectCrashrecorder(String crashrecorder) throws InterruptedException {

        if(!crashrecorder.contentEquals("NA")) {
            Select realSelect = new Select(this.crashrecorder);
            realSelect.selectByVisibleText(crashrecorder);
        }
    }


    public void selectErgaenzungen(
            String mobility,
            String mitgefuehrteSachen,
            String parkschaden,
            String unfallversicherung,
            String bonusschutz,
            String crashrecorder,
            WebDriver driver ) throws InterruptedException {

        this.selectMobility(mobility);
        this.selectMitgefuehrteSachen(mitgefuehrteSachen, driver);
        this.selectParkschaden(parkschaden);
        this.selectUnfallversicherung(unfallversicherung);
        this.selectBonusschutz(bonusschutz);
        this.selectCrashrecorder(crashrecorder);
    }

}
