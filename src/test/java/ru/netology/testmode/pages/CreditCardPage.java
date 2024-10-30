package ru.netology.testmode.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.testmode.data.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditCardPage {

    private final SelenideElement heading = $$("h3").find(exactText("Кредит по данным карты"));

    private final SelenideElement cardNumber = $(".input__control[placeholder=\"0000 0000 0000 0000\"]");
    private final SelenideElement month = $(".input__control[placeholder=\"08\"]");
    private final SelenideElement year = $(".input__control[placeholder=\"22\"]");
    private final SelenideElement holder = $$(".input").find(exactText("Владелец")).$(".input__control");
    private final SelenideElement cvc =$(".input__control[placeholder=\"999\"]");
    private final SelenideElement continueButton = $$("button").find(exactText("Продолжить"));

    private final SelenideElement successNotification = $$(".notification__title").findBy(text("Успешно"));
    private final SelenideElement errorNotification = $$(".notification__title").findBy(text("Ошибка"));

    private final SelenideElement errorFormat = $(byText("Неверный формат"));
    private final SelenideElement errorDate = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement invalidDate = $(byText("Истёк срок действия карты"));
    private final SelenideElement errorEmpty = $(byText("Поле обязательно для заполнения"));

    public CreditCardPage() {
        heading.shouldBe(visible);
    }

    public void inputData(CardInfo card) {
        cardNumber.setValue(card.getCardNumber());
        month.setValue(card.getMonth());
        year.setValue(card.getYear());
        holder.setValue(card.getCardHolder());
        cvc.setValue(card.getCvv());
        continueButton.click();
    }

    public void operationApproved() {
        successNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void operationDeclined() {
        errorNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void formatIsInvalid() {
        errorFormat.shouldBe(visible);
    }

    public void invalidDate() {
       errorDate.shouldBe(visible);
    }

    public void cardExpired() {
        invalidDate.shouldBe(visible);
    }

    public void wrongFormat() {
        errorEmpty.shouldBe(visible);
    }
}