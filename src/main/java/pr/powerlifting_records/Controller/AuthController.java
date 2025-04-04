package pr.powerlifting_records.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pr.powerlifting_records.Model.LifterModel;
import pr.powerlifting_records.Repository.LifterRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final LifterRepository lifterRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthController(LifterRepository lifterRepository){
        this.lifterRepository = lifterRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerLifter(@RequestBody LifterModel lifter) {
        Optional<LifterModel> existingLifter = lifterRepository.findByName(lifter.getName());

        if (existingLifter.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe.");
        }

        // Criptografa a senha antes de salvar
        lifter.setPassword(passwordEncoder.encode(lifter.getPassword()));
        LifterModel savedLifter = lifterRepository.save(lifter);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLifter);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginLifter(@RequestBody LifterModel lifter) {
        Optional<LifterModel> userOpt = lifterRepository.findByName(lifter.getName());

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nome ou senha inválidos.");
        }

        LifterModel user = userOpt.get();

        // Verifica a senha com BCrypt
        if (passwordEncoder.matches(lifter.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nome ou senha inválidos.");
        }
    }
}

