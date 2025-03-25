package pr.powerlifting_records.Model;

import java.util.List;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@EntityScan
@Table(name = "tb_lifter")
public class LifterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    
    @OneToMany(mappedBy = "lifter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrModel> pr;

    public LifterModel(){}
    
    public LifterModel(String name, String password, List<PrModel> pr){
        this.name = name;
        this.password = password;
        this.pr = pr;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public List<PrModel> getPr() {
        return pr;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPr(List<PrModel> pr) {
        this.pr = pr;
    }
}
