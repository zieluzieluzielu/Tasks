package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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