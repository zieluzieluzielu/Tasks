package com.crud.tasks.trello.client.facade;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.domain.cards.Badges;
import com.crud.tasks.domain.cards.CreatedTrelloCardDto;
import com.crud.tasks.domain.cards.TrelloCard;
import com.crud.tasks.domain.cards.TrelloCardDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.crud.tasks.trello.validator.TrelloValidator;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTest {

    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;

    @Test
    public void shouldFetchEmptyList(){
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("test_id1", "test_list1", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("board_id", "board_Name", trelloLists));

        List<TrelloList> mappedtrelloList = new ArrayList<>();
        mappedtrelloList.add(new TrelloList("test_id1", "test_list1", false));

        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("board_id", "board_Name", mappedtrelloList));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(new ArrayList<>());
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(new ArrayList<>());

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(0,trelloBoardDtos.size());

    }


    @Test
    public void shouldFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("test_id1", "test_list1", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("board_id", "board_Name", trelloLists));

        List<TrelloList> mappedtrelloList = new ArrayList<>();
        mappedtrelloList.add(new TrelloList("test_id1", "test_list1", false));

        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("board_id", "board_Name", mappedtrelloList));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoards);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(1, trelloBoardDtos.size());

        trelloBoardDtos.forEach(trelloBoardDto -> {
            assertEquals("board_id", trelloBoardDto.getId());
            assertEquals("board_Name",trelloBoardDto.getName());
            trelloBoardDto.getLists().forEach(trelloListDto -> {
                assertEquals("test_id1",trelloListDto.getId());
                assertEquals("test_list1", trelloListDto.getName());
                assertEquals(false, trelloListDto.isClosed());

            });
        });



    }


    @Test
    public void shouldCreateCard() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name","description","position","istid");
        TrelloCardDto trelloCardDto = new TrelloCardDto("name","description","position","istid");
        CreatedTrelloCardDto createdCardDto = new CreatedTrelloCardDto("id","name","shortUrl", new Badges());


        when(trelloMapper.mapToTrelloCard(trelloCardDto)).thenReturn(trelloCard);
        when(trelloMapper.mapToTrelloCardDto(trelloCard)).thenReturn(trelloCardDto);
        when(trelloService.createTrelloCard(trelloCardDto)).thenReturn(createdCardDto);
        //When
        CreatedTrelloCardDto createdTrelloCardDto = trelloFacade.createCard(trelloCardDto);

        //Then
        assertEquals("name", createdTrelloCardDto.getName());
    }



}
