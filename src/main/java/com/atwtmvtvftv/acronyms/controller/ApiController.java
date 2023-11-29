package com.atwtmvtvftv.acronyms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atwtmvtvftv.acronyms.entity.Album;
import com.atwtmvtvftv.acronyms.entity.Lyrics;
import com.atwtmvtvftv.acronyms.entity.Song;
import com.atwtmvtvftv.acronyms.service.LyricApiService;

@RestController
@RequestMapping("/api")
public class ApiController {

    // Not currently in use; using a CSV file in the project folder instead
    // This controller class is used to make requests to an external API of Taylor Swift songs

    private final LyricApiService lyricApiService;

    public ApiController(LyricApiService lyricApiService) {
        this.lyricApiService = lyricApiService;
    }

    @GetMapping("/albums")
    public List<Album> getAllAlbums() {
        return lyricApiService.getAllAlbums();
    }

    @GetMapping("/songs")
    public List<Song> getAllSongs() {
        return lyricApiService.getAllSongs();
    }

    // This endpoint from the external API is not currently working
    @GetMapping("/albums/{albumId}")
    public List<Song> getAlbumSongs(@PathVariable int albumId) {
        return lyricApiService.getAlbumSongs(albumId);
    }

    @GetMapping("/songs/{songId}")
    public Song getSongInfo(@PathVariable int songId) {
        return lyricApiService.getSongInfo(songId);
    }

    @GetMapping("/lyrics/{songId}")
    public Lyrics getLyricsForSong(@PathVariable int songId) {
        return lyricApiService.getLyricsForSong(songId);
    }

    
}
