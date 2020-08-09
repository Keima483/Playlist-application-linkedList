package com.keima;

import java.util.LinkedList;

public class Album {

    private String albumName ;
    private LinkedList<Song> songList = new LinkedList<>();

    public Album(String albumName) {

        this.albumName = albumName ;
    }

    public String getAlbumName() {
        return albumName ;
    }

    public LinkedList<Song> getSongList() {
        return songList;
    }

    public int searchSong(String songName) {

        for (int i = 0; i < songList.size() ; i ++ ) {

            if ( songName.equals( songList.get(i).getSongName() ) ) {

                return i ;
            }
        }
        return -1 ;
    }

    public void addSong(String songName , double duration ) {

        if (searchSong(songName) >= 0 ) {

            System.out.println("The song already exist") ;
        } else {

            songList.add(new Song(songName , duration )) ;
            System.out.println("Song " + songName + " added to the album " + albumName ) ;
        }
    }
}
