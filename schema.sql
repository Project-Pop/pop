create database pop;
use pop;
CREATE TABLE Users (
    userId CHAR(36) PRIMARY KEY,
    username VARCHAR(25) UNIQUE NOT NULL,
    fullname VARCHAR(50),
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(13) UNIQUE NOT NULL,
    dob DATE 
);
CREATE TABLE UserProfile(
	username VARCHAR(25) PRIMARY KEY,
    imageUrl VARCHAR(100),
    views int default(0),
    reacts int default(0),
    popScore int default(0),
    followers int default(0),
    following int default(0),
    bio VARCHAR(1000),
    FOREIGN KEY (username) REFERENCES Users(username) ON DELETE CASCADE
);


CREATE TABLE Posts(
	postId CHAR(36) PRIMARY KEY,
    imageUrl varchar(100),
    username VARCHAR(25),
    views INT DEFAULT(0),
    description VARCHAR(300),
    timeStamp date,
	FOREIGN KEY (username) REFERENCES Users(username) ON DELETE CASCADE,
    INDEX (username)
);

CREATE TABLE UserReactions(
	username VARCHAR(25),
    postId CHAR(36),
    reactionString varchar(100),
    PRIMARY KEY (postId, username),
    FOREIGN KEY (username) REFERENCES Users(username) ON DELETE CASCADE,
	FOREIGN KEY (postId) REFERENCES Posts(postId) ON DELETE CASCADE,
    INDEX (reactionString)
);
CREATE TABLE Tagged(
	postId CHAR(36),
    username VARCHAR(25),
    approvalStatus bool default(false),
	PRIMARY KEY (postId, username),
    INDEX (username),
    FOREIGN KEY (username) REFERENCES Users(username) ON DELETE CASCADE,
	FOREIGN KEY (postId) REFERENCES Posts(postId) ON DELETE CASCADE
);
CREATE TABLE PostViews(
	postId CHAR(36),
    username VARCHAR(25),
    PRIMARY KEY (postId, username),
    FOREIGN KEY (username) REFERENCES Users(username) ON DELETE CASCADE,
	FOREIGN KEY (postId) REFERENCES Posts(postId) ON DELETE CASCADE
);

CREATE TABLE Follows(
	username VARCHAR(25),
    followerUsername CHAR(36),
    PRIMARY KEY (username, followerUsername),
    FOREIGN KEY (username) REFERENCES Users(username) ON DELETE CASCADE,
    FOREIGN KEY (followerUsername) REFERENCES Users(username) ON DELETE CASCADE
);

create table Comments(
	commentId CHAR(36) PRIMARY KEY,
    postId CHAR(36),
    username VARCHAR(25),
    likeCount int DEFAULT(0),
    message varchar(1000),
    FOREIGN KEY (postId) REFERENCES Posts(postId),
    FOREIGN KEY (username) REFERENCES Users(username),
    INDEX (postId)
);

create TABLE CommentsReactionCounter(
	commentId CHAR(36),
    username CHAR(36),
    PRIMARY KEY (commentId, username),
    FOREIGN KEY (username) REFERENCES Users(username) ON DELETE CASCADE,
    FOREIGN KEY (commentId) REFERENCES Comments(commentId) ON DELETE CASCADE
);
