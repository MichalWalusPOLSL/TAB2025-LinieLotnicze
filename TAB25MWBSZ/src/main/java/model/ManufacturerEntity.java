package model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "manufacturers")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ManufacturerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long manufacturerId;

    private String manufacturerName;
}

