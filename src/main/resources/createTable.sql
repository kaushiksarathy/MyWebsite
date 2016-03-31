#drop database if exists mysite;
#CREATE  DATABASE if not exists mysite;

use mysite;

drop table if exists ExternalURL;
drop table if exists Blog;


create table Blog(ID binary(36) primary key,TITLE text NOT NULL, DESCRIPTION text DEFAULT NULL, GENRE text NOT NULL,PUBLISHED_ON date NOT NULL);

create table ExternalURL(EUID int NOT NULL AUTO_INCREMENT PRIMARY KEY,
 BLOG_ID binary(36)not null ,URL varchar(100) NOT NULL);
 

 #insert into ExternalURL(BLOG_ID,URL) values('8390d030995482188aa7caf9384f531d','ffa0');
 