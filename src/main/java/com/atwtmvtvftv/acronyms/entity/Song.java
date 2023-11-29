package com.atwtmvtvftv.acronyms.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

// import jakarta.persistence.Entity;
// import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = SongDeserializer.class)
public class Song {

    // @Id
    @JsonProperty("song_id")
    private int songId;

    @JsonAlias({"title", "song_title"})
    private String songTitle;

    @JsonProperty("album_id")
    private int albumId;


    
}
