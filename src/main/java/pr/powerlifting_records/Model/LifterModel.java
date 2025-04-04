package pr.powerlifting_records.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_lifter")
@NoArgsConstructor // lombok cria construtor sem parametros automaticamente
@AllArgsConstructor // lombok cria contrutor com todos os parametros autmaticamente
@Data // loombo cria todos os getters e setters criados automaticamente 
public class LifterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private float weight;
    
    // um lifter tem vários pr, mapear os pr por lifter
    @OneToMany(mappedBy = "lifter", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PrModel> pr;
}
