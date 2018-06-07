/*
 * Copyright (c) 2014 - 2018.  Element34 Solutions - All Rights Reserved
 * Unauthorized copying and redistribution of this file or parts thereof,
 * via any medium is strictly prohibited without explicit consent of Element34 Solutions GmbH.
 */

package axa.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PraemiePage {

    @FindBy(id="lp_praemie_bottom-mf_basic")
    private WebElement praemiebasic;
    @FindBy(id="lp_praemie_bottom-mf_compact")
    private WebElement praemiecompact;

    @FindBy(id="lp_kasko_option_basic")
    private WebElement kasko_basic;
    @FindBy(id="lp_kasko_option_compact")
    private WebElement kasko_compact;


    @FindBy(id="lp_vollkasko_selbstbehalt-mf_basic")
    private WebElement selbstbehaltAusserKollision_basic;
    @FindBy(id="lp_vollkasko_selbstbehalt-mf_compact")
    private WebElement selbstbehaltAusserKollision_compact;


    @FindBy(id="lp_vollkasko_kollision_selbstbehalt-mf_basic")
    private WebElement selbstbehaltKollision_basic;
    @FindBy(id="lp_vollkasko_kollision_selbstbehalt-mf_compact")
    private WebElement selbstbehaltKollision_compact;


    @FindBy(id="lp_teilkasko_selbstbehalt-mf_basic")
    private WebElement selbstbehaltTeilkasko_basic;
    @FindBy(id="lp_teilkasko_selbstbehalt-mf_compact")
    private WebElement selbstbehaltTeilkasko_compact;


    @FindBy(id="lp_mobilitaet-mf_basic")
    private WebElement mobility_basic;
    @FindBy(id="lp_mobilitaet-mf_compact")
    private WebElement mobility_compact;


    @FindBy(id="lp_glasbruch_plus_auto-mf_compact")
    private WebElement glasbruch_compact;


    @FindBy(id="lp_mitgefuehrte_sachen-mf_basic")
    private WebElement mitgefuehrteSachen_basic;
    @FindBy(id="lp_mitgefuehrte_sachen-mf_compact")
    private WebElement mitgefuehrteSachen_compact;


    @FindBy(id="lp_parkschaden-mf_basic")
    private WebElement parkschaden_basic;
    @FindBy(id="lp_parkschaden-mf_compact")
    private WebElement parkschaden_compact;


    @FindBy(id="lp_unfall_option-mf_basic")
    private WebElement unfallVersicherung_basic;
    @FindBy(id="lp_unfall_option-mf_compact")
    private WebElement unfallVersicherung_compact;


    @FindBy(id="lp_bonusschutz_option-mf_basic")
    private WebElement bonusschutz_basic;
    @FindBy(id="lp_bonusschutz_option-mf_compact")
    private WebElement bonusschutz_compact;


    @FindBy(id="lp_crashrecorder_mit_rabatt_option-mf_basic")
    private WebElement crashrecorder_basic_mit_rabatt;
    @FindBy(id="lp_crashrecorder_option-mf_basic")
    private WebElement crashrecorder_basic_ohne_rabatt;


    @FindBy(id="lp_crashrecorder_mit_rabatt_option-mf_compact")
    private WebElement crashrecorder_compact_mit_rabatt;
    @FindBy(id="lp_crashrecorder_option-mf_compact")
    private WebElement crashrecorder_compact_ohne_rabatt;

    public PraemiePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    //Prämien auslesen
    public String getBasicPraemie() {
        return praemiebasic.getText();
    }

    public String getCompactPrämie() {
        return praemiecompact.getText();
    }




    //Kasko
    public void selectKasko(String produkt, String kollision, String teilKasko, String selbstbehaltAusserKollision, String selbstbehaltKollision, String selbstbehaltTeilkasko) throws InterruptedException {

        System.out.println("Produkt: " + produkt);
        System.out.println("Kollision: " + kollision);
        System.out.println("Teilkasko: " + teilKasko);
        System.out.println("Selbstbehalt ausser Kollision: " + selbstbehaltAusserKollision);
        System.out.println("Selbstbehalt Kollision: " + selbstbehaltKollision);
        System.out.println("Selbstbehalt Teilkasko: " + selbstbehaltTeilkasko);





        //check if basic or compact tarif
        if (produkt.contentEquals("Compact")) {
            //Vollkasko
            if(kollision.contentEquals("Ja")) {
                Select realSelect = new Select(this.kasko_compact);
                realSelect.selectByVisibleText("Vollkasko");

                this.selectSelbstbehaltausserKollision(selbstbehaltAusserKollision); //TODO: which field in Excel?
                this.selectSelbstbehaltKollision(selbstbehaltKollision, true);

            }
            //Teilkasko
            else if (teilKasko.contentEquals("Ja")) {
                Select realSelect = new Select(this.kasko_compact);
                realSelect.selectByVisibleText("Teilkasko");

                this.selectTeilkaskoSelbstbehalt(selbstbehaltTeilkasko);

            }
            //no Kasko
            else {
                Select realSelect = new Select(this.kasko_basic);
                realSelect.selectByVisibleText("Ohne Kasko");

            }

        }

        //Basic
        else if (produkt.contentEquals("Basic")) {
            if(kollision.contentEquals("Ja")) {
                Select realSelect = new Select(this.kasko_basic);
                realSelect.selectByVisibleText("Vollkasko");

                this.selectSelbstbehaltausserKollision(selbstbehaltAusserKollision); //TODO: which field in Excel?
                this.selectSelbstbehaltKollision(selbstbehaltKollision, false);

            }
            else if (teilKasko.contentEquals("Ja")) {
                Select realSelect = new Select(this.kasko_basic);
                realSelect.selectByVisibleText("Teilkasko");

                this.selectTeilkaskoSelbstbehalt(selbstbehaltTeilkasko);

            }
            else {
                Select realSelect = new Select(this.kasko_basic);
                realSelect.selectByVisibleText("Ohne Kasko");

            }
        }





//        if (teilKasko.contentEquals("Ja")) {
//            Select realSelect = new Select(this.kasko_basic);
//            realSelect.selectByVisibleText("Teilkasko");
//
//            this.selectTeilkaskoSelbstbehalt(selbstbehaltTeilkasko_basic);
//
//        }
//        else //leave on Vollkasko which is the default
//        {
//
//        }


//        Select realSelect = new Select(this.kasko_basic);
//        realSelect.selectByVisibleText(kasko_basic);
//
//
//        if (kasko_basic.contentEquals("Vollkasko")) {
//            this.selectSelbstbehaltausserKollision(selbstbehaltAusserKollision_basic);
//            this.selectSelbstbehaltKollision(selbstbehaltKollision_basic);
//        }
//        else if (kasko_basic.contentEquals("Teilkasko")) {
//            this.selectTeilkaskoSelbstbehalt(selbstbehaltTeilkasko_basic);
//        }
    }

    //Teilkasko SB
    public void selectSelbstbehaltausserKollision(String selbstbehalt) throws InterruptedException {
        if(!selbstbehalt.contentEquals("NA")) {
            //convert number into CHF Format
            //Excel provides i.e. 0.0 which needs to be converted to CHF 0
            selbstbehalt = selbstbehalt.replaceAll("\\.0", "");
            selbstbehalt = "CHF " + selbstbehalt;

            Select realSelect = new Select(selbstbehaltAusserKollision_basic);
            realSelect.selectByVisibleText(selbstbehalt);
        }



        Select realSelect = new Select(selbstbehaltAusserKollision_basic);
        realSelect.selectByVisibleText(selbstbehalt);
    }

    //Vollkasko SB
    public void selectSelbstbehaltKollision(String selbstbehalt, Boolean compact) throws InterruptedException {

        if(!selbstbehalt.contentEquals("NA")) {
            //convert number into CHF Format
            //Excel provides i.e. 0.0 which needs to be converted to CHF 0
            selbstbehalt = selbstbehalt.replaceAll("\\.0", "");
            selbstbehalt = "CHF " + selbstbehalt;

            if(compact) {
                Select realSelect = new Select(selbstbehaltKollision_compact);
                realSelect.selectByVisibleText(selbstbehalt);
            }
            else {
                Select realSelect = new Select(selbstbehaltKollision_basic);
                realSelect.selectByVisibleText(selbstbehalt);
            }

        }
    }

    public void selectTeilkaskoSelbstbehalt(String selbstbehalt) throws InterruptedException {

        if(!selbstbehalt.contentEquals("NA")) {
            //convert number into CHF Format
            //Excel provides i.e. 0.0 which needs to be converted to CHF 0
            selbstbehalt = selbstbehalt.replaceAll("\\.0", "");
            selbstbehalt = "CHF " + selbstbehalt;


            Select realSelect = new Select(selbstbehaltTeilkasko_basic);
            realSelect.selectByVisibleText(selbstbehalt);
        }
    }


    //Ergänzungen
    public void selectMobility(String produkt, String mobility) throws InterruptedException {
        System.out.println("Mobility: " + mobility);

        //Basic
        if (produkt.contentEquals("Basic")) {
            if (mobility.contentEquals("ohne")) {
                Select realSelect = new Select(this.mobility_basic);
                realSelect.selectByVisibleText("nein");
            }
            else if (mobility.contentEquals("Schweiz")) {
                Select realSelect = new Select(this.mobility_basic);
                realSelect.selectByVisibleText("Schweiz");
            }
        }
        //Compact
        else if (produkt.contentEquals("Compact")) {
            if (mobility.contentEquals("Schweiz")) {
                Select realSelect = new Select(this.mobility_compact);
                realSelect.selectByVisibleText("Schweiz");
            }
            else if (mobility.contentEquals("Plus")) {
                Select realSelect = new Select(this.mobility_compact);
                realSelect.selectByVisibleText("Schweiz und Europa");
            }

        }
        else {
            System.out.println("INVALID - mobility");
        }

    }

    private void selectGlasbruch(String produkt, String glasbruch) {
        System.out.println("Glasbruch: " + glasbruch);

        //Basic
        if (produkt.contentEquals("Basic")) {
            //no selection options

        }
        //Compact
        else if (produkt.contentEquals("Compact")) {
            if (glasbruch.contentEquals("Glasbruch")) {
                Select realSelect = new Select(this.glasbruch_compact);
                realSelect.selectByVisibleText("Basisschutz");
            }
            else if (glasbruch.contentEquals("Glasbruch Plus")) {
                Select realSelect = new Select(this.glasbruch_compact);
                realSelect.selectByVisibleText("Glasbruch Plus");
            }

        }
        else {
            System.out.println("INVALID - Glasbruch");
        }
    }

    public void selectMitgefuehrteSachen(String produkt, String mitgefuehrteSachen, WebDriver driver) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        System.out.println("Glasbruch: " + mitgefuehrteSachen);

        //Basic
        if (produkt.contentEquals("Basic")) {
            if (mitgefuehrteSachen.contentEquals("ohne")) {
                Select realSelect = new Select(this.mitgefuehrteSachen_basic);
                realSelect.selectByVisibleText("nein");
            }
            else if (mitgefuehrteSachen.contentEquals("Normal")) {
                Select realSelect = new Select(this.mitgefuehrteSachen_basic);
                realSelect.selectByVisibleText("ja");
            }
        }

        //Compact
        else if (produkt.contentEquals("Compact")) {
            if (mitgefuehrteSachen.contentEquals("ohne")) {
                Select realSelect = new Select(this.mitgefuehrteSachen_compact);
                realSelect.selectByVisibleText("nein");
            }
            else if (mitgefuehrteSachen.contentEquals("Plus")) {
                Select realSelect = new Select(this.mitgefuehrteSachen_compact);
                realSelect.selectByVisibleText("ja");
            }

        }
        else {
            System.out.println("INVALID - Mitgeführte Sachen");
        }







        if(!mitgefuehrteSachen.contentEquals("NA")) {

            System.out.println("Mitgeführte Sachen: " + mitgefuehrteSachen);

            if (mitgefuehrteSachen.contentEquals("ohne")) {
                Select realSelect = new Select(this.mitgefuehrteSachen_basic);
                realSelect.selectByVisibleText("nein");
            }
            if (mitgefuehrteSachen.contentEquals("Normal")) {
//                wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("lp_mitgefuehrte_sachen_leistungstext_basic"))));
                Select realSelect = new Select(this.mitgefuehrteSachen_basic);
                realSelect.selectByVisibleText("ja");
            }
        }
    }

    //TODO: UGLY !!!
    public void selectParkschaden(String produkt, String pSchaden) throws InterruptedException {

        System.out.println("Parkschaden: " + pSchaden);

        //Basic
        if (produkt.contentEquals("Basic")) {
            if (pSchaden.contentEquals("ohne")) {
                Select realSelect = new Select(this.parkschaden_basic);
                realSelect.selectByVisibleText("nein");
            }
            else if (pSchaden.contentEquals("Normal")) {
                Select realSelect = new Select(this.parkschaden_basic);
                realSelect.selectByVisibleText("ja");
            }
        }

        //Compact
        else if (produkt.contentEquals("Compact")) {
            if (pSchaden.contentEquals("ohne")) {
                Select realSelect = new Select(this.parkschaden_compact);
                realSelect.selectByVisibleText("nein");
            }
            else if (pSchaden.contentEquals("Plus")) {
                Select realSelect = new Select(this.parkschaden_compact);
                realSelect.selectByVisibleText("ja");
            }

        }
        else {
            System.out.println("INVALID - Parkschaden");
        }





////////
//        try {
//            System.out.println("trying to find parkschaden_basic");
//            Select realSelect = new Select(this.parkschaden_basic);
//            if(!pSchaden.contentEquals("NA")) {
//                if (pSchaden.contentEquals("Normal")) {
//                    realSelect.selectByVisibleText("ja");
//                }
//                else {
//                    realSelect.selectByVisibleText("nein");
//                }
//            }
//
//        }
//        catch (ElementNotVisibleException e) {
//
//            System.out.println("parkschaden_basic not visible -> SKIP");
//        }
//
//        finally {
//
//        }

    }



    public void selectUnfallversicherung(String produkt, String unfallversicherung) throws InterruptedException {
        System.out.println("Unfallversicherung: " + unfallversicherung);

        //Basic
        if (produkt.contentEquals("Basic")) {
            if (unfallversicherung.contentEquals("Nein")) {
                Select realSelect = new Select(this.unfallVersicherung_basic);
                realSelect.selectByVisibleText("nein");
            }
            else if (unfallversicherung.contentEquals("Ja")) {
                Select realSelect = new Select(this.parkschaden_basic);
                realSelect.selectByVisibleText("ja");
            }
        }

        //Compact
        else if (produkt.contentEquals("Compact")) {
            if (unfallversicherung.contentEquals("Nein")) {
                Select realSelect = new Select(this.unfallVersicherung_compact);
                realSelect.selectByVisibleText("nein");
            }
            else if (unfallversicherung.contentEquals("Ja")) {
                Select realSelect = new Select(this.unfallVersicherung_compact);
                realSelect.selectByVisibleText("ja");
            }

        }
        else {
            System.out.println("INVALID - Unfallversicherung");
        }

    }

    public void selectBonusschutz(String produkt, String bonusschutz) throws InterruptedException {
        System.out.println("Bonusschutz: " + bonusschutz);

        //Basic
        if (produkt.contentEquals("Basic")) {
            if (bonusschutz.contentEquals("Nein")) {
                Select realSelect = new Select(this.bonusschutz_basic);
                realSelect.selectByVisibleText("nein");
            }
            else if (bonusschutz.contentEquals("Ja")) {
                Select realSelect = new Select(this.bonusschutz_basic);
                realSelect.selectByVisibleText("ja");
            }
        }

        //Compact
        else if (produkt.contentEquals("Compact")) {
            if (bonusschutz.contentEquals("Nein")) {
                Select realSelect = new Select(this.bonusschutz_compact);
                realSelect.selectByVisibleText("nein");
            }
            else if (bonusschutz.contentEquals("Ja")) {
                Select realSelect = new Select(this.bonusschutz_compact);
                realSelect.selectByVisibleText("ja");
            }

        }
        else {
            System.out.println("INVALID - Bonusschutz");
        }

    }

    //TODO: FIX THIS,EXTREMELY UGLY
    public void selectCrashrecorder(String produkt, String telematik) throws InterruptedException {
        System.out.println("Crash Recorder: " + telematik);

        //TODO: check when crash recorder rabatt 15% is displayed

        if (produkt.contentEquals("Basic")) {
            if (telematik.contentEquals("Nein")) {
                Select realSelect = new Select(this.crashrecorder_basic_ohne_rabatt);
                realSelect.selectByVisibleText("nein");
            }
            else if (telematik.contentEquals("Crash Recorder")) {
                Select realSelect = new Select(this.crashrecorder_basic_ohne_rabatt);
                realSelect.selectByVisibleText("ja");
            }
        }

        else if (produkt.contentEquals("Compact")) {
            if (telematik.contentEquals("Nein")) {
                Select realSelect = new Select(this.crashrecorder_compact_ohne_rabatt);
                realSelect.selectByVisibleText("nein");
            }
            else if (telematik.contentEquals("Crash Recorder")) {
                Select realSelect = new Select(this.crashrecorder_compact_ohne_rabatt);
                realSelect.selectByVisibleText("ja");
            }

        }
        else {
            System.out.println("INVALID - Crash Recorder");
        }





//
//
//
//        try {
//            System.out.println("crashrecorder_basic_mit_rabatt");
//
//
//            Select realSelect = new Select(this.crashrecorder_basic_mit_rabatt);
//            if(!telematik.contentEquals("NA")) {
//                if (telematik.contentEquals("Crash Recorder")) {
//                    realSelect.selectByVisibleText("ja");
//                }
//                else {
//                    realSelect.selectByVisibleText("nein");
//                }
//            }
//
//        }
//        catch (ElementNotVisibleException e) {
//
//            System.out.println("crashrecorder_basic_ohne_rabatt");
//
//            Select realSelect = new Select(this.crashrecorder_basic_ohne_rabatt);
//            if(!telematik.contentEquals("NA")) {
//                if (telematik.contentEquals("Crash Recorder")) {
//                    realSelect.selectByVisibleText("ja");
//                }
//                else {
//                    realSelect.selectByVisibleText("nein");
//                }
//            }
//
//        }
//
//        finally {
//
//        }

    }


    public void selectErgaenzungen(
            String produkt,
            String mobility,
            String glasbruch,
            String mitgefuehrteSachen,
            String parkschaden,
            String unfallversicherung,
            String bonusschutz,
            String crashrecorder,
            WebDriver driver) throws InterruptedException {

        this.selectMobility(produkt, mobility);
        this.selectGlasbruch(produkt, glasbruch);
        this.selectMitgefuehrteSachen(produkt, mitgefuehrteSachen, driver);
        this.selectParkschaden(produkt, parkschaden);
        this.selectUnfallversicherung(produkt, unfallversicherung);
        this.selectBonusschutz(produkt, bonusschutz);
        this.selectCrashrecorder(produkt, crashrecorder);
    }



}
