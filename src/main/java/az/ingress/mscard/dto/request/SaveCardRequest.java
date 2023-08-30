package az.ingress.mscard.dto.request;

import az.ingress.mscard.enums.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaveCardRequest {
    String pan;
    String cvv;
    String cardHolder;
    LocalDate expirationDate;
    CardType creditCard;
}
