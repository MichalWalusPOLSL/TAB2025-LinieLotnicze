package model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "planes")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PlaneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planeId;

    @ManyToOne
    @JoinColumn(name = "modelId")
    private PlaneModelEntity model;

    private int yearOfManufacture;
}
