package com.sourcemind;

import io.javalin.Javalin;

public class WebApp {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(80);
        app.get("/", context -> context.result("It works!"));

        app.get("/view", context -> {
        });
    }

    public String test(String s) {
        return s.toUpperCase();
    }
}
