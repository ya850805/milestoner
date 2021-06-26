drop table if exists mission;
drop table if exists users;
drop table if exists profession;
drop table if exists tier;

create table if not exists profession (
    profession_id INT NOT NULL AUTO_INCREMENT comment 'primary key for profession',
    name VARCHAR(255) NOT NULL comment 'profession name',
    PRIMARY KEY ( profession_id )
) comment = 'All profession exists in database currently';

create table if not exists users (
    user_id INT NOT NULL AUTO_INCREMENT comment 'primary key for user',
    fk_profession_id INT NOT NULL comment 'foreign key mapping to profession table',
    email VARCHAR(255) NOT NULL comment 'user email',
    password VARCHAR(255) NOT NULL comment 'user password',
    name VARCHAR(255) NOT NULL comment 'user name',
    points INT NOT NULL DEFAULT 0 comment 'user current points',
    PRIMARY KEY ( user_id ),
    FOREIGN KEY ( fk_profession_id ) REFERENCES profession ( profession_id )
) comment = 'All users exists in database currently';

create table if not exists mission (
    mission_id INT NOT NULL AUTO_INCREMENT comment 'primary key for mission',
    fk_user_id INT NOT NULL comment 'foreign key mapping to user table',
    title VARCHAR(255) NOT NULL comment 'mission title',
    description VARCHAR(255) NOT NULL comment 'mission description',
    date DATE comment 'mission complete date predictably',
    status VARCHAR(1) NOT NULL DEFAULT '0' comment 'mission status. 0:initial, 1:completed, 2:failed',
    PRIMARY KEY ( mission_id ),
    FOREIGN KEY ( fk_user_id ) REFERENCES users ( user_id )
) comment = 'All mission once been created in database';

create table if not exists tier (
    tier_id INT NOT NULL AUTO_INCREMENT comment 'primary key for tier',
    name VARCHAR(255) NOT NULL comment 'tier name',
    lowest_points INT NOT NULL comment 'lowest points require to this tier',
    PRIMARY KEY ( tier_id )
) comment = 'All user tier in database currently';