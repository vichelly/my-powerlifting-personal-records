package pr.powerlifting_records.DTO;

import lombok.Data;
import pr.powerlifting_records.Model.PrModel;
import pr.powerlifting_records.Model.LifterModel;

import java.util.List;

@Data
public class LifterData {
    private LifterModel lifter;
    private List<PrModel> prs;
}
