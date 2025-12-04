package in.arc.urlShortner.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private String password;
    @OneToMany(mappedBy = "url")
    private List<Url> urls=new ArrayList<>();
}
