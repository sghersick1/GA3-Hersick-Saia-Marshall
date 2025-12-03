-- DDL for creating tables
create table users (
  id_user int  auto_increment,
  email varchar(64) not null,
  first_name varchar(64) not null,
  last_name varchar(64) not null,
  phone_number varchar(20) not null,
  language varchar(64) not null,
  country varchar(64) not null,
  city varchar(64) not null,
  street varchar(128) not null,
  zipcode varchar(16)not null,
  dob date not null,
  created_at datetime not null,
  constraint user_pk primary key(id_user),
  constraint user_email_uk unique key(email)
);

create table label (
  id_label int auto_increment,
  title varchar(64) not null,
  founded_year year not null,
  phone_number varchar(20),
  email varchar(64),
  constraint label_pk primary key(id_label),
  constraint label_title_uk unique key(title)
);

create table band (
  id_band int auto_increment,
  name varchar(64) not null,
  formed_year year not null,
  is_active int not null,
  id_label int,
  constraint band_pk primary key(id_band),
  constraint band_label_fk foreign key (id_label) references label(id_label) on delete restrict,
  constraint band_name_uk unique key(name)
);

create table genre (
  id_genre int auto_increment,
  name varchar(64) not null,
  description varchar(256) not null,
  popularity_index int,
  constraint genre_pk primary key(id_genre),
  constraint genere_name_uk unique key(name)
);

create table artist (
  id_artist int  auto_increment,
  name varchar(64) not null,
  dob date not null,
  country varchar(64) not null,
  picture blob,
  id_label int,
  id_user int,
  constraint artist_pk primary key(id_artist),
  constraint artist_label_fk foreign key (id_label) references label(id_label) on delete restrict,
  constraint user_artist_fk foreign key(id_user) references users(id_user) on delete restrict,
  constraint artist_name_uk unique key(name)
);

create table album (
  id_album int auto_increment,
  name varchar(64) not null,
  picture blob,
  date_released date not null,
  constraint album_pk primary key(id_album)
);

create table song (
  id_song int auto_increment,
  name varchar(64) not null,
  song_length time not null,
  file_size int not null,
  explicit boolean not null,
  date_released date not null,
  Picture blob,
  id_album int,
  constraint song_pk primary key(id_song),
  constraint song_album_fk foreign key (id_album) references album(id_album) on delete restrict
);

create table playlist (
  id_playlist int auto_increment,
  name varchar(64) not null,
  date_created datetime not null,
  constraint playlist_pk primary key(id_playlist)
  );

create table listening_history (
  id_listening_history int auto_increment,
  id_user int not null,
  played_at datetime not null,
  duration_played time not null,
  is_completed boolean not null,
  constraint lh_pk primary key(id_listening_history),
  constraint lh_user_fk foreign key (id_user) references users(id_user) on delete restrict
);

create table queue (
  id_queue int auto_increment,
  is_active boolean not null,
  modified_at datetime,
  shuffle_enabled boolean not null,
  position int,
  id_user int not null,
  constraint queue_pk primary key(id_queue),
  constraint queue_user_fk foreign key (id_user) references users(id_user) on delete restrict
);

create table song_genre (
  id_song  int not null,
  id_genre int not null,
  constraint genre_song_pk primary key(id_song, id_genre),
  constraint song_genre_song_fk  foreign key (id_song)  references song(id_song) on delete restrict,
  constraint song_genre_genre_fk foreign key (id_genre) references genre(id_genre) on delete restrict
);

create table album_genre (
  id_album int not null,
  id_genre int not null,
  Constraint genre_album_pk primary key (id_album, id_genre),
  constraint genre_album_album_fk foreign key (id_album) references album(id_album) on delete restrict,
  constraint genre_album_genre_fk foreign key (id_genre) references genre(id_genre) on delete restrict
);

create table band_genre (
  id_band  int not null,
  id_genre int not null,
  constraint genre_band_pk primary key(id_band, id_genre),
  constraint genre_band_band_fk  foreign key (id_band)  references band(id_band) on delete restrict,
  constraint genre_band_genre_fk foreign key (id_genre) references genre(id_genre) on delete restrict
);

create table artist_genre (
  id_artist int not null,
  id_genre  int not null,
  constraint artist_genre_pk primary key(id_artist, id_genre),
  constraint artist_genre_artist_fk foreign key (id_artist) references artist(id_artist) on delete restrict,
  constraint artist_genre_genre_fk  foreign key (id_genre)  references genre(id_genre) on delete restrict
);

create table song_artist (
  id_song int not null,
  id_artist int not null,
  constraint song_artist_pk primary key (id_song, id_artist),
  constraint song_artist_song_fk  foreign key (id_song)   references song(id_song) on delete restrict,
  constraint song_artist_artist_fk foreign key (id_artist) references artist(id_artist) on delete restrict
);

