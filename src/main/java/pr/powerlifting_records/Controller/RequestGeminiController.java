package pr.powerlifting_records.Controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gemini")
public class RequestGeminiController {

    @PostMapping("/send-to-flask/{lifterId}")
    public ResponseEntity<String> sendToFlask(@PathVariable Long lifterId) {
        RestTemplate restTemplate = new RestTemplate();
        String flaskUrl = "http://localhost:5000/treino";
        String prsUrl = "http://localhost:8080/lifter/" + lifterId + "/prs";

        try {
            // Buscar PRs diretamente
            ResponseEntity<List<Map<String, Object>>> prsResponse =
                restTemplate.exchange(
                    prsUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Map<String, Object>>>() {}
                );

            List<Map<String, Object>> prs = prsResponse.getBody();
            if (prs == null || prs.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Lista de PRs está vazia ou não pôde ser carregada.");
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<List<Map<String, Object>>> request = new HttpEntity<>(prs, headers);

            // Enviar para o Flask (que espera uma lista JSON de PRs)
            ResponseEntity<String> response = restTemplate.postForEntity(flaskUrl, request, String.class);
            return ResponseEntity.ok(response.getBody());

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao enviar PRs para o Flask: " + e.getMessage());
        }
    }
}
