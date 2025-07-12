package dev.sorokin.offtopic;

import dev.sorokin.contract.Music;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RockMusic implements Music {
    MusicGenre musicGenre = MusicGenre.ROCK;
    @Override
    public String playMusic(Enum genres) {
        return musicGenre.getRandomSong();
    }
}
