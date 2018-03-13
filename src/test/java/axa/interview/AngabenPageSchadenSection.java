/*
 * Copyright (c) 2014 - 2018.  Element34 Solutions - All Rights Reserved
 * Unauthorized copying and redistribution of this file or parts thereof,
 * via any medium is strictly prohibited without explicit consent of Element34 Solutions GmbH.
 */

package axa.interview;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class AngabenPageSchadenSection {

    @FindBy(id="fl_hff_schaeden_ph_anzahl")
    private WebElement haftPflichtSchaeden;

    @FindBy(id="fl_hff_schaden_ph_jahr")
    private WebElement schadenJahr;

    @FindBy(id="fl_hff_schaeden_tk_fzdiebstahl")
    private WebElement diebstahl;

    @FindBy(id="fl_hff_schaeden_tk_parkschaeden")
    private WebElement parkschaden;

    @FindBy(id="fl_hff_schaeden_vk_anzahl")
    private WebElement kSchaden;

    @FindBy(id="fl_hff_schaden_vk_jahr")
    private WebElement kollisionsSchadenJahr;


    public AngabenPageSchadenSection(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void sethaftPflichtSchaden(String haftpflichtschaden) throws InterruptedException {
        if(!haftpflichtschaden.contentEquals("NA")) {
            Select realSelect = new Select(haftPflichtSchaeden);
            realSelect.selectByVisibleText(haftpflichtschaden);
        }
    }

    public void setSchadenJahr(String schadenjahr) throws InterruptedException {
        if(!schadenjahr.contentEquals("NA")) {
            Select realSelect = new Select(schadenJahr);
            realSelect.selectByVisibleText(schadenjahr);
        }
    }

    public void setDiebstahl(String diebstahl) throws InterruptedException {
        if(!diebstahl.contentEquals("NA")) {
            Select realSelect = new Select(this.diebstahl);
            realSelect.selectByVisibleText(diebstahl);
        }
    }

    public void setParkschaden(String parkschaden) throws InterruptedException {
        if(!parkschaden.contentEquals("NA")) {
            Select realSelect = new Select(this.parkschaden);
            realSelect.selectByVisibleText(parkschaden);
        }
    }

    public void setKollisionsSchaden(String kollisionsschaden) throws InterruptedException {
        if(!kollisionsschaden.contentEquals("NA")) {
            Select realSelect = new Select(kSchaden);
            realSelect.selectByVisibleText(kollisionsschaden);
        }
    }

    public void setkollisionSchadenJahr(String kollisionsschadenjahr) throws InterruptedException {
        if(!kollisionsschadenjahr.contentEquals("NA")) {
            Select realSelect = new Select(kollisionsSchadenJahr);
            realSelect.selectByVisibleText(kollisionsschadenjahr);
        }
    }
}
