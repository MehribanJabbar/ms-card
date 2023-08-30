package az.ingress.mscard.dto.response;

import az.ingress.mscard.enums.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardResponse {
    Long id;
    String pan;
    String cvv;
    String cardHolder;
    LocalDate expirationDate;
    CardType creditCard;
}
