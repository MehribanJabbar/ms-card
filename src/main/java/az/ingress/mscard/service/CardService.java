package az.ingress.mscard.service;

import az.ingress.mscard.dao.entity.CardEntity;
import az.ingress.mscard.dao.repository.CardRepository;
import az.ingress.mscard.dto.request.SaveCardRequest;
import az.ingress.mscard.dto.response.CardResponse;
import az.ingress.mscard.enums.CardStatus;
import az.ingress.mscard.exception.NotFoundException;
import az.ingress.mscard.mapper.CardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    public CardResponse getCardById(Long id){
        var card = cardRepository.findById(id)
                .orElseThrow(() ->new  NotFoundException("CARD_NOT_FOUND"));
        return CardMapper.buildToResponse(card);
    }

    public void saveCard(SaveCardRequest cardRequest){
        CardEntity card = CardMapper.buildToEntity(cardRequest);
        card.setCardStatus(CardStatus.COMPLETED);
        cardRepository.save(card);
    }

    public void changeStatus(Long id, CardStatus newStatus){
        var card = cardRepository.findById(id)
                .orElseThrow(() ->new  NotFoundException("CARD_NOT_FOUND"));
        card.setCardStatus(newStatus);
        cardRepository.save(card);
    }

}
