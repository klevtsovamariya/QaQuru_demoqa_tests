package testData;

import net.datafaker.Faker;
import utils.RandomUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RegistrationFormData {
    Faker faker = new Faker();
    Faker fakerRu = new Faker(new Locale("ru"));
    RandomUtils randomUtils = new RandomUtils() ;

    public String firstName = faker.name().firstName();
    public String lastName = faker.name().lastName();
    public String userEmail = faker.internet().emailAddress();
    public String sex = randomUtils.getRandomGender();
    public String userNumber = faker.phoneNumber().subscriberNumber(10);
    public LocalDate dateOfBirth = faker.timeAndDate().birthday(18, 99);
    public String year = String.valueOf(dateOfBirth.getYear());
    public String month = dateOfBirth.format(DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH));
    public String day = dateOfBirth.format(DateTimeFormatter.ofPattern("dd"));
    public String dateOfBirthText = day + " " + month + "," + year;
    public String currentAddress = fakerRu.address().fullAddress();
    public String hobbies = randomUtils.getRandomHobbies();
    public String subject = randomUtils.getRandomSubject();
    public String state = randomUtils.getRandomState();
    public String city = randomUtils.getRandomCityForState(state);
    public String picture = "1.png";
    public String title = "Thanks for submitting the form";
    public String redBorder = "rgb(220, 53, 69)";
    public String greenBorder = "rgb(25, 135, 84)";
}