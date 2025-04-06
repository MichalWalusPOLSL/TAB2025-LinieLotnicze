package model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "airfields")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AirfieldEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airfieldId;

    @ManyToOne
    @JoinColumn(name = "locationCityId")
    private CityEntity locationCity;

    private String airfieldName;
}
