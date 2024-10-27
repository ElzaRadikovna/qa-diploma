package ru.netology.testmode.test;

import com.codeborne.selenide.logevents.SelenideLogger;

import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.testmode.data.DataHelper;
import ru.netology.testmode.data.SQLHelper;
import ru.netology.testmode.pages.PaymentPage;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditTest {
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
    void positiveTestCreditCard() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCreditCard();
        payment.inputData(DataHelper.getApprovedCard());
        payment.operationApproved();
        assertEquals("APPROVED", SQLHelper.getCreditRequestStatus());
    }

    @Test
    void negativeTestCreditCard() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCreditCard();
        payment.inputData(DataHelper.getDeclinedCard());
        payment.operationDeclined();
        assertEquals("DECLINED", SQLHelper.getCreditRequestStatus());
    }

    @Test
    void shouldNotSetCreditWithEmptyNumber() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCreditCard();
        payment.inputData(DataHelper.getCardWithEmptyNumber());
        payment.formatIsInvalid();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetCreditWithShortNumber() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCreditCard();
        payment.inputData(DataHelper.getShortNumberCard());
        payment.formatIsInvalid();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetInvalidNumberCreditCard() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCreditCard();
        payment.inputData(DataHelper.getInvalidNumberCard());
        payment.operationDeclined();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetCreditWithEmptyMonth() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCreditCard();
        payment.inputData(DataHelper.getCardWithEmptyMonth());
        payment.formatIsInvalid();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetCreditCardZeroMonth() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCreditCard();
        payment.inputData(DataHelper.getCardZeroMonth());
        payment.invalidDate();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetCreditWith1DigitMonth() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCreditCard();
        payment.inputData(DataHelper.getCard1DigitMonth());
        payment.formatIsInvalid();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldSetCreditBeMonthOver12() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCreditCard();
        payment.inputData(DataHelper.getCardMonthOver12());
        payment.invalidDate();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetCreditWithEmptyYear() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCreditCard();
        payment.inputData(DataHelper.getCardWithEmptyYear());
        payment.formatIsInvalid();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetCreditWithPreviousYear() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCreditCard();
        payment.inputData(DataHelper.getCardPreviousYear());
        payment.cardExpired();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetCreditOver5year() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCreditCard();
        payment.inputData(DataHelper.getCardOver5Year());
        payment.invalidDate();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetCreditWithEmptyHolder() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCreditCard();
        payment.inputData(DataHelper.getCardWithEmptyHolder());
        payment.wrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetCreditWithCirillicHolder() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCreditCard();
        payment.inputData(DataHelper.getCardHolderCirillic());
        payment.formatIsInvalid();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetCreditWithHolderDigit() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCreditCard();
        payment.inputData(DataHelper.getCardHolderWithDigit());
        payment.formatIsInvalid();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetCreditWithHolderSymbols() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCreditCard();
        payment.inputData(DataHelper.getCardHolderWithSymbols());
        payment.formatIsInvalid();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetCreditWithShortNameHolder() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCreditCard();
        payment.inputData(DataHelper.getCardShortNameHolder());
        payment.formatIsInvalid();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetCreditWithEmptyCvv() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCreditCard();
        payment.inputData(DataHelper.getCardWithEmptyCvv());
        payment.formatIsInvalid();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetCreditWith1DigitCvv() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCreditCard();
        payment.inputData(DataHelper.getCardCvvWith1Digit());
        payment.formatIsInvalid();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void shouldNotSetCreditWith2DigitCvv() {
        val startPage = new PaymentPage();
        val payment = startPage.paymentByCreditCard();
        payment.inputData(DataHelper.getCardCvvWith2Digit());
        payment.formatIsInvalid();
        assertEquals("0", SQLHelper.getOrderCount());
    }
  }