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

    public PraemiePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    public String getBasicPraemie() {
        return praemiebasic.getText();
    }

    public String getBasicCompact() {
        return praemiecompact.getText();
    }

    public void selectKasko(String data_kasko_basic) throws InterruptedException {

        Select realSelect = new Select(kasko);
        realSelect.selectByVisibleText(data_kasko_basic);
    }

    public void selectSelbstbehaltausserKollision(String data_selbstbehalt) throws InterruptedException {

        Thread.sleep(500);
        Select realSelect = new Select(selbstbehaltAusserKollision);
        realSelect.selectByVisibleText(data_selbstbehalt);
    }

    public void selectSelbstbehaltKollision(String data_selbstbehalt) throws InterruptedException {

        if(!data_selbstbehalt.contentEquals("NA")) {
            Thread.sleep(500);
            Select realSelect = new Select(selbstbehaltKollision);
            realSelect.selectByVisibleText(data_selbstbehalt);
        }
    }

    public void selectTeilkaskoSelbstbehalt(String data_selbstbehalt) throws InterruptedException {

        if(!data_selbstbehalt.contentEquals("NA")) {
            Thread.sleep(500);
            Select realSelect = new Select(selbstbehaltTeilkasko);
            realSelect.selectByVisibleText(data_selbstbehalt);
        }
    }

}
