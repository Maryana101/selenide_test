package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Selenide.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallbackTest {

    @BeforeEach
    void setUp() {
        open("http://0.0.0.0:9999");
        SelenideElement form = $("[data-test-id=\"name\"]");

    }

    @Test
    void shouldSubmitRequest() {
        $("[data-test-id=\"name\"] input").setValue("Оксана Иванова");
        $("[data-test-id=\"phone\"] input").setValue("+79128114397");
        $("[data-test-id=\"agreement\"]").click();
        $("button").click();
        String text = $("[data-test-id=\"order-success\"]").getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    void shouldNotifyAboutInvalidName() {
        $("[data-test-id=\"name\"] input").setValue("Oxana Ivanova");
        $("button").click();
        String text = $("[data-test-id=\"name\"]").$("[class=\"input__sub\"]").getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }

    @Test
    void shouldNotifyAboutEmptyName() {

        $("[data-test-id=\"phone\"] input").setValue("+79128114397");
        $("[data-test-id=\"agreement\"]").click();
        $("button").click();
        String text = $("[data-test-id=\"name\"]").$("[class=\"input__sub\"]").getText();
        assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    void shouldNotifyAboutInvalidPhone() {
        $("[data-test-id=\"name\"] input").setValue("Оксана Иванова");
        $("[data-test-id=\"phone\"] input").setValue("+791281143");
        $("button").click();
        String text = $("[data-test-id=\"phone\"]").$("[class=\"input__sub\"]").getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    void shouldNotifyAboutEmptyPhone() {
        $("[data-test-id=\"name\"] input").setValue("Оксана Иванова");
        $("button").click();
        String text = $("[data-test-id=\"phone\"]").$("[class=\"input__sub\"]").getText();
        assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    void shouldRedColorForUncheckedAgreementText() {
        $("[data-test-id=\"name\"] input").setValue("Оксана Иванова");
        $("[data-test-id=\"phone\"] input").setValue("+79128114397");
        $("button").click();
        String text = $("[data-test-id=\"agreement\"]").$("[class=\"checkbox__text\"]").getCssValue("color");
        assertEquals("rgba(255, 92, 92, 1)", text.trim());
    }

}
