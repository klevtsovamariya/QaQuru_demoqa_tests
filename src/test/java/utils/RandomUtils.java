package utils;

import net.datafaker.Faker;

public class RandomUtils {
    Faker faker = new Faker();

    public String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};

        return faker.options().option(genders);
    }

    public String getRandomHobbies() {
        String[] hobbies = {"Sports", "Reading", "Music"};

        return faker.options().option(hobbies);
    }

    public String getRandomSubject() {
        String[] subject = {"English", "Social Studies", "Chemistry", "Maths", "Economics", "Arts"};

        return faker.options().option(subject);
    }

    public String getRandomState() {
        String[] state = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};

        return faker.options().option(state);
    }

    public String getRandomCityForState(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option(new String[]{"Delhi", "Gurgaon", "Noida"});
            case "Uttar Pradesh" -> faker.options().option(new String[]{"Agra", "Lucknow", "Merrut"});
            case "Haryana" -> faker.options().option(new String[]{"Karnal", "Panipat"});
            case "Rajasthan" -> faker.options().option(new String[]{"Jaipur", "Jaiselmer"});
            default -> throw new IllegalStateException("Unexpected value: " + state);
        };
    }
}