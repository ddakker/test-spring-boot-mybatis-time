package pe.kr.ddakker.test.spring.boot.mybatis.time.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
public class User {
    int seq;
    String name;
    int age;
    LocalDate birthday;
    ZonedDateTime deleteTime;
    ZonedDateTime created;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
