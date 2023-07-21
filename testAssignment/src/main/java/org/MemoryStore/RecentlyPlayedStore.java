package org.MemoryStore;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class RecentlyPlayedStore {
    private final int initialCapacity;
    private final int maxSongsPerUser;
    private Map<String, UserPlaylist> userSongsMap;

    public RecentlyPlayedStore(int initialCapacity, int maxSongsPerUser) {
        this.initialCapacity = initialCapacity;
        this.maxSongsPerUser = maxSongsPerUser;
        this.userSongsMap = new HashMap<>(initialCapacity);
    }


    public void recordSong(String user, String song) {
        if (!userSongsMap.containsKey(user)) {
            userSongsMap.put(user, new UserPlaylist(maxSongsPerUser));
        }

        UserPlaylist userPlaylist = userSongsMap.get(user);
        userPlaylist.addSong(song);
    }


    public LinkedList<String> getRecentlyPlayedSongs(String user) {
        UserPlaylist userPlaylist = userSongsMap.get(user);
        if (userPlaylist == null) {
            return null;
        }

        return userPlaylist.getRecentlyPlayedSongs();
    }


    private static class UserPlaylist {
        private final int maxSongs;
        private final LinkedList<String> playlist;

        private UserPlaylist(int maxSongs) {
            this.maxSongs = maxSongs;
            this.playlist = new LinkedList<>();
        }

        private void addSong(String song) {

            playlist.remove(song);
            playlist.addLast(song);
            if (playlist.size() > maxSongs) {
                playlist.removeFirst();
            }
        }

        private LinkedList<String> getRecentlyPlayedSongs() {
            return new LinkedList<>(playlist);
        }
    }

    public static void main(String[] args) {

        RecentlyPlayedStore store = new RecentlyPlayedStore(1000, 3);
        store.recordSong("user1", "song1");
        store.recordSong("user1", "song2");
        store.recordSong("user2", "song3");
        store.recordSong("user1", "song4");
        store.recordSong("user2", "song5");
        store.recordSong("user1", "song6");
        store.recordSong("user1","song7");
        store.recordSong("user2", "song4");
        store.recordSong("user2", "song3");


        LinkedList<String> recentlyPlayedUser1 = store.getRecentlyPlayedSongs("user1");
        if (recentlyPlayedUser1 != null) {
            System.out.println("Recently played songs for user1: " + recentlyPlayedUser1);
        } else {
            System.out.println("No recently played songs found for user1.");
        }

        LinkedList<String> recentlyPlayedUser2 = store.getRecentlyPlayedSongs("user2");
        if (recentlyPlayedUser2 != null) {
            System.out.println("Recently played songs for user2: " + recentlyPlayedUser2);
        } else {
            System.out.println("No recently played songs found for user2.");
        }
    }
}


