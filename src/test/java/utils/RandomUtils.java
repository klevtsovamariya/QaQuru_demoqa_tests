package utils;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.String.format;

public class RandomUtils {

    public static String getRandomString(int length) {
        String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        SecureRandom rnd = new SecureRandom();

        for (int i = 0; i < length; i++)
            result.append(LETTERS.charAt(rnd.nextInt(LETTERS.length())));

        return result.toString();
    }

    public static String getRandomEmail() {
        return format("%s@%s.com", getRandomString(8), getRandomString(5));
    }

    public static int getRandomInt(int min, int max) {

        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String getRandomPhone() {
        String phoneTemplate = "8%s%s%s";

        return format(phoneTemplate,
                getRandomInt(100, 999),
                getRandomInt(100, 999),
                getRandomInt(100, 999)
        );
    }

    public static String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};

        return getRandomItemFromStringArray(genders);
    }

    public static String getRandomHobbies() {
        String[] hobbies = {"Sports", "Reading", "Music"};

        return getRandomItemFromStringArray(hobbies);
    }

    public static String getRandomSubject() {
        String[] subject = {"English", "Social Studies", "Chemistry", "Maths", "Economics", "Arts"};

        return getRandomItemFromStringArray(subject);
    }

    public static String getRandomState() {
        String[] state = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};

        return getRandomItemFromStringArray(state);
    }

    public static String getRandomCityForState(String state) {
        return switch (state) {
            case "NCR" -> getRandomItemFromStringArray(new String[]{"Delhi", "Gurgaon", "Noida"});
            case "Uttar Pradesh" -> getRandomItemFromStringArray(new String[]{"Agra", "Lucknow", "Merrut"});
            case "Haryana" -> getRandomItemFromStringArray(new String[]{"Karnal", "Panipat"});
            case "Rajasthan" -> getRandomItemFromStringArray(new String[]{"Jaipur", "Jaiselmer"});
            default -> throw new IllegalStateException("Unexpected value: " + state);
        };
    }

    public static String getRandomItemFromStringArray(String[] stringArray) {
        int arrayLength = stringArray.length;
        int randomIndex = getRandomInt(0, arrayLength - 1);

        return stringArray[randomIndex];
    }
}