package dev.sorokin.offtopic;

import dev.sorokin.contract.Music;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
//@NoArgsConstructor
//@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class MusicPlayer {
    private Music musicRock;
    private Music musicClassic;
    private Enum genres;

    @Autowired
    public MusicPlayer(@Qualifier("classicalMusic") Music musicClassic,
                       @Qualifier("rockMusic") Music musicRock) {
        this.musicClassic = musicClassic;
        this.musicRock = musicRock;
    }

    public String playMusic(){
        Random random = new Random();
        int i = random.nextInt(100);
        String classic = musicClassic.playMusic(genres);
        String rock = musicRock.playMusic(genres);
        return i > 50 ? classic : rock;
    }
}
