package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.domain.cards.TrelloCard;
import com.crud.tasks.domain.cards.TrelloCardDto;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TrelloMapperTest {

    private TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void mapToTrelloCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test task",
                "Test Description",
                "top",
                "test_id");

        //When
        TrelloCard trelloCard = trelloMapper.mapToTrelloCard(trelloCardDto);

        //Then
        assertEquals(trelloCardDto.getName(), trelloCard.getName());
        assertEquals(trelloCardDto.getDescription(), trelloCard.getDescription());
        assertEquals(trelloCardDto.getPos(), trelloCard.getPos());
        assertEquals(trelloCardDto.getListId(), trelloCard.getListId());
    }

    @Test
    public void mapToTrelloCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard(
                "Test task",
                "Test Description",
                "top",
                "test_id");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToTrelloCardDto(trelloCard);


        //Then

        assertEquals(trelloCard.getName(), trelloCardDto.getName());
        assertEquals(trelloCard.getDescription(), trelloCardDto.getDescription());
        assertEquals(trelloCard.getPos(), trelloCardDto.getPos());
        assertEquals(trelloCard.getListId(), trelloCardDto.getListId());
    }


    @Test
    public void mapToListTest() {
        //Given
        TrelloListDto listDto1 = new TrelloListDto("test_id1", "test_list1", false);
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(listDto1);


        //When
        List<TrelloList> trelloList = trelloMapper.mapToList(trelloListDto);

        //Then
        assertEquals(1, trelloList.size());
        assertEquals("test_id1", trelloList.get(0).getId());
        assertEquals(false, trelloList.get(0).isClosed());

    }


    @Test
    public void mapToListDtoTest() {
        //Given
        TrelloList list1 = new TrelloList("test_id1", "test_list1", false);
        TrelloList list2 = new TrelloList("test_id2", "test_list2", true);
        List<TrelloList> trelloList = new ArrayList<TrelloList>();
        trelloList.add(list1);
        trelloList.add(list2);


        //When
        List<TrelloListDto> trelloListDto = trelloMapper.mapToListDto(trelloList);

        //Then
        assertEquals(2, trelloListDto.size());
        assertEquals("test_list1", trelloListDto.get(0).getName());

    }

    @Test
    public void mapToBoardsTest() {
        //Given
        TrelloListDto listDto1 = new TrelloListDto("test_id1", "test_list1", false);
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(listDto1);
        TrelloBoardDto boardDto = new TrelloBoardDto("board_id", "board_Name", trelloListDto);
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(boardDto);

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);

        //Then
        assertEquals(1, trelloBoardDtos.size());
        assertEquals("board_id", trelloBoardDtos.get(0).getId());
        assertEquals("test_list1", trelloBoardDtos.get(0).getLists().get(0).getName());

    }

    @Test
    public void mapToBoardsDtoTest() {
        //Given
        TrelloList list1 = new TrelloList("test_id1", "test_list1", false);
        TrelloList list2 = new TrelloList("test_id2", "test_list2", true);

        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(list1);
        trelloList.add(list2);
        TrelloBoard board = new TrelloBoard("board_id", "board_Name", trelloList);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(board);

        //When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        assertEquals(1, trelloBoardsDto.size());
        assertEquals("board_id", trelloBoardsDto.get(0).getId());
        assertEquals("test_list1", trelloBoardsDto.get(0).getLists().get(0).getName());
        assertEquals(2, trelloBoardsDto.get(0).getLists().size());

    }
}
