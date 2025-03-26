package pr.powerlifting_records.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pr.powerlifting_records.Model.PrModel;

public interface PrRepository extends JpaRepository<PrModel, Long> {
}