create table artist_band (
  id_artist int not null,
  id_band   int not null,
  position varchar(64),
  dt_start  date not null,
  dt_end  date,
  constraint artist_band_pk primary key (id_artist, id_band),
  constraint artist_band_artist_fk foreign key (id_artist) references artist(id_artist) on delete restrict,
  constraint artist_band_band_fk   foreign key (id_band)   references band(id_band) on delete restrict
);

create table album_artist (
  id_album  int not null,
  id_artist int not null,
  constraint album_artist_pk primary key (id_album, id_artist),
  constraint album_artist_album_fk  foreign key (id_album)  references album(id_album) on delete restrict,
  constraint album_artist_artist_fk foreign key (id_artist) references artist(id_artist) on delete restrict
);

create table queue_song (
  id_queue    int not null,
  id_song int not null,
  position_id int,
  added_at  datetime,
  constraint queue_song_pk primary key (id_queue, id_song),
  constraint queue_song_queue_fk foreign key (id_queue) references queue(id_queue) on delete restrict,
  constraint queue_song_song_fk  foreign key (id_song)  references song(id_song) on delete restrict
);

create table playlist_song (
  id_playlist int not null,
  id_song int not null,
  constraint playlist_song_pk primary key (id_playlist, id_song),
  constraint playlist_song_playlist_fk foreign key (id_playlist) references playlist(id_playlist) on delete restrict,
  constraint playlist_song_song_fk foreign key (id_song) references song(id_song) on delete restrict
);

create table user_playlist (
  id_user int not null,
  id_playlist int not null,
  constraint user_playlist_pk primary key (id_user, id_playlist),
  constraint user_playlist_user_fk foreign key (id_user) references users(id_user) on delete restrict,
  constraint user_playlist_playlist_fk foreign key (id_playlist) references playlist(id_playlist) on delete restrict
);

create table listening_history_song (
  Id_listening_history int not null,
  id_song int not null,
  constraint listening_history_song_pk primary key(id_listening_history, id_song),
  constraint listening_history_fk foreign key (Id_listening_history) references listening_history(Id_listening_history) on delete restrict,
  constraint song_fk foreign key (id_song) references song(id_song) on delete restrict
);


-- DML adding example rows to tables

-- users
insert into users (
  email, first_name, last_name, phone_number, language,
  country, city, street, zipcode, dob, created_at
)
values
  ('freddie.mercury@queen.com', 'freddie', 'mercury', '5551111975', 'english',
   'uk', 'london', '1 garden lodge', 'sw59eg', '1946-09-05', '2025-10-29 10:00:00'),
  ('steven.tyler@aerosmith.com', 'steven', 'tyler', '5552221973', 'english',
   'usa', 'boston', '1325 commonwealth ave', '02134', '1948-03-26', '2025-10-29 10:05:00');

-- label
insert into label (
  title, founded_year, phone_number, email
)
values
  ('emi records', 1931, '442071234567', 'info@emirecords.co.uk'),
  ('columbia records', 1901, '12125559876', 'contact@columbiarecords.com');

-- band
insert into band (name, formed_year, is_active, id_label)
values
  ('queen', 1970, 0, 1),
  ('aerosmith', 1970, 1, 2);


-- genre
insert into genre (
  name, description, popularity_index
)
values
  ('rock', 'a genre characterized by electric guitars, strong rhythms, and energetic performances', 98),
  ('hard rock', 'a style of rock music with heavier sound and powerful vocals popularized in the 70s and 80s', 92);

-- artist
insert into artist (name, dob, country, picture, id_label, id_user)
values
('freddie mercury','1946-09-05','uk',null,1,1),
('steven tyler','1948-03-26','usa',null,2,2);

-- album
insert into album (
  name, picture, date_released
)
values
  ('a night at the opera', null, '1975-11-21'),
  ('toys in the attic', null, '1975-04-08');

-- song
insert into song (
  name, song_length, file_size, explicit, date_released, picture, id_album
)
values
  ('bohemian rhapsody', '00:05:55', 6, 0, '1975-10-31', null, 1),
  ('sweet emotion', '00:04:34', 5, 0, '1975-04-08', null, 2);

-- playlist
insert into playlist (
  name, date_created
)
values
  ('70s rock anthems', '2025-10-29 12:00:00'),
  ('classic rock legends', '2025-10-29 12:05:00');

-- listening history
insert into listening_history (
  id_user, played_at, duration_played, is_completed
)
values
  (1, '2025-10-29 14:30:00', '00:05:55', 1),
  (2, '2025-10-29 15:00:00', '00:04:34', 1);

-- queue
insert into queue (
  is_active, modified_at, shuffle_enabled, position, id_user
)
values
  (1, '2025-10-29 16:00:00', 0, 1, 1),
  (0, '2025-10-29 16:05:00', 1, 2, 2);

