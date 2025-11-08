package in.arc.urlShortner.repository;

import in.arc.urlShortner.model.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UrlRepository extends JpaRepository<Url, UUID> {
}
