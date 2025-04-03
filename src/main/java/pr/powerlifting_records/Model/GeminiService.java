package pr.powerlifting_records.Model;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;

@Service
public class GeminiService {

    private final WebClient webClient;
    private final String API_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-pro:generateContent";
    private final String API_KEY = "AIzaSyCOhzytHlZ8-28q3TNU9XRvYmeu6fCdxRM"; // Substitua pela sua chave de API

    public GeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(API_URL + "?key=" + API_KEY).build();
    }

    public Mono<String> generateText(String prompt) {
        Map<String, Object> requestBody = Map.of(
            "contents", Collections.singletonList(
                Map.of("parts", Collections.singletonList(
                    Map.of("text", prompt)
                ))
            )
        );

        return webClient.post()
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> response.toString());
    }
}