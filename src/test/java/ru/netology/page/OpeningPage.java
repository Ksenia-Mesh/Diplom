package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
public class OpeningPage {
    private SelenideElement heading = $$("h2").find(exactText("Путешествие дня"));
    private SelenideElement debitButton = $$("button").find(exactText("Купить"));
    private SelenideElement creditButton = $$("button").find(exactText("Купить в кредит"));

    public OpeningPage() {
        heading.shouldBe(visible);
    }

    public BuyWithDebitCard openBuyWithCard() {
        debitButton.click();
        return new BuyWithDebitCard();
    }

    public BuyWithCreditCard openBuyWithCredit() {
        creditButton.click();
        return new BuyWithCreditCard();
    }
}
