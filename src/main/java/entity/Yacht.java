package entity;

import javax.persistence.*;

/**
 * Represents a yacht item
 * @author Marta Polcyn
 */

@Entity(name = "yachts")
public class Yacht {

    @Id
    @GeneratedValue
    @Column (name = "yacht_id")
    private Long yachtId;

    @Column (name = "model_id")
    @ManyToOne
    private Long modelId;

    public Long getYachtId() {
        return yachtId;
    }

    public void setYachtId(Long yachtId) {
        this.yachtId = yachtId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }
}
