package hr.java.web.zavrsnizadatak3.repository;

import hr.java.web.zavrsnizadatak3.domain.Upis;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

@Profile("!jdbc")
public interface SpringDataUpisRepository
        extends JpaRepository<Upis, Integer>,
        UpisRepository {
}