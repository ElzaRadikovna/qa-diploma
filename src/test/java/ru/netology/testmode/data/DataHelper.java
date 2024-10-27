package ru.netology.testmode.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    public static CardInfo getApprovedCard() {
        return new CardInfo("4444444444444441", "10", "24", "IVANOV IVAN", "123");
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo("4444444444444442", "10", "24", "IVANOV IVAN", "123");
    }

    public static CardInfo getEmptyCard() {
        return new CardInfo("", "", "", "", "");
    }

    public static String getMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static CardInfo getCardWithEmptyNumber() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMonth();
        String year = getYear();
        String cvv = faker.number().digits(3);
        return new CardInfo("", month, year, holder, cvv);
    }

    public static CardInfo getShortNumberCard() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMonth();
        String year = getYear();
        String cvv = faker.number().digits(3);
        String number = faker.number().digits(11);
        return new CardInfo(number, month, year, holder, cvv);
    }

    public static CardInfo getInvalidNumberCard() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMonth();
        String year = getYear();
        String cvv = faker.number().digits(3);
        return new CardInfo("1111222233334444", month, year, holder, cvv);
    }

    public static CardInfo getCardWithEmptyMonth() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getYear();
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", "", year, holder, cvv);
    }

    public static CardInfo getCardZeroMonth() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getYear();
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", "00", year, holder, cvv);
    }

    public static CardInfo getCard1DigitMonth() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        //String month = faker.number().digit();
        String year = getYear();
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", "1", year, holder, cvv);
    }

    public static CardInfo getCardMonthOver12() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getYear();
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", "13", year, holder, cvv);
    }

    public static CardInfo getCardWithEmptyYear() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMonth();
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, "", holder, cvv);
    }

    public static CardInfo getCardPreviousYear() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMonth();
        //String year = getYear();
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, "23", holder, cvv);
    }

    public static CardInfo getCardOver5Year() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMonth();
        //String year = getMovedYearInPast(1);
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, "30", holder, cvv);
    }

    public static CardInfo getCardWithEmptyHolder() {
        Faker faker = new Faker();
        //String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMonth();
        String cvv = faker.number().digits(3);
        String year = getYear();
        return new CardInfo("4444444444444441", month, year, "", cvv);
    }

    public static CardInfo getCardHolderCirillic() {
        Faker faker = new Faker(new Locale("ru"));
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMonth();
        String year = getYear();
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, holder, cvv);
    }

    public static CardInfo getCardHolderWithDigit() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + "123";
        String month = getMonth();
        String year = getYear();
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, holder, cvv);
    }

    public static CardInfo getCardHolderWithSymbols() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + "!*";
        String month = getMonth();
        String year = getYear();
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, holder, cvv);
    }

    public static CardInfo getCardShortNameHolder() {
        Faker faker = new Faker();
        String holder = faker.name().firstName();
        String month = getMonth();
        String year = getYear();
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, holder, cvv);
    }

    public static CardInfo getCardWithEmptyCvv() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMonth();
        //String cvv = faker.number().digits(3);
        String year = getYear();
        return new CardInfo("4444444444444441", month, year, holder, "");
    }

    public static CardInfo getCardCvvWith1Digit() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMonth();
        String year = getYear();
        String cvv = faker.number().digits(1);
        return new CardInfo("4444444444444441", month, year, holder, cvv);
    }

    public static CardInfo getCardCvvWith2Digit() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMonth();
        String year = getYear();
        String cvv = faker.number().digits(2);
        return new CardInfo("4444444444444441", month, year, holder, cvv);
    }
}