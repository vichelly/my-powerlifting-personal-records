package pr.powerlifting_records.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pr.powerlifting_records.Model.LifterModel;

@Repository
public interface LifterRepository extends JpaRepository<LifterModel, Long> {
    // No repositório:
    Optional<LifterModel> findByName(String name);


    List<LifterModel> findByNameContaining(String name);

    List<LifterModel> findByPassword(String password);
}
