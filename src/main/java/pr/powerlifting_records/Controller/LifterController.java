package pr.powerlifting_records.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pr.powerlifting_records.Model.LifterModel;
import pr.powerlifting_records.Model.PrModel;
import pr.powerlifting_records.Repository.LifterRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/lifter")
public class LifterController {
    
    private LifterRepository lifterRepository;

    public LifterController(LifterRepository lifterRepository){
        this.lifterRepository = lifterRepository;
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
    
}
