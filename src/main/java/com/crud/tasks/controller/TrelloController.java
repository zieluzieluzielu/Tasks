package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.cards.TrelloCardDto;
import com.crud.tasks.domain.cards.CreatedTrelloCard;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public void getTrelloBoards() {

        // GET request
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        trelloBoards.stream()
                .filter(id -> id.getId() != null)
                .filter(title -> title.getName() != null)
                .filter(title -> title.getName().contains("Kodilla"))
                .forEach(trelloBoardDto -> {

                    System.out.println(trelloBoardDto.getId() + " - " + trelloBoardDto.getName());

                    System.out.println("This board contains lists: ");

                    trelloBoardDto.getLists().forEach(trelloListDto ->

                            System.out.println(trelloListDto.getName() + " - " + trelloListDto.getId()
                                    + " - " + trelloListDto.isClosed()));
                });

    }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloClient.createNewCard(trelloCardDto);
    }


}