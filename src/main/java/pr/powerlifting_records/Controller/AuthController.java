package pr.powerlifting_records.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pr.powerlifting_records.Model.LifterModel;
import pr.powerlifting_records.Repository.LifterRepository;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private LifterRepository lifterRepository;

    public AuthController(LifterRepository lifterRepository){
        this.lifterRepository = lifterRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerLifter(@RequestBody LifterModel lifter) {

        if (lifterRepository.findByNameAndPassword(lifter.getName(), lifter.getPassword()).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Já existe usuário com noome ou senha iguais registrado.");
        }
        lifterRepository.save(lifter);
        return ResponseEntity.status(HttpStatus.CREATED).body("Lifter cadastrado.");
    }


    @PostMapping("/login")
    public ResponseEntity<String> loginLifter(@RequestBody LifterModel lifter){

        Optional<LifterModel> lifterOptional = lifterRepository.findByNameAndPassword(lifter.getName(), lifter.getPassword());

        if (lifterOptional.isPresent()){
            return ResponseEntity.ok("Login bem-sucedido!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nome ou senha inválidos.");
        }
    }
    
    
}
