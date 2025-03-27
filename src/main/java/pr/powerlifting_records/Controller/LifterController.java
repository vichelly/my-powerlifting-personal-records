package pr.powerlifting_records.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pr.powerlifting_records.Model.LifterModel;
import pr.powerlifting_records.Model.PrModel;
import pr.powerlifting_records.Repository.LifterRepository;
import pr.powerlifting_records.Repository.PrRepository;  // Adicione esta linha

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/lifter")
public class LifterController {
    
    private LifterRepository lifterRepository;
    private PrRepository prRepository;  // Declare a variável para o prRepository

    // Injete o prRepository no construtor
    public LifterController(LifterRepository lifterRepository, PrRepository prRepository) {
        this.lifterRepository = lifterRepository;
        this.prRepository = prRepository;
    }

    @GetMapping()
    public List<LifterModel> listAllLifters() {
        return lifterRepository.findAll();
    }
    
    @PostMapping
    public LifterModel createLifter(@RequestBody LifterModel lifter) {
        return lifterRepository.save(lifter);
    }

    @GetMapping("/{id}/prs")
    public ResponseEntity<?> prList(@PathVariable Long id){
        Optional<LifterModel> lifter = lifterRepository.findById(id);

        if(lifter.isPresent()){
            List<PrModel> prs = lifter.get().getPr();
            return ResponseEntity.ok(prs);
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
            return ResponseEntity.ok("PR adicionado com sucesso.");
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
