drop table if exists ExternalURL;
drop table if exists Blog;


create table Blog(ID binary(36) primary key,TITLE text NOT NULL, DESCRIPTION text DEFAULT NULL, GENRE text NOT NULL,PUBLISHED_ON date NOT NULL);

create table ExternalURL(EUID bigint not null AUTO_INCREMENT primary key,
 BLOG_ID binary(36) ,URL text NOT NULL,foreign key (BLOG_ID) references Blog(ID));