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


public class SchadenSection {

    @FindBy(id="fl_hff_schaeden_ph_anzahl")
    private WebElement haftPflichtSchaeden;

    @FindBy(id="fl_hff_schaden_ph_jahr")
    private WebElement schadenJahr;

    @FindBy(id="fl_hff_schaeden_tk_fzdiebstahl")
    private WebElement diebstahl;

    @FindBy(id="fl_hff_schaeden_tk_parkschaeden")
    private WebElement parkschaden;

    @FindBy(id="fl_hff_schaeden_vk_anzahl")
    private WebElement kollisionsSchaden;

    @FindBy(id="fl_hff_schaden_vk_jahr")
    private WebElement kollisionsSchadenJahr;


    public SchadenSection(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void sethaftPflichtSchaden(String data_haftPflichtSchaden) throws InterruptedException {
        if(!data_haftPflichtSchaden.contentEquals("NA")) {
            Select realSelect = new Select(haftPflichtSchaeden);
            realSelect.selectByVisibleText(data_haftPflichtSchaden);
        }
    }

    public void setSchadenJahr(String data_schadenJahr) throws InterruptedException {
        if(!data_schadenJahr.contentEquals("NA")) {
            Select realSelect = new Select(schadenJahr);
            realSelect.selectByVisibleText(data_schadenJahr);
        }
    }

    public void setDiebstahl(String data_diebstahl) throws InterruptedException {
        if(!data_diebstahl.contentEquals("NA")) {
            Select realSelect = new Select(diebstahl);
            realSelect.selectByVisibleText(data_diebstahl);
        }
    }

    public void setParkschaden(String data_parkschaden) throws InterruptedException {
        if(!data_parkschaden.contentEquals("NA")) {
            Select realSelect = new Select(parkschaden);
            realSelect.selectByVisibleText(data_parkschaden);
        }
    }

    public void setKollisionsSchaden(String data_kollisionsSchaden) throws InterruptedException {
        if(!data_kollisionsSchaden.contentEquals("NA")) {
            Select realSelect = new Select(kollisionsSchaden);
            realSelect.selectByVisibleText(data_kollisionsSchaden);
        }
    }

    public void setkollisionSchadenJahr(String data_kollisionsSchadenJahr) throws InterruptedException {
        if(!data_kollisionsSchadenJahr.contentEquals("NA")) {
            Select realSelect = new Select(kollisionsSchadenJahr);
            realSelect.selectByVisibleText(data_kollisionsSchadenJahr);
        }
    }
}
