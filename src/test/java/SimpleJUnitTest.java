import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.text;


public class SimpleJUnitTest {

    int result;

    @BeforeAll
    static void beforeAll() {
        System.out.println("###    Before All");
    }


    @BeforeEach
    void beforeEach() {
        System.out.println("###  beforeEach()");
        result = getResult();
    }


    @Test
    void firstTest() {
        System.out.println("###     firstTest()");
        Assertions.assertTrue(result>2);
    }

    @Test
    void secondTest() {
        System.out.println("###     secondTest()");
        Assertions.assertTrue(result>2);
    }

    @Test
    void thirdTest() {
        System.out.println("###     thirdTest()");
        Assertions.assertTrue(result>2);
    }

    private int getResult() {
        return 3;
    }

    @AfterEach
    void afterEach() {
        System.out.println("###  afterEach()");
        result = 0;
    }

    @AfterAll
    static void afterAll() {
        System.out.println("###  afterAll()");
    }
}
