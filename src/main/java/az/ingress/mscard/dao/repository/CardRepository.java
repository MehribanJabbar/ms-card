package az.ingress.mscard.dao.repository;

import az.ingress.mscard.dao.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CardEntity, Long> {
}
