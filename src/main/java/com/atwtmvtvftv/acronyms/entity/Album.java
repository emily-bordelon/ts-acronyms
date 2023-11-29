package com.atwtmvtvftv.acronyms.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

// import jakarta.persistence.Entity;
// import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    // @Id
    @JsonProperty("album_id")
    private int albumId;

    @JsonProperty("title")
    private String albumTitle;

    @JsonProperty("release_date")
    private String releaseDate;
    
}
