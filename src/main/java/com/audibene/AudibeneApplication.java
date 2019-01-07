package com.audibene;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AudibeneApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AudibeneApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

}
