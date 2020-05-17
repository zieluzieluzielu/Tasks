package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.domain.cards.TrelloCard;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TrelloValidatorTest {


    private TrelloValidator trelloValidator = new TrelloValidator();

    @Test
    void validateTrelloBoards() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("test_id1", "test_list1", false));
        trelloLists.add(new TrelloList("id1", "list2", false));

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("board_id", "board_Name", trelloLists));

        //When
        List<TrelloBoard> trelloBoardsValidated = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then

        assertEquals(1, trelloBoardsValidated.size());


    }
}