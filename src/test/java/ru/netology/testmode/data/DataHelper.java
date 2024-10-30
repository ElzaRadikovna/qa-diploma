package ru.netology.testmode.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {


    public static CardInfo getApprovedCard() {
        return new CardInfo(approvedCard(), getMonth(), getYear(), getHolder(), getCvv());
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo(declinedCard(), getMonth(), getYear(), getHolder(), getCvv());
    }

    // public static String getEmptyCard() {
    // return new CardInfo("", "", "", "", ""); }

    public static String approvedCard() {
        return "4444444444444441";
    }
    public static String declinedCard() {
        return "4444444444444442";
    }

    public static String getMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }


    public static String getHolder() {
        Faker faker = new Faker(new Locale("EN"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getCvv() {
        Faker faker = new Faker();
        return faker.number().digits(3);
    }

    public static CardInfo getCardWithEmptyNumber() {
        return new CardInfo("", getMonth(),getYear(), getHolder(), getCvv());
    }

    public static CardInfo getShortNumberCard() {
        Faker faker = new Faker();
        return new CardInfo(faker.number().digits(11), getMonth(), getYear(), getHolder(), getCvv() );
    }

    public static CardInfo getInvalidNumberCard() {
        return new CardInfo("1111222233334444", getMonth(), getYear(), getHolder(), getCvv() );
    }

    public static CardInfo getCardWithEmptyMonth() {
        return new CardInfo(approvedCard(), "", getYear(), getHolder(), getCvv() );
    }

    public static CardInfo getCardZeroMonth() {
        return new CardInfo(approvedCard(), "00", getYear(), getHolder(), getCvv() );
    }

    public static CardInfo getCard1DigitMonth() {
        return new CardInfo(approvedCard(), "1", getYear(), getHolder(), getCvv() );
    }

    public static CardInfo getCardMonthOver12() {
        return new CardInfo(approvedCard(), "13", getYear(), getHolder(), getCvv() );
    }

    public static CardInfo getCardWithEmptyYear() {

        return new CardInfo(approvedCard(), getMonth(), "", getHolder(), getCvv() );
    }

    public static CardInfo getCardPreviousYear() {
        return new CardInfo(approvedCard(), getMonth(), "23", getHolder(), getCvv() );
    }

    public static CardInfo getCardOver5Year() {
        return new CardInfo(approvedCard(), getMonth(), "30", getHolder(), getCvv() );
    }

    public static CardInfo getCardWithEmptyHolder() {
        return new CardInfo(approvedCard(), getMonth(), getYear(),  "", getCvv());
    }

    public static CardInfo getCardHolderCirillic() {
        Faker faker = new Faker(new Locale("ru"));
        return new CardInfo(approvedCard(), getMonth(), getYear(), faker.name().lastName(), getCvv());
    }

    public static CardInfo getCardHolderWithDigit() {
        Faker faker = new Faker();
        return new CardInfo(approvedCard(), getMonth(), getYear(), faker.name().firstName() + "123", getCvv());
    }

    public static CardInfo getCardHolderWithSymbols() {
        Faker faker = new Faker();
        return new CardInfo(approvedCard(), getMonth(), getYear(), faker.name().firstName() + "!*", getCvv());
    }

    public static CardInfo getCardShortNameHolder() {
        return new CardInfo(approvedCard(), getMonth(), getYear(), "I", getCvv());
    }

    public static CardInfo getCardWithEmptyCvv() {

        return new CardInfo(approvedCard(), getMonth(), getYear(), getHolder(), "");
    }

    public static CardInfo getCardCvvWith1Digit() {
        Faker faker = new Faker();
        return new CardInfo(approvedCard(), getMonth(), getYear(), getHolder(), faker.number().digits(1));
    }

    public static CardInfo getCardCvvWith2Digit() {
        Faker faker = new Faker();
        return new CardInfo(approvedCard(), getMonth(), getYear(), getHolder(), faker.number().digits(2));
    }
}