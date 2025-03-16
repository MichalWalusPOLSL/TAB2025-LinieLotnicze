package model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class FlightEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    @ManyToOne
    @JoinColumn(name = "flightsOutAirfieldId")
    private AirfieldEntity flightsOutAirfield;

    @ManyToOne
    @JoinColumn(name = "landsInAirfieldId")
    private AirfieldEntity landsInAirfield;

    @ManyToOne
    @JoinColumn(name = "usesPlaneId")
    private PlaneEntity plane;

    private LocalDateTime flightsOutTime;
    private LocalDateTime landsInTime;
}
