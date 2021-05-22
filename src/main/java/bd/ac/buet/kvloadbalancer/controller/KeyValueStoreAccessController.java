package bd.ac.buet.kvloadbalancer.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class KeyValueStoreAccessController {
    @LoadBalanced
    private final WebClient.Builder loadBalancedWebClientBuilder;
    private final DiscoveryClient discoveryClient;
    private final ReactorLoadBalancerExchangeFilterFunction lbFunction;
    private final RestTemplate restTemplate;

    public KeyValueStoreAccessController(WebClient.Builder loadBalancedWebClientBuilder, DiscoveryClient discoveryClient, ReactorLoadBalancerExchangeFilterFunction lbFunction, RestTemplate restTemplate) {
        this.loadBalancedWebClientBuilder = loadBalancedWebClientBuilder;
        this.discoveryClient = discoveryClient;
        this.lbFunction = lbFunction;
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/fetch")
    public Mono<String> fetchByKey(@RequestParam("key") String key){
        Map<String, String> providedKey = new HashMap<>();
        providedKey.put("key", key);
        return loadBalancedWebClientBuilder
                .filter(lbFunction)
                .build()
                .get()
                .uri("http://keyvaluestore")
                .retrieve()
                .bodyToMono(String.class);
    }


    @RequestMapping("/hello")
    public Mono<String> sayHello(){
        Map<String, String> providedKey = new HashMap<>();

        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("keyvaluestore");
        return loadBalancedWebClientBuilder
                .baseUrl("http://keyvaluestore")
                .filter(lbFunction)
                .build()
                .get()
                .uri("/hello")
                .retrieve()
                .bodyToMono(String.class);
    }

    @RequestMapping("/test-hello")
    public Mono<String> testHello(){
        return Mono.just("Test Hello");
    }
}
