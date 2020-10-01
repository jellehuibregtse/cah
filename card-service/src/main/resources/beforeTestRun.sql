create table if not exists cards (id bigint primary key, card_type integer, card_text varchar(255));

insert into cards (id, card_type, card_text) values (1, 1, 'card-text-for-testing'), (2, 2, 'card-text-for-testing');