package model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "plane_models")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PlaneModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planeModelId;

    @ManyToOne
    @JoinColumn(name = "planeManufacturerId")
    private ManufacturerEntity manufacturer;

    private int capacity;
    private int range;
}
