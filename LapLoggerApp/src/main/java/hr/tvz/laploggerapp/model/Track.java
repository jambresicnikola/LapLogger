package hr.tvz.laploggerapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "track")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 100)
    private String country;

    @Column(name = "length_km", nullable = false, precision = 6, scale = 3)
    private BigDecimal lengthKm;

    @Column(length = 100)
    private String configuration;

    @OneToMany(mappedBy = "track")
    private List<Session> sessions = new ArrayList<>();
}