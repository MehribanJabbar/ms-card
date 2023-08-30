package az.ingress.mscard.controller;

import az.ingress.mscard.dto.request.SaveCardRequest;
import az.ingress.mscard.dto.response.CardResponse;
import az.ingress.mscard.enums.CardStatus;
import az.ingress.mscard.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/cards")
public class CardController {
    private final CardService cardService;

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public CardResponse getCardById(@PathVariable Long id){
        return cardService.getCardById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveCard(@RequestBody SaveCardRequest request){
        cardService.saveCard(request);
    }

    @PutMapping("/{id}/status")
    @ResponseStatus(NO_CONTENT)
    public void changeStatus(@PathVariable Long id, @RequestBody CardStatus newStatus){
        cardService.changeStatus(id, newStatus);
    }
}
