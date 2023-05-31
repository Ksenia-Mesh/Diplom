package ru.netology.tests;

import ru.netology.page.BuyWithDebitCard;
import ru.netology.page.BuyWithCreditCard;
import ru.netology.page.OpeningPage;
import com.codeborne.selenide.logevents.SelenideLogger;
import ru.netology.data.DataHelper;
import ru.netology.data.SQL;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.SQL.clearData;

public class CreditCardTest {
    @BeforeAll
    static void setUpAll() {

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @SneakyThrows
    @BeforeEach
    public void setUpEach() {
        String url = System.getProperty("sut.url");
        open(url);
//        SQL.clearData();
    }
    @AfterAll
    static void tearDownAll() {

        SelenideLogger.removeListener("allure");
    }


    @SneakyThrows
    @Test
    void shouldCreditByCardWithStatusApproved() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCreditCard();
        buyWithCredit.fillData(DataHelper.getApprovedCard());
        buyWithCredit.waitNotificationOk();
        String actual = SQL.getCreditCardStatus();
        assertEquals("APPROVED", actual);
    }

    @SneakyThrows
    @Test
    void shouldCreditByCardWithStatusDecline() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCreditCard();
        buyWithCredit.fillData(DataHelper.getDeclinedCard());
        buyWithCredit.waitNotificationError();
        assertEquals("DECLINED", SQL.getCreditCardStatus());
    }

    @Test
    void shouldShortNameInOwnerApproved() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCreditCard();
        buyWithCredit.fillData(DataHelper.getShortNameInOwnerApprovedCard());
        buyWithCredit.waitNotificationOk();
    }

    @Test
    void shouldShortNameInOwnerDeclined() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCreditCard();
        buyWithCredit.fillData(DataHelper.getShortNameInOwnerDeclinedCard());
        buyWithCredit.waitNotificationError();
    }

    @Test
    void shouldInvalidFieldMessageEmptyForm() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCreditCard();
        buyWithCredit.fillData(DataHelper.getEmptyForm());
        buyWithCredit.getInputInvalid();
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageInvalidMonthApproved() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCreditCard();
        buyWithCredit.fillData(DataHelper.getInvalidMonthApprovedCard());
        assertEquals("Неверно указан срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageInvalidMonthDeclined() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCreditCard();
        buyWithCredit.fillData(DataHelper.getInvalidMonthDeclinedCard());
        assertEquals("Неверно указан срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneMonthApproved() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCreditCard();
        buyWithCredit.fillData(DataHelper.getBygoneMonthApprovedCard());
        assertEquals("Неверно указан срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneMonthDeclined() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCreditCard();
        buyWithCredit.fillData(DataHelper.getBygoneMonthDeclinedCard());
        assertEquals("Неверно указан срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageIncompleteField() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCreditCard();
        buyWithCredit.fillData(DataHelper.getIncompleteField());
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldCharactersInFieldOwnerApproved() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCreditCard();
        buyWithCredit.fillData(DataHelper.getCharactersInFieldOwnerApprovedCard());
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldCharactersInFieldOwnerDeclined() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCreditCard();
        buyWithCredit.fillData(DataHelper.getCharactersInFieldOwnerDeclinedCard());
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldOneCharacterInFieldOwnerApprovedCard() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCreditCard();
        buyWithCredit.fillData(DataHelper.getOneCharacterInFieldOwnerApprovedCard());
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldOneCharacterInFieldOwnerDeclinedCard() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCreditCard();
        buyWithCredit.fillData(DataHelper.getOneCharacterInFieldOwnerDeclinedCard());
        assertEquals("Неверный формат", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneYearApprovedCard() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCreditCard();
        buyWithCredit.fillData(DataHelper.getBygoneYearApprovedCard());
        assertEquals("Истёк срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneYearDeclinedCard() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCredit();
        val buyWithCredit = new BuyWithCreditCard();
        buyWithCredit.fillData(DataHelper.getBygoneYearDeclinedCard());
        assertEquals("Истёк срок действия карты", buyWithCredit.getInputInvalid());
    }

    @Test
    void shouldInvalidDebitCard() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithDebitCard();
        debitPage.fillData(DataHelper.getNonExistentCard());
        assertEquals("Ошибка! Банк отказал в проведении операции.", debitPage.getInputInvalid());
    }

    @SneakyThrows
    @Test
    void shouldAmountByCardWithDeclined() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCard();
        val buyWithCard = new BuyWithDebitCard();
        buyWithCard.fillData(DataHelper.getApprovedCard());
        buyWithCard.waitNotificationError();
        assertEquals("null", SQL.getAmountStatus());
    }
}

