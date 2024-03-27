package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

@SpringBootTest
public class MessageSourceTEst {
    @Autowired
    MessageSource ms;

    @Test
    void helloMeesage() {
        System.out.println(Locale.getDefault());
        String hello = ms.getMessage("hello", null, null);
        Assertions.assertThat(hello).isEqualTo("안녕");
    }

    @Test
    void notFoundMessageCode() {
        Assertions.assertThatThrownBy(() -> ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class); // 메세지가 없으면 NoSuchMessageException 발생
    }

    @Test
    void notFoundMessageCodeDefaultMessage() {
        String result = ms.getMessage("no_code", null, "기본 메세지", null);
        Assertions.assertThat(result).isEqualTo("기본 메세지");
    }

    @Test
    void argumentMessage() {
        String result = ms.getMessage("hello.name", new Object[]{"spring"}, null);
        Assertions.assertThat(result).isEqualTo("안녕 spring");
    }

    @Test
    void defulatLang() {
        Assertions.assertThat(ms.getMessage("hello", null, null)).isEqualTo("안녕");
        Assertions.assertThat(ms.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
    }

    @Test
    void enLang() {
        Assertions.assertThat(ms.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
    }
}
