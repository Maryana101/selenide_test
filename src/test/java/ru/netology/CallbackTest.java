package ru.netology;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Visible;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallbackTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSubmitRequest() {

        $("[data-test-id=name] input").setValue("Василий Озеров");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldBe(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNotifyAboutInvalidName() {
        $("[data-test-id=name] input").setValue("Vasili Ozerov");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name]").$(".input__sub").shouldBe(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotifyAboutInvalidName2() {
        $("[data-test-id=name] input").setValue("Василий Озеров!");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name]").$(".input__sub").shouldBe(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotifyAboutEmptyName() {
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name]").$(".input__sub").shouldBe(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotifyAboutInvalidPhone() {
        $("[data-test-id=name] input").setValue("Василий Озеров");
        $("[data-test-id=phone] input").setValue("+7927000000");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone]").$(".input__sub").shouldBe(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotifyAboutInvalidPhone2() {
        $("[data-test-id=name] input").setValue("Василий Озеров");
        $("[data-test-id=phone] input").setValue("89270000000");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone]").$(".input__sub").shouldBe(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotifyAboutEmptyPhone() {
        $("[data-test-id=name] input").setValue("Василий Озеров");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone]").$(".input__sub").shouldBe(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotifyAboutUncheckedAgreement() {
        $("[data-test-id=name] input").setValue("Василий Озеров");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("button").click();
        $(".input_invalid").shouldBe(visible);
    }
}
