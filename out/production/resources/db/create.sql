SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS team (
    id int PRIMARY KEY auto_increment,
    teamName VARCHAR,
    teamDescription VARCHAR,
);

CREATE TABLE IF NOT EXISTS member (
    id int PRIMARY KEY auto_increment,
    memberName VARCHAR,
);