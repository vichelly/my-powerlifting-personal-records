package pr.powerlifting_records.Controller;

import org.springframework.web.bind.annotation.*;

import pr.powerlifting_records.Model.LifterModel;
import pr.powerlifting_records.Model.PrModel;
import pr.powerlifting_records.Repository.LifterRepository;
import pr.powerlifting_records.Repository.PrRepository;  // Adicione esta linha

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



@RestController
@RequestMapping("/lifter")
public class LifterController {
    
    private final LifterRepository lifterRepository;
    private final PrRepository prRepository;  // Declare a variável para o prRepository

    // Injete o prRepository no construtor
    public LifterController(LifterRepository lifterRepository, PrRepository prRepository) {
        this.lifterRepository = lifterRepository;
        this.prRepository = prRepository;
    }

    @GetMapping()
    public List<LifterModel> listAllLifters() {
        return lifterRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public LifterModel getLifterById(@PathVariable Long id) {
        Optional<LifterModel> lifterOptional = lifterRepository.findById(id);
        LifterModel lifter = lifterOptional.get();
        return lifter;
    }
    

    @PostMapping
    public LifterModel createLifter(@RequestBody LifterModel lifter) {
        return lifterRepository.save(lifter);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateLifterWeight(@PathVariable Long id, @RequestBody float lifterWeight) {
        Optional<LifterModel> lifterOptional = lifterRepository.findById(id);
        if (lifterOptional.isPresent()) {
            LifterModel lifter = lifterOptional.get();
            lifter.setWeight(lifterWeight);
            lifterRepository.save(lifter);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(lifter);

        }else{
            return ResponseEntity.status(404).body("Lifter não encontrado.");
        }
    };

    @GetMapping("/{id}/prs")
    public ResponseEntity<?> prList(@PathVariable Long id){
        Optional<LifterModel> lifter = lifterRepository.findById(id);

        if(lifter.isPresent()){
            List<PrModel> prs = lifter.get().getPr();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(prs);
        } else{
            return ResponseEntity.status(404).body("Lifter não encontrado.");
        }
    }
    
    @PostMapping("/{id}/prs")
    public ResponseEntity<?> adicionarPr(@PathVariable Long id, @RequestBody PrModel pr) {
        Optional<LifterModel> lifterOptional = lifterRepository.findById(id);

        if (lifterOptional.isPresent()) {
            LifterModel lifter = lifterOptional.get();
            pr.setLifter(lifter); 
            lifter.getPr().add(pr); 
            lifterRepository.save(lifter);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(pr);
        } else {
            return ResponseEntity.status(404).body("Lifter não encontrado.");
        }
    }

    @PutMapping("{id}/prs/{prId}")
    public ResponseEntity<?> updatePr(@PathVariable Long id, @PathVariable Long prId, @RequestBody PrModel pr) {
        Optional<LifterModel> lifterOptional = lifterRepository.findById(id);

        if (!lifterOptional.isPresent()) {
            return ResponseEntity.status(404).body("Lifter não encontrado.");
        }
        Optional<PrModel> prOptional = prRepository.findById(prId);

        if (!prOptional.isPresent()) {
            return ResponseEntity.status(404).body("PR não encontrado.");
        }

        PrModel prToUpdate = prOptional.get();
        // Atualizando os campos diretamente

        prToUpdate.setExercise(pr.getExercise());   // Atualizando o tipo de exercício
        prToUpdate.setKg(pr.getKg());               // Atualizando o peso levantado
        prRepository.save(prToUpdate);

        return ResponseEntity.ok("PR atualizado com sucesso.");
    }
    
    @DeleteMapping("/{lifterId}/pr/{id}")
    public ResponseEntity<String> deletePr(@PathVariable Long lifterId, @PathVariable Long id) {
        Optional<PrModel> prOptional = prRepository.findById(id);
        
        if (!prOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PR não encontrado.");
        }
        
        prRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("PR deletado com sucesso.");
    }

}
