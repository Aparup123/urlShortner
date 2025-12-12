package in.arc.urlShortner.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String shortUrl;
    @Column(columnDefinition = "TEXT")
    private String trueUrl;
    @ManyToOne
    @ToString.Exclude
    private User createdBy;
}
