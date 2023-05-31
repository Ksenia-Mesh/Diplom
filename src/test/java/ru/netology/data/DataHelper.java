package ru.netology.data;

import com.github.javafaker.Faker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Random;
public class DataHelper {
    private DataHelper(){
    }
    private static String getMonth() {
        LocalDate localDate = LocalDate.now();
        int month = localDate.getMonthValue();
        return String.format("%02d", month);
    }

    private static String getBygoneMonth() {
        LocalDate localDate = LocalDate.now();
        int month = localDate.minusMonths(1).getMonthValue();
        return String.format("%02d", month);
    }

    private static String getYear() {
        DateFormat df = new SimpleDateFormat("yy");
        return df.format(Calendar.getInstance().getTime());
    }

    private static String getBygoneYear() {
        LocalDate localDate = LocalDate.now();
        int year = localDate.minusYears(1).getYear();
        return String.format("%02d", year);
    }

    private static String getName() {
        Faker faker = new Faker();
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    private static String getCvc() {
        Random random = new Random();
        int cvc = random.nextInt((1000 - 1));
        return String.format("%03d", cvc);
    }

    private static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    private static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static InformationCard getNonExistentCard() {
        return new InformationCard("7777 7777 7777 7777", getMonth(), getYear(), getName(), getCvc());
    }

    public static InformationCard getApprovedCard() {
        return new InformationCard(getApprovedCardNumber(), getMonth(), getYear(), getName(), getCvc());
    }

    public static InformationCard getDeclinedCard() {
        return new InformationCard(getDeclinedCardNumber(), getMonth(), getYear(), getName(), getCvc());
    }

    public static InformationCard getShortNameInOwnerApprovedCard() {
        return new InformationCard(getApprovedCardNumber(), getMonth(), getYear(), "In", getCvc());
    }

    public static InformationCard getShortNameInOwnerDeclinedCard() {
        return new InformationCard(getDeclinedCardNumber(), getMonth(), getYear(), "In", getCvc());
    }

    public static InformationCard getEmptyForm() {
        return new InformationCard();
    }

    public static InformationCard getInvalidMonthApprovedCard() {
        return new InformationCard(getApprovedCardNumber(), "13", getYear(), getName(), getCvc());
    }

    public static InformationCard getInvalidMonthDeclinedCard() {
        return new InformationCard(getDeclinedCardNumber(), "13", getYear(), getName(), getCvc());
    }

    public static InformationCard getBygoneMonthApprovedCard() {
        return new InformationCard(getApprovedCardNumber(), getBygoneMonth(), getYear(), getName(), getCvc());
    }

    public static InformationCard getBygoneMonthDeclinedCard() {
        return new InformationCard(getDeclinedCardNumber(), getBygoneMonth(), getYear(), getName(), getCvc());
    }

    public static InformationCard getIncompleteField() {
        return new InformationCard("4444 4444 4444 444", "1", "2", "A", "11");
    }

    public static InformationCard getCharactersInFieldOwnerApprovedCard() {
        return new InformationCard(getApprovedCardNumber(), getMonth(), getYear(), "<#%^*&$@>", getCvc());
    }

    public static InformationCard getCharactersInFieldOwnerDeclinedCard() {
        return new InformationCard(getDeclinedCardNumber(), getMonth(), getYear(), "<#%^*&$@>", getCvc());
    }

    public static InformationCard getOneCharacterInFieldOwnerApprovedCard() {
        return new InformationCard(getApprovedCardNumber(), getMonth(), getYear(), "I", getCvc());
    }

    public static InformationCard getOneCharacterInFieldOwnerDeclinedCard() {
        return new InformationCard(getDeclinedCardNumber(), getMonth(), getYear(), "I", getCvc());
    }

    public static InformationCard getBygoneYearApprovedCard() {
        return new InformationCard(getApprovedCardNumber(), getMonth(), getBygoneYear(), getName(), getCvc());
    }

    public static InformationCard getBygoneYearDeclinedCard() {
        return new InformationCard(getApprovedCardNumber(), getMonth(), getBygoneYear(), getName(), getCvc());
    }
}
