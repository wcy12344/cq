package org.example.cq;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Slf4j
@MapperScan("org.example.cq.mapper")
public class CqApplication {
    public static void main(String[] args) {
        SpringApplication.run(CqApplication.class, args);
        log.info("项目启动成功");
    }
}
