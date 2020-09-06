package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents a model of a rent
 *
 * @author Danuta Hering
 */

@Entity
@Table(name = "rents")
public class Rent {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "rent_id")
    private Long rentId;

    @Column(name = "yacht_id")
    private Long yachtId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "rented_from")
    private LocalDate rentedFrom;

    @Column(name = "rented_to")
    private LocalDate rentedTo;

    @Column
    private BigDecimal cost;

    public Long getRentId() {
        return rentId;
    }

    public void setRentId(Long rentId) {
        this.rentId = rentId;
    }

    public Long getYachtId() {
        return yachtId;
    }

    public void setYachtId(Long yachtId) {
        this.yachtId = yachtId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDate getRentedFrom() {
        return rentedFrom;
    }

    public void setRentedFrom(LocalDate rentedFrom) {
        this.rentedFrom = rentedFrom;
    }

    public LocalDate getRentedTo() {
        return rentedTo;
    }

    public void setRentedTo(LocalDate rentedTo) {
        this.rentedTo = rentedTo;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Rent number id:" + rentId +
                ", yacht id =" + yachtId +
                ", customer id = " + customerId +
                ", rented date from " + rentedFrom +
                " to " + rentedTo +
                "";
    }
}
