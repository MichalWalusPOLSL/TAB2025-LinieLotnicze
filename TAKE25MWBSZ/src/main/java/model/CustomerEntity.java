package model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "customers")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CustomerEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @ManyToOne
    @JoinColumn(name = "nationality", referencedColumnName = "countryId")
    private CountryEntity nationality;

    private String name;
    private String email;
    private String address;
    private LocalDate dateOfBirth;
}
