package hr.tvz.laploggerapp.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "lap")
public class Lap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lap_number", nullable = false)
    private Integer lapNumber;

    @Column(name = "lap_time_ms", nullable = false)
    private Long lapTimeMs;

    @Column(name = "sector1_ms")
    private Long sector1Ms;

    @Column(name = "sector2_ms")
    private Long sector2Ms;

    @Column(name = "sector3_ms")
    private Long sector3Ms;

    @Column(name = "is_valid", nullable = false)
    private Boolean isValid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;
}
