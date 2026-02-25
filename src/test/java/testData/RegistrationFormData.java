package testData;

import net.datafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static utils.RandomUtils.*;

public class RegistrationFormData {
    static Faker faker = new Faker();
    static Faker fakerRu = new Faker(new Locale("ru"));

    public String firstName = faker.name().firstName();
    public String lastName = faker.name().lastName();
    public String userEmail = getRandomEmail();
    public String sex = getRandomGender();
    public String userNumber = getRandomPhone();
    ;
    public LocalDate dateOfBirth = faker.timeAndDate().birthday(18, 99);
    public String year = String.valueOf(dateOfBirth.getYear());
    public String month = dateOfBirth.format(DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH));
    public String day = dateOfBirth.format(DateTimeFormatter.ofPattern("dd"));
    public String dateOfBirthText = day + " " + month + "," + year;
    public String currentAddress = fakerRu.address().fullAddress();
    public String hobbies = getRandomHobbies();
    public String subject = getRandomSubject();
    public String state = getRandomState();
    public String city = getRandomCityForState(state);
    public String picture = "1.png";
    public String title = "Thanks for submitting the form";
    public String redBorder = "rgb(220, 53, 69)";
    public String greenBorder = "rgb(25, 135, 84)";
}