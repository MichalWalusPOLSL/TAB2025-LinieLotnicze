package model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "countries")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countryId;

    private String countryName;
    
    @Enumerated(EnumType.STRING)
    private Continent continent;
}
