package az.ingress.mscard.mapper

import az.ingress.mscard.dao.entity.CardEntity
import az.ingress.mscard.dto.request.SaveCardRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class CardMapperTest extends Specification{
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private CardMapper cardMapper

    def "TestBuildToResponse"(){
        given:
        def card = random.nextObject(CardEntity)

        when:
        def actual = CardMapper.buildToResponse(card)

        then:
        actual.id == card.id
        actual.pan == card.pan
        actual.cvv == card.cvv
        actual.cardHolder == card.cardHolder
        actual.expirationDate == card.expirationDate
        card.creditCard == card.creditCard
    }

    def "TestBuildToEntity"(){
        given:
        def request = random.nextObject(SaveCardRequest)

        when:
        def actual = CardMapper.buildToEntity(request)

        then:
        actual.pan == request.pan
        actual.cvv == request.cvv
        actual.cardHolder == request.cardHolder
        actual.expirationDate == request.expirationDate
        actual.creditCard == request.creditCard
    }

}
