package com.atwtmvtvftv.acronyms.entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class SongDeserializer extends StdDeserializer<Song> {

    // the external API uses 2 different field names for Song Title ("title" and "song_title")
    // this class is needed for my Song entity to resolve that issue

    public SongDeserializer() {
        this(null);
    }

    public SongDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Song deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        int songId = node.get("song_id").intValue();
        String songTitle = node.has("title") ? node.get("title").asText() : node.get("song_title").asText();
        int albumId = node.get("album_id").intValue();
        return new Song(songId, songTitle, albumId);
    }
}

