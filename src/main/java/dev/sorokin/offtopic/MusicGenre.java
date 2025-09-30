package dev.sorokin.offtopic;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
public enum MusicGenre {
    CLASSICAL(List.of("Tchaikovsky", "Mozart", "Rahmaninov")),
    ROCK(List.of("Smells like teen spirit", "The show must go on", "Bring me to life")),
    POP(List.of("Thriller", "Like a Prayer")),
    METAL(List.of("Master of Puppets", "Fear of the Dark"));

    private final List<String> songs;

    MusicGenre(List<String> songs) {
        this.songs = songs;
    }

    public static void getAllSongs(){
        List<String> allSongs = new ArrayList<>();
        for(MusicGenre genre : MusicGenre.values()){
            allSongs.addAll(genre.getSongs());
        }
        for(String string : allSongs) {
            System.out.println(string);
        }
    }

    public String getRandomSong() {
        Random random = new Random();
        int r = random.nextInt(this.songs.size());
        return this.songs.get(r);
    }
}

