CREATE TABLE words(
	word_id varchar(100) not null primary key default gen_random_uuid(),
	word text not null,
	meaning text not null,
	added_on timestamp not null default NOW(),
	last_edit timestamp
);