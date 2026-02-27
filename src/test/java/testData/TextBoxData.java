package testData;

import net.datafaker.Faker;

import java.util.Locale;

public class TextBoxData {
    private Faker faker = new Faker();
    private Faker fakerRu = new Faker(new Locale("ru"));

    public String userEmail = faker.internet().emailAddress();
    public String userName = faker.name().fullName();
    public String permanentAddress = fakerRu.address().fullAddress();
    public String currentAddress = fakerRu.address().streetAddress();
}