package ru.netology.testmode.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;

import ru.netology.testmode.data.DataHelper;
import ru.netology.testmode.data.SQLHelper;
import ru.netology.testmode.pages.PaymentPage;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CardTest {
    public static String url = System.getProperty("sut.url");

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void openPage() {
        open(url);
    }

    @AfterEach
    public void cleanBase() {
        SQLHelper.cleanDB();
    }



    @Test
    void positiveTestCard() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCard();
        payment.inputData(DataHelper.getApprovedCard());
        payment.operationApproved();
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }

    @Test
    void negativeTestCard() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCard();
        payment.inputData(DataHelper.getDeclinedCard());
        payment.operationDeclined();
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
    }

    @Test
    void shouldNotSetWithEmptyNumber() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCard();
        payment.inputData(DataHelper.getCardWithEmptyNumber());
        payment.formatIsInvalid();
        assertEquals("0", SQLHelper.getOrderCount());
    }


    @Test
    void shouldNotSetShortNumberCard() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCard();
        payment.inputData(DataHelper.getShortNumberCard());
        payment.formatIsInvalid();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetInvalidNumberCard() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCard();
        payment.inputData(DataHelper.getInvalidNumberCard());
        payment.formatIsInvalid();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetWithEmptyMonth() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCard();
        payment.inputData(DataHelper.getCardWithEmptyMonth());
        payment.formatIsInvalid();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetCardZeroMonth() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCard();
        payment.inputData(DataHelper.getCardZeroMonth());
        payment.invalidDate();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSet1DigitMonth() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCard();
        payment.inputData(DataHelper.getCard1DigitMonth());
        payment.formatIsInvalid();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldSetBeMonthOver12() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCard();
        payment.inputData(DataHelper.getCardMonthOver12());
        payment.invalidDate();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetWithEmptyYear() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCard();
        payment.inputData(DataHelper.getCardWithEmptyYear());
        payment.formatIsInvalid();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetWithPreviousYear() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCard();
        payment.inputData(DataHelper.getCardPreviousYear());
        payment.cardExpired();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetOver5year() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCard();
        payment.inputData(DataHelper.getCardOver5Year());
        payment.invalidDate();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetWithEmptyHolder() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCard();
        payment.inputData(DataHelper.getCardWithEmptyHolder());
        payment.wrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetWithCirillicHolder() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCard();
        payment.inputData(DataHelper.getCardHolderCirillic());
        payment.formatIsInvalid();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetWithHolderDigit() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCard();
        payment.inputData(DataHelper.getCardHolderWithDigit());
        payment.formatIsInvalid();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetWithHolderSymbols() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCard();
        payment.inputData(DataHelper.getCardHolderWithSymbols());
        payment.formatIsInvalid();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetWithShortNameHolder() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCard();
        payment.inputData(DataHelper.getCardShortNameHolder());
        payment.formatIsInvalid();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetWithEmptyCvv() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCard();
        payment.inputData(DataHelper.getCardWithEmptyCvv());
        payment.formatIsInvalid();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetWith1DigitCvv() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCard();
        payment.inputData(DataHelper.getCardCvvWith1Digit());
        payment.formatIsInvalid();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetWith2DigitCvv() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCard();
        payment.inputData(DataHelper.getCardCvvWith2Digit());
        payment.formatIsInvalid();
        assertEquals("0", SQLHelper.getOrderCount());
    }
}
