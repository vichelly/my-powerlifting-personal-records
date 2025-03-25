package pr.powerlifting_records.Model;

public class PrModel {
    enum exercise {Squad, Benchpress, Deadlift};
    exercise exercise;
    float kg;

    public PrModel(exercise exercise, float kg){
        this.exercise = exercise;
        this.kg = kg;
    }

    public exercise getExercise() {
        return exercise;
    }
    public float getKg() {
        return kg;
    }

    public void setExercise(exercise exercise) {
        this.exercise = exercise;
    }
    public void setKg(float kg) {
        this.kg = kg;
    }
}
