package br.com.searadivina;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SearaDivinaBeApplication  implements CommandLineRunner {

	//@Autowired
	//private S3Service s3Service;
	
	public static void main(String[] args) {
		SpringApplication.run(SearaDivinaBeApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {	
		//s3Service.uploadFile("D:\\temp\\fotos\\images.png");
	}

}
