package dev.sorokin.offtopic;

import dev.sorokin.contract.Music;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ClassicalMusic implements Music {
    MusicGenre musicGenre = MusicGenre.CLASSICAL;
    @Override
    public String playMusic(Enum genres) {
        return musicGenre.getRandomSong();
    }

    public void initMethod() {
        System.out.println("do my init");
    }

    public void destroyMethod(){
        System.out.println("DOING DESTRUCTION!!!!!");
    }
}
