package ru.netology.tests;

import ru.netology.page.BuyWithDebitCard;
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

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebitCardTest {
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
    void shouldDebitByCardWithApproved() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCard();
        val buyWithCard = new BuyWithDebitCard();
        buyWithCard.fillData(DataHelper.getApprovedCard());
        buyWithCard.waitNotificationOk();
        assertEquals("APPROVED", SQL.getDebitCardStatus());
    }

    @SneakyThrows
    @Test
    void shouldDebitByCardWithDecline() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCard();
        val buyWithCard = new BuyWithDebitCard();
        buyWithCard.fillData(DataHelper.getDeclinedCard());
        buyWithCard.waitNotificationError();
        assertEquals("DECLINED", SQL.getDebitCardStatus());
    }

    @Test
    void shouldShortNameInOwnerApproved() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCard();
        val buyWithCard = new BuyWithDebitCard();
        buyWithCard.fillData(DataHelper.getShortNameInOwnerApprovedCard());
        buyWithCard.waitNotificationOk();
    }

    @Test
    void shouldShortNameInOwnerDeclined() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithDebitCard();
        debitPage.fillData(DataHelper.getShortNameInOwnerDeclinedCard());
        debitPage.waitNotificationError();
    }

    @Test
    void shouldInvalidFieldMessageEmptyForm() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithDebitCard();
        debitPage.fillData(DataHelper.getEmptyForm());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageInvalidMonthApproved() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithDebitCard();
        debitPage.fillData(DataHelper.getInvalidMonthApprovedCard());
        assertEquals("Неверно указан срок действия карты", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageInvalidMonthDeclined() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithDebitCard();
        debitPage.fillData(DataHelper.getInvalidMonthDeclinedCard());
        assertEquals("Неверно указан срок действия карты", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneMonthApproved() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithDebitCard();
        debitPage.fillData(DataHelper.getBygoneMonthApprovedCard());
        assertEquals("Неверно указан срок действия карты", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneMonthDeclined() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithDebitCard();
        debitPage.fillData(DataHelper.getBygoneMonthDeclinedCard());
        assertEquals("Неверно указан срок действия карты", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageIncompleteField() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithDebitCard();
        debitPage.fillData(DataHelper.getIncompleteField());
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    @Test
    void shouldCharactersInFieldOwnerApproved() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithDebitCard();
        debitPage.fillData(DataHelper.getCharactersInFieldOwnerApprovedCard());
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    @Test
    void shouldCharactersInFieldOwnerDeclined() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithDebitCard();
        debitPage.fillData(DataHelper.getCharactersInFieldOwnerDeclinedCard());
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    @Test
    void shouldOneCharacterInFieldOwnerApproved() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithDebitCard();
        debitPage.fillData(DataHelper.getCharactersInFieldOwnerApprovedCard());
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    @Test
    void shouldOneCharacterInFieldOwnerDeclined() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithDebitCard();
        debitPage.fillData(DataHelper.getOneCharacterInFieldOwnerDeclinedCard());
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneYearApproved() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithDebitCard();
        debitPage.fillData(DataHelper.getBygoneYearApprovedCard());
        assertEquals("Истёк срок действия карты", debitPage.getInputInvalid());
    }

    @Test
    void shouldInvalidFieldMessageBygoneYearDeclined() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCard();
        val debitPage = new BuyWithDebitCard();
        debitPage.fillData(DataHelper.getBygoneYearDeclinedCard());
        assertEquals("Истёк срок действия карты", debitPage.getInputInvalid());
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
    void shouldAmountByCardWithApproved() {
        OpeningPage startPage = new OpeningPage();
        startPage.openBuyWithCard();
        val buyWithCard = new BuyWithDebitCard();
        buyWithCard.fillData(DataHelper.getApprovedCard());
        buyWithCard.waitNotificationOk();
        assertEquals("45000", SQL.getAmountStatus());
    }

}
