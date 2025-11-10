package in.arc.urlShortner.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
