package org.MemoryStore;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;


public class RecentlyPlayedStoreTest {

    private RecentlyPlayedStore store;

    @BeforeEach
    public void setUp() {
        store = new RecentlyPlayedStore(1000, 3);
    }

    @Test
    public void testRecentlyPlayedSongs() {
        store.recordSong("user1", "S1");
        store.recordSong("user1", "S2");
        store.recordSong("user1", "S3");

        LinkedList<String> recentlyPlayedUser1 = store.getRecentlyPlayedSongs("user1");
        Assertions.assertNotNull(recentlyPlayedUser1);
        Assertions.assertEquals(3, recentlyPlayedUser1.size());
        Assertions.assertEquals("S1", recentlyPlayedUser1.get(0));
        Assertions.assertEquals("S2", recentlyPlayedUser1.get(1));
        Assertions.assertEquals("S3", recentlyPlayedUser1.get(2));

        store.recordSong("user1", "S4");
        recentlyPlayedUser1 = store.getRecentlyPlayedSongs("user1");
        Assertions.assertEquals(3, recentlyPlayedUser1.size());
        Assertions.assertEquals("S2", recentlyPlayedUser1.get(0));
        Assertions.assertEquals("S3", recentlyPlayedUser1.get(1));
        Assertions.assertEquals("S4", recentlyPlayedUser1.get(2));

        store.recordSong("user1", "S2");
        recentlyPlayedUser1 = store.getRecentlyPlayedSongs("user1");
        Assertions.assertEquals(3, recentlyPlayedUser1.size());
        Assertions.assertEquals("S3", recentlyPlayedUser1.get(0));
        Assertions.assertEquals("S4", recentlyPlayedUser1.get(1));
        Assertions.assertEquals("S2", recentlyPlayedUser1.get(2));

        store.recordSong("user1", "S1");
        recentlyPlayedUser1 = store.getRecentlyPlayedSongs("user1");
        Assertions.assertEquals(3, recentlyPlayedUser1.size());
        Assertions.assertEquals("S4", recentlyPlayedUser1.get(0));
        Assertions.assertEquals("S2", recentlyPlayedUser1.get(1));
        Assertions.assertEquals("S1", recentlyPlayedUser1.get(2));
    }

    @Test
    public void testEmptyRecentlyPlayedSongs() {
        LinkedList<String> recentlyPlayedUser1 = store.getRecentlyPlayedSongs("user1");
        Assertions.assertNull(recentlyPlayedUser1);
    }

    @Test
    public void testNonExistentUser() {
        store.recordSong("user2", "Song1");
        LinkedList<String> recentlyPlayedUser1 = store.getRecentlyPlayedSongs("user1");
        LinkedList<String> recentlyPlayedUser2 = store.getRecentlyPlayedSongs("user2");

        Assertions.assertNull(recentlyPlayedUser1);
        Assertions.assertNotNull(recentlyPlayedUser2);
        Assertions.assertEquals(1, recentlyPlayedUser2.size());
        Assertions.assertEquals("Song1", recentlyPlayedUser2.get(0));
    }
}

