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

        System.out.println("Haftpflichtschaden: " + haftpflichtschaden);

        if(!haftpflichtschaden.contentEquals("NA")) {
            Select realSelect = new Select(haftPflichtSchaeden);
            realSelect.selectByVisibleText(haftpflichtschaden);
        }
    }

    public void setSchadenJahr(String schadenjahr) throws InterruptedException {

        System.out.println("Schadenjahr: " + schadenjahr);

        schadenjahr = schadenjahr.substring(6, 10);

        if(!schadenjahr.contentEquals("NA")) {
            Select realSelect = new Select(schadenJahr);
            realSelect.selectByVisibleText(schadenjahr);
        }
    }

    public void setDiebstahl(String diebstahl) throws InterruptedException {
        System.out.println("Diebstahl: " + diebstahl);

        if(!diebstahl.contentEquals("NA")) {

            //TODO: add cases when more than 1 Schaden has ocurred (2 Schäden)
            diebstahl = diebstahl.substring(0,1) + " Schaden";

            Select realSelect = new Select(this.diebstahl);
            realSelect.selectByVisibleText(diebstahl);
        }
    }

    public void setParkschaden(String parkschaden) throws InterruptedException {

        System.out.println("Parkschaden: " + parkschaden);

        if(!parkschaden.contentEquals("NA")) {

            //TODO: add case when more than 3 Schäden have ocurred (mehr als 3 Schäden)
            parkschaden = parkschaden.substring(0,1);
            if (parkschaden.contentEquals("0")) {
                parkschaden = "keinen Schaden";
            }
            else if (parkschaden.contentEquals("1")) {
                parkschaden = parkschaden + " Schaden";
            }
            else {
                parkschaden = parkschaden + " Schäden";
            }

            Select realSelect = new Select(this.parkschaden);
            realSelect.selectByVisibleText(parkschaden);
        }
    }

    public void setKollisionsSchaden(String kollisionsschaden) throws InterruptedException {
        System.out.println("Kollisionsschaden: " + kollisionsschaden);

        if(!kollisionsschaden.contentEquals("NA")) {

            //TODO: add case when more than 3 Schäden have ocurred (mehr als 3 Schäden)
            kollisionsschaden = kollisionsschaden.substring(0,1);
            if (kollisionsschaden.contentEquals("0")) {
                kollisionsschaden = "keinen Schaden";
            }
            else if (kollisionsschaden.contentEquals("1")) {
                kollisionsschaden = kollisionsschaden + " Schaden";
            }
            else {
                kollisionsschaden = kollisionsschaden + " Schäden";
            }


            Select realSelect = new Select(kSchaden);
            realSelect.selectByVisibleText(kollisionsschaden);
        }
    }

    public void setkollisionSchadenJahr(String kollisionsschadenjahr) throws InterruptedException {

        System.out.println("Kollisions Schadenjahr: " + kollisionsschadenjahr);

        if( !kollisionsschadenjahr.contentEquals("")) {
            kollisionsschadenjahr = kollisionsschadenjahr.substring(6, 10);

            Select realSelect = new Select(kollisionsSchadenJahr);
            realSelect.selectByVisibleText(kollisionsschadenjahr);
        }
    }
}
