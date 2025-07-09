package utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class RandomizeData {

    Faker faker = new Faker(new Locale("en"));

    public String
            firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            phone = (faker.number().numberBetween(100000, 999999) + String.valueOf(faker.number().numberBetween(0000, 9999))),
            gender = faker.options().option("Male","Female","Other"),
            subject = faker.options().option("Computer Science","English","Chemistry","Maths","Commerce"),
            hobbies =  faker.options().option("Reading", "Music", "Sports"),
            day = setDay(faker.number().numberBetween(1, 28)),
            pics = faker.options().option("pic.png","pic2.png","pic3.png"),
            year = String.valueOf(faker.number().numberBetween(1980, 2100)),
            month = String.valueOf(faker.number().numberBetween(1, 11)),
            elevenDigitsPhoneNumber = (faker.number().numberBetween(1000000, 9999999) + String.valueOf(faker.number().numberBetween(0000, 9999))),
            currentAddress = faker.address().fullAddress(),
            state = faker.options().option("NCR","Uttar Pradesh","Haryana","Rajasthan"),
            city = setCity(state);


    private String setDay(int day) {
        if (day < 10)
        {
            return Integer.toString(day = Integer.parseInt("0" + day));
        }
        else {
            return Integer.toString(day);
        }
    }

    private String setCity(String state) {
                return switch (state) {
                    case "NCR" ->  faker.options().option("Delhi","Gurgaon","Noida");
                    case "Uttar Pradesh" ->  faker.options().option("Agra","Lucknow","Merrut");
                    case "Haryana" -> faker.options().option("Karnal","Panipat");
                    case "Rajasthan" -> faker.options().option("Jaipur","Jaiselmer");
                    default -> null;
                };
            }

}

