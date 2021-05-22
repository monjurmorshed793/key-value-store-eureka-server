package bd.ac.buet.kvloadbalancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class KvloadBalancerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KvloadBalancerApplication.class, args);
	}

}
