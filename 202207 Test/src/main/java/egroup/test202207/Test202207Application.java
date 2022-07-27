package egroup.test202207;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class Test202207Application {

	@Value("${spring.application.name:unnamed}")
	private String appName;
	
	public static void main(String[] args) {
		SpringApplication.run(Test202207Application.class, args);
	}

	@RequestMapping(value = "/")
	@ResponseBody
	public String name() {
		return "<h1>嗨你好，"+appName+"運行中。</h1>";
	}
}
