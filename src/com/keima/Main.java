package com.keima;

import java.util.LinkedList ;
import java.util.ListIterator ;
import java.util.Scanner ;

public class Main {

    public static LinkedList<Album> spotify = new LinkedList<>() ;
    public static LinkedList<Song> playlist = new LinkedList<>() ;

    public static Scanner sc = new Scanner(System.in) ;

    public static void main(String[] args) {

        boolean quit = false ;
        menuOption();
        while (!quit) {

            System.out.println("Enter your choice:");
            int choice = sc.nextInt();
            sc.nextLine() ;
            switch (choice) {

                case 0: quit = true ;
                    break;

                case 1: createAlbum();
                    break;

                case 2: addSongToAlbum();
                    break;

                case 3: addSongToPlaylist();
                    break;

                case 4: playSongsFromPlaylist();
                    break;

                default: menuOption();
            }
        }
    }


    private static void addSongToAlbum() {

        System.out.print("Enter an album name: ");
        String albumName = sc.nextLine() ;
        int position = searchAlbum( albumName ) ;
        if(position >= 0) {

            System.out.print("Enter the song's name: ");
            String songName = sc.nextLine() ;
            int songPosition = spotify.get(position).searchSong(songName) ;
            if (songPosition >= 0 ) {

                System.out.println("Song already exist");
            } else {

                System.out.print("Enter the duration of the song: ");
                double duration = sc.nextDouble() ;
                sc.nextLine();
                spotify.get(position).addSong(songName , duration );
            }
        } else {

            System.out.println("Album not found") ;
        }
    }


    private static void addSongToPlaylist() {

        System.out.print("Enter the name of the song: ");
        String songName = sc.nextLine() ;

        if(!isSongInPlaylist(songName)) {

            ListIterator<Album> albumListIterator = spotify.listIterator();
            while (albumListIterator.hasNext()) {

                int position = searchAlbum(albumListIterator.next().getAlbumName()) ;
                ListIterator<Song> songListIterator = spotify.get(position).getSongList().listIterator() ;
                while (songListIterator.hasNext()) {

                    Song song = songListIterator.next() ;
                    if(songName.equals(song.getSongName())) {

                        playlist.add(song);
                        System.out.println("Song added");
                        return;
                    }
                }
            }
            System.out.println("Song not found in any album");
        } else {

            System.out.println("Song already in the playlist");
        }
    }

    private static boolean isSongInPlaylist(String songName) {

        for (Song song : playlist) {
            if (songName.equals(song.getSongName())) {

                return true;
            }
        }
        return false;
    }
    private static void createAlbum() {

        System.out.print("Enter an album name: ");
        String albumName = sc.nextLine() ;
        int position = searchAlbum( albumName ) ;
        if(position >= 0) {

            System.out.println("Album already exist");
        } else {

            spotify.add(new Album(albumName));
            System.out.println("Album " + albumName + " created successfully");
        }
    }

    private static int searchAlbum(String albumName) {

        for(int i = 0 ; i < spotify.size() ; i++ ) {

            if(albumName.equals(spotify.get(i).getAlbumName())) {

                return i ;
            }
        }
        return -1 ;
    }

    private static void playSongsFromPlaylist() {

        System.out.println("Welcome to the playlist ");
        boolean quit = true , goingForward = true ;
        ListIterator<Song> songListIterator = playlist.listIterator() ;

        if (playlist.isEmpty()) {

            System.out.println("Playlist empty") ;
            System.out.println("Going back to main menu");
            return;
        } else  {

            System.out.println("Playing " + songListIterator.next().getSongName());
        }

        playlistOption();

        while (quit) {

            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {

                case 0 : quit = false ;
                    System.out.println("Going back to main menu");
                    break;

                case 1 :
                    if (!goingForward) {
                        if (songListIterator.hasNext()) {
                            songListIterator.next() ;
                        }
                        goingForward = true ;
                    }
                    if (songListIterator.hasNext()) {

                        System.out.println("Now playing " + songListIterator.next().getSongName());
                    } else {

                        System.out.println("Reached the end of the playlist");
                        goingForward = false ;
                    }
                    break;

                case 2 :
                    if (goingForward) {

                        if (songListIterator.hasPrevious()) {

                            System.out.println("Now playing "  + songListIterator.previous().getSongName());
                            goingForward = false ;
                        } else  {

                            System.out.println("Reached the beginning of the playlist");
                        }
                    } else {

                        if (songListIterator.hasNext()) {
                            System.out.println("Now playing " + songListIterator.next().getSongName());
                            goingForward = true ;
                        } else {

                            System.out.println("Reached the end of the playlist");
                        }
                    }
                    break;

                case 3 :
                    if (goingForward) {
                        if (songListIterator.hasPrevious() ) {
                            songListIterator.previous();
                        }
                        goingForward = false ;
                    }
                    if (songListIterator.hasPrevious()) {

                        System.out.println("Now playing " + songListIterator.previous().getSongName());
                    } else {

                        System.out.println("Reached the beginning");
                        goingForward = true ;
                    }
                    break;

                case 4 : if (playlist.size() > 0 ) {

                    songListIterator.remove() ;
                    System.out.println("Song removed") ;

                    if (songListIterator.hasNext()) {

                        System.out.println("Now playing " + songListIterator.next().getSongName());
                    } else {

                        System.out.println("Now playing "+ songListIterator.previous().getSongName());
                    }
                }
                    break;

                default:
                    playlistOption();
            }
        }
    }
    private static void playlistOption() {

        System.out.println("Press 0 to quit the menu ");
        System.out.println("Press 1 to play the next song");
        System.out.println("Press 2 to play the replay the current song");
        System.out.println("Press 3 to play the previous song");
        System.out.println("Press 4 to remove the current song");
        System.out.println("Press any other number to print the option list");
    }

    private static void menuOption() {
        System.out.println("Press 0 to quit");
        System.out.println("Press 1 to add an album");
        System.out.println("Press 2 to add a song to the album");
        System.out.println("Press 3 to add a song to the playlist");
        System.out.println("Press 4 to go to playlist");
        System.out.println("Press any other number to show the option menu");
    }
}
