package com.atwtmvtvftv.acronyms.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.atwtmvtvftv.acronyms.controller.ApiController;
import com.atwtmvtvftv.acronyms.entity.Lyrics;
import com.atwtmvtvftv.acronyms.util.CsvHandler;

@Service
public class AcronymService {

    private final ApiController apiController;

    public AcronymService(ApiController apiController) {
        this.apiController = apiController;
    }

    // This uses the ApiController to call the external API
    // public String findLyrics(String acronym) {

    //     for (int songId = 1; songId <= 177; songId++) {
    //         Lyrics lyrics = apiController.getLyricsForSong(songId);

    //         String lyricsString = lyrics.getLyrics();

    //         if (findMatch(acronym, lyricsString) != null) {
    //             return findMatch(acronym, lyricsString);
    //         }
    //     }

    //     // If no match is found, return null or handle accordingly
    //     return null;
    // }

    public Set<String> findLyrics(String acronym) throws IOException {
        List<String[]> csvData = CsvHandler.readCsv();
        // List<String> matches = new ArrayList<>();
        Set<String> matches = new HashSet<>();
   
        for (String[] row : csvData) {
            // Song titles are in the 1st column of the CSV file
            String songTitles = row.length > 0 ? row[0] : "";

            // Lyrics are in the 3rd column of the CSV file
            String lyrics = row.length > 2 ? row[2] : "";
   
            if (findMatchInLyrics(acronym, lyrics) != null) {
                matches.add(findMatchInLyrics(acronym, lyrics));
            }

            // Implementing song title matches in next iteration:
            // if (findMatchInTitles(acronym, songTitles) != null) {
            //     matches.add(findMatchInTitles(acronym, songTitles));
            // }
        }

        if (matches.isEmpty()) {
            matches.add("no matches found :(");
        }
    
        return matches;
    }


    // Implementing song title matches in next iteration:
    // public String findMatchInTitles(String acronym, String title) {
    //     // Convert the acronym and title to lowercase
    //     String lowercaseAcronym = acronym.toLowerCase();
    //     String lowercaseTitle = title.toLowerCase();
    
    //     // Remove punctuation and special characters from the title
    //     String cleanedTitle = lowercaseTitle.replaceAll("[^a-zA-Z0-9\\s']", "");
    
    //     // Split the cleanedTitle into words
    //     String[] words = cleanedTitle.split("\\s+");
    
    //     // Split the acronym into a character array
    //     char[] letters = lowercaseAcronym.toCharArray();
    
    //     StringBuilder titleAcronym = new StringBuilder();
    //     StringBuilder match = new StringBuilder();
    
    //     for (String word : words) {
    //         titleAcronym.append(firstLetter(word));
    //     }
    
    //     String longAcronym = titleAcronym.toString();
    
    //     // Match the whole title
    //     if (longAcronym.equals(lowercaseAcronym)) {
    //         return title;
    //     }
    
    //     // Match the section of the title before any parenthesis
    //     int parenthesisIndex = cleanedTitle.indexOf('(');
    //     if (parenthesisIndex != -1) {
    //         String sectionBeforeParenthesis = cleanedTitle.substring(0, parenthesisIndex);
    //         String cleanedSectionBeforeParenthesis = sectionBeforeParenthesis.replaceAll("[^a-zA-Z0-9\\s']", "");
    //         String sectionAcronym = cleanedSectionBeforeParenthesis.chars()
    //                 .filter(Character::isLetter)
    //                 .mapToObj(c -> String.valueOf((char) c))
    //                 .collect(Collectors.joining());
    
    //         if (sectionAcronym.equals(lowercaseAcronym)) {
    //             return sectionBeforeParenthesis.trim();
    //         }
    //     }
    
    //     // Match considering numbers in the title
    //     if (cleanedTitle.contains(lowercaseAcronym)) {
    //         return title;
    //     }
    
    //     // Match considering variations of "[From the Vault]"
    //     if (cleanedTitle.contains("[from the vault]")) {
    //         String titleWithoutVault = cleanedTitle.replace("[from the vault]", "").trim();
    //         String titleWithoutVaultAcronym = titleWithoutVault.chars()
    //                 .filter(Character::isLetter)
    //                 .mapToObj(c -> String.valueOf((char) c))
    //                 .collect(Collectors.joining());
    
    //         if (titleWithoutVaultAcronym.equals(lowercaseAcronym)) {
    //             return titleWithoutVault;
    //         }
    //     }
    
    //     // Match considering variations in "tv" and "ftv"
    //     String titleWithoutTV = cleanedTitle.replace("tv", "").replace("ftv", "").trim();
    //     String titleWithoutTVAcronym = titleWithoutTV.chars()
    //             .filter(Character::isLetter)
    //             .mapToObj(c -> String.valueOf((char) c))
    //             .collect(Collectors.joining());
    
    //     if (titleWithoutTVAcronym.equals(lowercaseAcronym)) {
    //         return titleWithoutTV;
    //     }
    
    //     return null; // No match found
    // }
    

    public String findMatchInLyrics(String acronym, String lyrics) {

        // String hardCodedLyrics = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum";

        // Convert the acronym and lyrics to lowercase
        String lowercaseAcronym = acronym.toLowerCase();
        String lowercaseLyrics = lyrics.toLowerCase();

        // Remove punctuation and special characters from the lyrics
        String cleanedLyrics = lowercaseLyrics.replaceAll("[^a-zA-Z0-9\\s]", "");

        // Split the cleanedLyrics into words
        String[] words = cleanedLyrics.split("\\s+");

        // Split the acronym into a character array
        // char[] letters = lowercaseAcronym.toCharArray();

        StringBuilder wholeSongAcronym = new StringBuilder();
        StringBuilder foundLyrics = new StringBuilder();

        for (String word : words) {
            wholeSongAcronym.append(firstLetter(word));
        }

        String longAcronym = wholeSongAcronym.toString();

        int startIndex = longAcronym.indexOf(lowercaseAcronym);

        // startIndex will be -1 if the acronym isn't found
        if (startIndex != -1) {
            int endIndex = startIndex + lowercaseAcronym.length() - 1;

            startIndex = Math.max(0, startIndex);
            endIndex = Math.min(endIndex, words.length - 1);

            for (int i = startIndex; i <= endIndex; i++) {
                foundLyrics.append(words[i]).append(" ");
            }
        } else {
            return null;
        }

        return foundLyrics.toString().trim();

    }


    // Helper function to get the first letter of a word
    public static char firstLetter(String word) {
        return word.charAt(0);
    }

    // Helper function to get the corresponding letter from the lowercase acronym
    public static char correspondingLetter(String lowercaseAcronym) {
        return lowercaseAcronym.charAt(0);
    }

}