package lzgene.newscreening;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling//开启定时器
@SpringBootApplication
public class NewscreeningApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewscreeningApplication.class, args);
	}

}

