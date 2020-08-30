package entities;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Represents a model of a yacht
 * @author Marta Polcyn
 */

@Entity(name = "models")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "model_id")
    private Long modelId;

    @Column (name = "model_desc", nullable = false)
    private String modelDescription;

    @Column (name = "price_per_day", nullable = false)
    private BigDecimal pricePerDay;

    @Column (name = "price_per_week", nullable = false)
    private BigDecimal pricePerWeek;

    @Column (name = "bunk_count", nullable = false)
    private int bunkCount;

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getModelDescription() {
        return modelDescription;
    }

    public void setModelDescription(String modelDescription) {
        this.modelDescription = modelDescription;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public BigDecimal getPricePerWeek() {
        return pricePerWeek;
    }

    public void setPricePerWeek(BigDecimal pricePerWeek) {
        this.pricePerWeek = pricePerWeek;
    }

    public int getBunkCount() {
        return bunkCount;
    }

    public void setBunkCount(int bunkCount) {
        this.bunkCount = bunkCount;
    }
}
