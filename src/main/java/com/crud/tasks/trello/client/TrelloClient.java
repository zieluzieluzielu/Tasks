package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TrelloClient {

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/maciekzielinski4/boards")
            .queryParam("key", trelloAppKey)
            .queryParam("token", trelloToken)
            .queryParam("fields", "name,id").build().encode().toUri();

    @Autowired
    private RestTemplate restTemplate;

    TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);

    public List<TrelloBoardDto> getTrelloBoards() {
    if (boardsResponse != null) {
        return Arrays.asList(boardsResponse);
    }
    return new ArrayList<>();
    }





}
