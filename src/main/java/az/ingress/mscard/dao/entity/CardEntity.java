package az.ingress.mscard.dao.entity;

import az.ingress.mscard.enums.CardStatus;
import az.ingress.mscard.enums.CardType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cards")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private String pan;
    private String cvv;
    private String cardHolder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDate expirationDate;

    @Enumerated(EnumType.STRING)
    private CardType creditCard;

    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardEntity that = (CardEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
