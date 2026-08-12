package hr.tvz.laploggerapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String make;

    @Column(nullable = false, length = 100)
    private String model;

    @Column(nullable = false)
    private Integer year;

    @Column(name = "power_hp", nullable = false)
    private Integer powerHp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 3)
    private Drivetrain drivetrain;

    @Column(length = 50)
    private String category;

    @OneToMany(mappedBy = "car")
    private List<Session> sessions = new ArrayList<>();
}