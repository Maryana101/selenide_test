package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallbackTest {
    @Test
    void shouldTest() {
        open("http://0.0.0.0:9999");
        SelenideElement form = $("[data-test-id=\"name\"]");
        $("[data-test-id=\"name\"] input").setValue("Оксана Иванова");
        $("[data-test-id=\"phone\"] input").setValue("+79128114397");
        $("[data-test-id=\"agreement\"]").click();
        $("button").click();

        String text = $("[data-test-id=\"order-success\"]").getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());

    }
}
