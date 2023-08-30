package az.ingress.mscard.service

import az.ingress.mscard.dao.entity.CardEntity
import az.ingress.mscard.dao.repository.CardRepository
import az.ingress.mscard.dto.request.SaveCardRequest
import az.ingress.mscard.enums.CardStatus
import az.ingress.mscard.exception.NotFoundException
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class CardServiceTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private CardRepository cardRepository
    private CardService cardService

    def setup() {
        cardRepository = Mock()
        cardService = new CardService(cardRepository)
    }

    def "TestGetCardById success"() {
        given:
        def id = random.nextLong()
        def card = random.nextObject(CardEntity)

        when:
        def actual = cardService.getCardById(id)

        then:
        1 * cardRepository.findById(id) >> Optional.of(card)
        actual.id == card.id
        actual.pan == card.pan
        actual.cvv == card.cvv
        actual.cardHolder == card.cardHolder
        actual.expirationDate == card.expirationDate
        card.creditCard == card.creditCard
    }

    def "TestGetCardById entity not found"() {
        given:
        def id = random.nextLong()

        when:
        cardService.getCardById(id)

        then:
        1 * cardRepository.findById(id) >> Optional.empty()

        NotFoundException exception = thrown()
        exception.message == "CARD_NOT_FOUND"

    }

    def "TestSaveCard"() {
        given:
        def request = random.nextObject(SaveCardRequest)
        def card = CardEntity.builder()
                .cvv(request.cvv)
                .pan(request.pan)
                .cardHolder(request.cardHolder)
                .expirationDate(request.expirationDate)
                .creditCard(request.creditCard)
                .build()

        when:
        cardService.saveCard(request)

        then:
        1 * cardRepository.save(card)
        card.cvv == request.cvv
        card.pan == request.pan
        card.cardHolder == request.cardHolder
        card.expirationDate == request.expirationDate
        card.creditCard == request.creditCard
    }

    def "TestChangeStatus success"() {
        given:
        def id = random.nextLong()
        def status = random.nextObject(CardStatus)
        def card = CardEntity.builder()
                .cardStatus(status)
                .build()

        when:
        cardService.changeStatus(id, status)

        then:
        1 * cardRepository.findById(id) >> Optional.of(card)
        1 * cardRepository.save(card)
        card.cardStatus == status
    }

    def "TestChangeStatus entity not found"(){
        given:
        def id = random.nextLong()
        def status = random.nextObject(CardStatus)

        when:
        cardService.changeStatus(id, status)

        then:
        1 * cardRepository.findById(id) >> Optional.empty()

        NotFoundException exception = thrown()
        exception.message == "CARD_NOT_FOUND"
    }
}
