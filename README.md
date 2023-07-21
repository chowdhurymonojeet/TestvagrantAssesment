# TestvagrantAssesment
Coding Assignment

This Java application implements an in-memory store for recently played songs. It can accommodate N songs per user and has a fixed initial capacity. The store allows users to store a list of song-user pairs, with each song linked to a user. When the store reaches its maximum capacity for a user, it automatically eliminates the least recently played songs to make room for new entries.

To use the RecentlyPlayedStore in your project, follow these steps:

1.Include the RecentlyPlayedStore class in your project.
2.Instantiate the RecentlyPlayedStore with the desired initial capacity and the maximum number of songs per user
3.Record songs played by users using the recordSong method:
4.Retrieve recently played songs for a user using the getRecentlyPlayedSongs method:
