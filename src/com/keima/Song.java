package com.keima;

import java.util.LinkedList;

public class Song {

    private String songName ;
    private double durationOfTheSong ;

    public Song(String songName, double durationOfTheSong) {

        this.songName = songName;
        this.durationOfTheSong = durationOfTheSong ;
    }

    public String getSongName() {

        return songName;
    }


}
