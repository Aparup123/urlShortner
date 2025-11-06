package in.arc.urlShrotner.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String shortUrl;
    @Column(columnDefinition = "TEXT")
    private String trueUrl;
}
