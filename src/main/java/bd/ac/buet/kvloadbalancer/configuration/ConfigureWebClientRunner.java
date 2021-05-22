/*
package bd.ac.buet.kvloadbalancer.configuration;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Log4j2
@Component
public class ConfigureWebClientRunner {
    ConfigureWebClientRunner(WebClient http){
        call(http, "http://api/storage").subscribe(response->log.info("configured response: "+ response));
    }

    static Flux<String> call(WebClient http, String url) {
        return http.get().uri(url).retrieve().bodyToFlux(String.class);
    }
}
*/
