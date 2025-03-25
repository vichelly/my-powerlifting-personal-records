package pr.powerlifting_records.Model;

public class LifterModel {
    String name;
    String password;
    String id;
    PrModel pr;

    public LifterModel(String name, String password, String id, PrModel pr){
        this.name = name;
        this.password = password;
        this.id = id;
        this.pr = pr;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public PrModel getPr() {
        return pr;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPr(PrModel pr) {
        this.pr = pr;
    }
}
