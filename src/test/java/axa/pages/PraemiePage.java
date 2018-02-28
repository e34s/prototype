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


    public PraemiePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    public String getBasicPraemie() {
        return praemiebasic.getText();
    }

    public String getBasicCompact() {
        return praemiecompact.getText();
    }

}
