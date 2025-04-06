package model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer_flights")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CustomerFlightEntity {

	//TODO zamienic na klucz zlozony
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "passengerFlightId")
    private FlightEntity flight;

    @ManyToOne
    @JoinColumn(name = "passengerId")
    private CustomerEntity customer;

    private boolean boarded;
}
