package az.ingress.mscard.mapper;

import az.ingress.mscard.dao.entity.CardEntity;
import az.ingress.mscard.dto.request.SaveCardRequest;
import az.ingress.mscard.dto.response.CardResponse;

public class CardMapper {
    public static CardResponse buildToResponse(CardEntity card){
        return CardResponse.builder()
                .id(card.getId())
                .pan(card.getPan())
                .cvv(card.getCvv())
                .cardHolder(card.getCardHolder())
                .expirationDate(card.getExpirationDate())
                .creditCard(card.getCreditCard())
                .build();
    }

    public static CardEntity buildToEntity(SaveCardRequest request){
        return CardEntity.builder()
                .cvv(request.getCvv())
                .pan(request.getPan())
                .cardHolder(request.getCardHolder())
                .expirationDate(request.getExpirationDate())
                .creditCard(request.getCreditCard())
                .build();
    }
}
