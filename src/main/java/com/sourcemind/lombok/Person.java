package com.sourcemind.lombok;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Person {
    private String name;
    private int age;

    public static void main(String[] args) {
        log.info("test");
        log.error("whoops");
    }
}
