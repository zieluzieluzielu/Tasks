package com.crud.tasks.service;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.domain.cards.Badges;
import com.crud.tasks.domain.cards.CreatedTrelloCardDto;
import com.crud.tasks.domain.cards.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.config.AdminConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {
    @InjectMocks
    TrelloService trelloService;

    @Mock
    TrelloClient trelloClient;

    @Mock
    AdminConfig adminConfig;

    @Test
    public void fetchTrelloBoardsTest() {

        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("test_id1", "test_list1", false));

        TrelloClient trelloClient = new TrelloClient();
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("board_id", "board_Name", trelloLists));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoards);

        //When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(1, fetchedTrelloBoards.size());
        assertEquals("board_id", fetchedTrelloBoards.get(0).getName());
    }


    @Test
    public void createTrelloCardTest() {
        //Given
        TrelloCardDto cardDto = new TrelloCardDto(
                "Test task",
                "Test Description",
                "top",
                "test_id");

        CreatedTrelloCardDto newCard = new CreatedTrelloCardDto("id","name","shortUrl", new Badges());

        when(trelloClient.createNewCard(cardDto)).thenReturn(newCard);
        when(adminConfig.getAdminMail()).thenReturn("test@test.pl");

        //When
        CreatedTrelloCardDto createdCard = trelloService.createTrelloCard(cardDto);

        //Then
        assertEquals("name",createdCard.getName());
    }



}