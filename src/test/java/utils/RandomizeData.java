package utils;

import com.github.javafaker.Faker;

import java.util.Calendar;
import java.util.Locale;

public class RandomizeData {

    Faker faker = new Faker(new Locale("en"));

    public final String[] genderArr = new String[] {"Male","Female","Other"},
    subjectArr = new String[] {"Computer Science","English","Chemistry","Maths","Commerce"},
    hobbiesArr = new String[] {"Reading", "Music", "Sports"},
    picsArr = new String[] {"pic.png","pic2.png","pic3.png"};
    public String[][] statesArr = new String[][] {{"NCR","Delhi","Gurgaon","Noida"},{"Uttar Pradesh","Agra","Lucknow","Merrut"},{"Haryana","Karnal","Panipat"},{"Rajastan","Jaipur","Jaiselmer"}};
    private int _state =  faker.number().numberBetween(0,statesArr.length-1);

    public final String
            firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            phone = (faker.number().numberBetween(100000, 999999) + String.valueOf(faker.number().numberBetween(0000, 9999))),
            gender = faker.options().option(genderArr),
            subject = faker.options().option(subjectArr),
            hobbies = faker.options().option(hobbiesArr),
            pics = faker.options().option(picsArr),
            year = String.valueOf(faker.number().numberBetween(1980, 2100)),
            month = String.valueOf(faker.number().numberBetween(0, 11)),
            day = String.valueOf(faker.number().numberBetween(1, 28)),
            elevenDigitsPhoneNumber = (faker.number().numberBetween(1000000, 9999999) + String.valueOf(faker.number().numberBetween(0000, 9999))),
            currentAddress = faker.address().fullAddress(),
            state = statesArr[_state][0],
            city = faker.options().option(statesArr[_state]);

}

