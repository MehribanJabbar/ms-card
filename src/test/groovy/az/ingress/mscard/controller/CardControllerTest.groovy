package az.ingress.mscard.controller

import az.ingress.mscard.dto.request.SaveCardRequest
import az.ingress.mscard.dto.response.CardResponse
import az.ingress.mscard.enums.CardType
import az.ingress.mscard.service.CardService
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

import java.time.LocalDate

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

class CardControllerTest extends Specification {
    private CardService cardService
    private MockMvc mockMvc

    def setup() {
        cardService = Mock()
        def cardController = new CardController(cardService)
        mockMvc = MockMvcBuilders.standaloneSetup(cardController).build()
    }

    def "TestGetCardById"() {
        given:
        def id = 1L
        def url = "/v1/cards/$id"

        def responseView = new CardResponse(1L, "1234567809876543", "123",
                "Tony Stark", LocalDate.of(2023, 1, 1), CardType.MASTERCARD)

        def expectedResponse = '''
                {
                    "id" : 1,
                    "pan" : "1234567809876543",
                    "cvv" : "123",
                    "cardHolder" : "Tony Stark",
                    "expirationDate" : [2023,1,1],
                    "creditCard" : "MASTERCARD"
                }
        '''

        when:
        def result = mockMvc.perform(get(url)).andReturn()

        then:
        1 * cardService.getCardById(id) >> responseView

        def response = result.response
        response.status == HttpStatus.OK.value()
        JSONAssert.assertEquals(expectedResponse, response.getContentAsString(), false)
    }

    def "TestSaveCard"() {
        given:
        def url = "/v1/cards"

        def request = new SaveCardRequest("1234567809876543", "123",
                "Tony Stark", LocalDate.of(2023, 1, 1), CardType.MASTERCARD)

        def requestBody = '''
                {
                    "pan" : "1234567809876543",
                    "cvv" : "123",
                    "cardHolder" : "Tony Stark",
                    "expirationDate" : [2023,1,1],
                    "creditCard" : "MASTERCARD"
                }
        '''

        when:
        def result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andReturn()

        then:
        1 * cardService.saveCard(request)

        def response = result.response
        response.status == HttpStatus.CREATED.value()
    }


}
