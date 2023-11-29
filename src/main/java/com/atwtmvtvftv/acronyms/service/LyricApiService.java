package com.atwtmvtvftv.acronyms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.atwtmvtvftv.acronyms.entity.Album;
import com.atwtmvtvftv.acronyms.entity.Lyrics;
import com.atwtmvtvftv.acronyms.entity.Song;

@Service
public class LyricApiService {

    // Not currently in use
    // This service class interacts with the AcronymController class to make requests to an external API of TS songs

    private final WebClient webClient;

    public LyricApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://taylor-swift-api.sarbo.workers.dev").build();
    }

    public List<Album> getAllAlbums() {
        return webClient.get()
                .uri("/albums")
                .retrieve()
                .bodyToFlux(Album.class)
                .collectList()
                .block();
    }

    public List<Song> getAllSongs() {
        return webClient.get()
                .uri("/songs")
                .retrieve()
                .bodyToFlux(Song.class)
                .collectList()
                .block();
    }

    // this endpoint from the external API isn't working
    public List<Song> getAlbumSongs(int albumId) {
        return webClient.get()
                .uri("/albums/{albumId}", albumId)
                .retrieve()
                .bodyToFlux(Song.class)
                .collectList()
                .block();
    }

    public Song getSongInfo(int songId) {
        return webClient.get()
                .uri("/songs/{songId}", songId)
                .retrieve()
                .bodyToMono(Song.class)
                .block();
    }

    public Lyrics getLyricsForSong(int songId) {
        return webClient.get()
                .uri("/lyrics/{songId}", songId)
                .retrieve()
                .bodyToMono(Lyrics.class)
                .block();        
    }
    
}
