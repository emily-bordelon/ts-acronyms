package com.atwtmvtvftv.acronyms.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atwtmvtvftv.acronyms.entity.Lyrics;
import com.atwtmvtvftv.acronyms.service.AcronymService;

@RestController
@RequestMapping("/lyric-finder")
public class AcronymController {

    AcronymService acronymService;
    private Environment env;

    public AcronymController(AcronymService acronymService, Environment env) {
        this.acronymService = acronymService;
        this.env = env;
    }

    @PostMapping("search/{acronym}")
    public Set<String> findLyrics(@PathVariable("acronym") String acronym) throws IOException {
        return acronymService.findLyrics(acronym);
    }

}