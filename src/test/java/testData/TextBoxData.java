package testData;

import net.datafaker.Faker;

import java.util.Locale;

import static utils.RandomUtils.getRandomEmail;

public class TextBoxData {
    static Faker faker = new Faker();
    static Faker fakerRu = new Faker(new Locale("ru"));

    public static String userEmail = getRandomEmail();
    public static String userName = faker.name().fullName();
    public static String permanentAddress = fakerRu.address().fullAddress();
    public static String currentAddress = fakerRu.address().streetAddress();
}