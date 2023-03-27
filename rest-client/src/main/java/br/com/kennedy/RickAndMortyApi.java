package br.com.kennedy;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import io.netty.resolver.DefaultAddressResolverGroup;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

/**
 * Hello world!
 *
 */
public class RickAndMortyApi {
    //https://catfact.ninja/facts
    //https://github.com/Kamilahsantos/Consumindo-api-rick-morty-com-webclient/blob/main/src/main/java/com/kamilacode/webclientrickandmortyapi/client/RickAndMortyClient.java
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RickAndMortyApi.class);
    
    private static final String SERVICE_URL = "https://rickandmortyapi.com/api/character";

    private static final HttpClient createHttpClientConnector () {
        LOGGER.info("Criando projeto");
        HttpClient httpClient = HttpClient.create()
                .resolver(DefaultAddressResolverGroup.INSTANCE)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                .responseTimeout(Duration.ofMillis(5000))
                .baseUrl(SERVICE_URL)
                .doOnConnected(conn -> 
                  conn.addHandlerLast(new ReadTimeoutHandler(3000, TimeUnit.MILLISECONDS))
                    .addHandlerLast(new WriteTimeoutHandler(3000, TimeUnit.MILLISECONDS)));
        return httpClient;
    }
    
    public static void main(String[] args) {
        System.out.println("                     *****************   ");
        
        
        WebClient webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(createHttpClientConnector()))
                .build();
        
        
        String aza = webClient.get()
                .uri("/2")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("verifique os parâmetros informados")))
                .bodyToMono(String.class).block(Duration.ofSeconds(3l));
        System.out.println(aza);
           
      System.out.println("****************************");
        
        
    }
}
