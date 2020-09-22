--insert into artist(id, name) values (1, 'Muse')
--insert into artist(id, name) values (2, 'Duran Duran')
--insert into artist(id, name) values (3, 'Van Halen')

insert into album(id, name, year_released) values (1, 'Drones', 2015)
insert into album(id, name, year_released) values (2, 'Origin of Symmetry', 2001)
insert into album(id, name, year_released) values (3, 'Rio', 1982)
insert into album(id, name, year_released) values (4, '1984', 1984)

--insert into artist_albums(artist_id, albums_id) values(1, 1)
--insert into artist_albums(artist_id, albums_id) values(1, 2)
--insert into artist_albums(artist_id, albums_id) values(2, 3)
--insert into artist_albums(artist_id, albums_id) values(3, 4)

insert into song(id, album_id, track, name) values(1, 1, 1, 'Dead Inside')
insert into song(id, album_id, track, name) values(2, 1, 2, 'Drill Sargeant')
insert into song(id, album_id, track, name) values(3, 1, 3, 'Psycho')
insert into song(id, album_id, track, name) values(4, 1, 4, 'Mercy')
insert into song(id, album_id, track, name) values(5, 1, 5, 'Reapers')
insert into song(id, album_id, track, name) values(6, 1, 6, 'The Handler')
insert into song(id, album_id, track, name) values(7, 1, 7, 'JFK')
insert into song(id, album_id, track, name) values(8, 1, 8, 'Defector')
insert into song(id, album_id, track, name) values(9, 1, 9, 'Revolt')
insert into song(id, album_id, track, name) values(10, 1, 10, 'Aftermath')
insert into song(id, album_id, track, name) values(11, 1, 11, 'The Globalist')
insert into song(id, album_id, track, name) values(12, 1, 12, 'Drones')

insert into song(album_id, track, name) values(2, 1, 'Intro')
insert into song(album_id, track, name) values(2, 2, 'Apocolypse Please')
insert into song(album_id, track, name) values(2, 3, 'Time is Running Out')
insert into song(album_id, track, name) values(2, 4, 'Sing for Absolution')
insert into song(album_id, track, name) values(2, 5, 'Stokholm Syndrome')
insert into song(album_id, track, name) values(2, 6, 'Falling Away With you')
insert into song(album_id, track, name) values(2, 7, 'Interlude')
insert into song(album_id, track, name) values(2, 8, 'Hysteria')
insert into song(album_id, track, name) values(2, 9, 'Blackout')
insert into song(album_id, track, name) values(2, 10, 'Butterflies and Hurricanes')
insert into song(album_id, track, name) values(2, 11, 'The Small Print')
insert into song(album_id, track, name) values(2, 12, 'Endlessly')
insert into song(album_id, track, name) values(2, 13, 'Thoughts of a Dying Athiest')
insert into song(album_id, track, name) values(2, 14, 'Ruled by Secrecy')
insert into song(album_id, track, name) values(2, 15, 'Fury')

insert into song(album_id, track, name) values(3, 1, 'Rio')
insert into song(album_id, track, name) values(3, 2, 'My Own Way')
insert into song(album_id, track, name) values(3, 3, 'Lonely in Your Nightmare')
insert into song(album_id, track, name) values(3, 4, 'Hungry Like the Wolf')
insert into song(album_id, track, name) values(3, 5, 'Hold Back the Rain')
insert into song(album_id, track, name) values(3, 6, 'New Religion')
insert into song(album_id, track, name) values(3, 7, 'Last Chance on the Stairway')
insert into song(album_id, track, name) values(3, 8, 'Save a Prayer')
insert into song(album_id, track, name) values(3, 9, 'The Chauffeur')

insert into song(album_id, track, name) values(4, 1, '1984')
insert into song(album_id, track, name) values(4, 2, 'Jump')
insert into song(album_id, track, name) values(4, 3, 'Panama')
insert into song(album_id, track, name) values(4, 4, 'Top Jimmy')
insert into song(album_id, track, name) values(4, 5, 'Drop Dead Legs')
insert into song(album_id, track, name) values(4, 6, 'Hot for Teacher')
insert into song(album_id, track, name) values(4, 7, 'Ill Wait')
insert into song(album_id, track, name) values(4, 8, 'Girl Gone Bad')
insert into song(album_id, track, name) values(4, 9, 'House of Pain')