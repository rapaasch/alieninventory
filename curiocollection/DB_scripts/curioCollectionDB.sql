DROP DATABASE IF EXISTS curio_collection;
CREATE DATABASE curio_collection;

USE curio_collection;


DROP TABLE IF EXISTS location;
CREATE TABLE location (
	locId INT PRIMARY KEY AUTO_INCREMENT,
    locName VARCHAR(40) NOT NULL,
    civilization VARCHAR(80),
    galaxy VARCHAR(40),
    era VARCHAR(40)
);

DROP TABLE IF EXISTS items;
CREATE TABLE items (
	itemId INT PRIMARY KEY AUTO_INCREMENT,
    itemName VARCHAR(40) NOT NULL,
    itemAbout TEXT,
    quantity INT,
    itemValue DECIMAL(10,2),
    dateFound date,
    isFeatured tinyint(1), 
    locId INT NOT NULL,
    CONSTRAINT fk_location_items FOREIGN KEY (locId) REFERENCES location (locId) ON DELETE CASCADE
);

DROP TABLE IF EXISTS categories;
CREATE TABLE categories (
	catId INT PRIMARY KEY AUTO_INCREMENT,
    catName VARCHAR(40) NOT NULL,
    catAbout TEXT
);

DROP TABLE IF EXISTS author;
CREATE TABLE author (
	authorId INT PRIMARY KEY AUTO_INCREMENT,
    authorName VARCHAR(40) NOT NULL,
    userName VARCHAR(40) NOT NULL,
    isEnabled TINYINT(1),
    authorPassword VARCHAR(40) NOT NULL
);

DROP TABLE IF EXISTS roles;
CREATE TABLE roles (
	rolesId INT PRIMARY KEY AUTO_INCREMENT,
    roleName VARCHAR(40) NOT NULL
);

DROP TABLE IF EXISTS userRoles;
CREATE TABLE userRoles (
	id INT PRIMARY KEY AUTO_INCREMENT,
    authorId INT NOT NULL,
    rolesId INT NOT NULL,
    CONSTRAINT fk_author_userRoles FOREIGN KEY (authorId) REFERENCES author (authorId) ON DELETE CASCADE,
    CONSTRAINT fk_roles_userRoles FOREIGN KEY (rolesId) REFERENCES roles (rolesId) ON DELETE CASCADE
);

DROP TABLE IF EXISTS article;
CREATE TABLE article (
	articleId INT PRIMARY KEY AUTO_INCREMENT,
    authorId INT NOT NULL,
    articleTitle VARCHAR(40) NOT NULL,
    articleText TEXT,
    CONSTRAINT fk_author_article FOREIGN KEY (authorId) REFERENCES author (authorId) ON DELETE CASCADE
);

DROP TABLE IF EXISTS itemType;
CREATE TABLE itemType (
	itemTypeId INT PRIMARY KEY AUTO_INCREMENT,
    itemId INT NOT NULL,
    catId INT NOT NULL,
    CONSTRAINT fk_items_itemType FOREIGN KEY (itemId) REFERENCES items (itemId),
    CONSTRAINT fk_categories_itemType FOREIGN KEY (catId) REFERENCES categories (catId) 
);

DROP TABLE IF EXISTS itemArticles;
CREATE TABLE itemArticles (
	itemArticlesId INT PRIMARY KEY AUTO_INCREMENT,
    itemId INT NOT NULL,
    articleId INT NOT NULL,
    CONSTRAINT fk_items_itemArticles FOREIGN KEY (itemId) REFERENCES items (itemId) ON DELETE CASCADE,
    CONSTRAINT fk_categories_itemArticles FOREIGN KEY (articleId) REFERENCES article (articleId) ON DELETE CASCADE
);

INSERT INTO `curio_collection`.`author` (`authorName`, `userName`, `authorPassword`) 
	VALUES ('Amy Pond', 'apond', '1234'),
	('The Master', 'eeeeevil1', '1234'),
	('Jack Harkness', 'captainjack', '1234'),
	('Rose Tyler', 'rosiet', '1234'),
	('Ianto Jones', 'iheartjack', '1234'),
	('Austyn Hill', 'lilacllama', '1234'),
	('Martha Jones', 'drsdoctor', '1234');

INSERT INTO `curio_collection`.`categories` (`catName`, `catAbout`) 
	VALUES ('Shiny', 'oooh look'),
	('Power Source', 'power sources for time travel'),
	('Food', 'things I put in my mouth'),
	('Weapon', 'scares the meanies away'),
	('Tech', 'machine thingies '),
	('Body Part', 'things that are, or were once, alive'),
	('Human', 'human origin'),
	('Alien', 'alien origin');

INSERT INTO `curio_collection`.`location` (`locName`, `civilization`, `galaxy`, `era`) 
	VALUES ('Tiaanamat', 'Sun-singers of Akhet', 'Andromeda', 'prehistoric'),
	('Beta Two', 'unknown', 'Perugellis', 'Renaissance'),
	('Cashel', 'Tollund and Lindow', 'Milky Way', 'Ancient Greece'),
	('Planet of the Coffee Shops', 'Top Ten Destinations for the Discerning Traveller', 'Number One', 'Victorian'),
	('Raxacoricofallapatorius', 'Raxacoricofallapatorians', 'CLom', 'Roaring Twenties');


INSERT INTO `curio_collection`.`article` (`authorId`, `articleTitle`, `articleText`) 
	VALUES ('3', 'A dandy and a clown', 'They do exactly what you tell them at amazing speed. Even if you order them to kill you. So if you do happen to change your mind, it\'s very difficult to stop them from obeying the original order. But not impossible. Mortuaries and larders. Easiest things to break out of. Then, all things considered...it\'s time I grew up. I love humans. Always seeing patterns in things that aren\'t there.'),
	('2', 'Don\'t be lasagna', 'Pure mathematics can not lie! Oh I\'m so sorry, Jamie. You know how it is; you put things off for a day and next thing you know, it\'s a hundred years later. Usually called \'The Doctor.\' Or \'The Caretaker.\' Or \'Get off this planet.\' Though, strictly speaking, that probably isn\'t a name. My dear Miss Shaw, I never report myself anywhere, particularly not forthwith. How can you miss me? I\'m easy to find. I\'m the guy with two hearts, remember?'),
	('4', 'Please tell me I didn\'t get old', 'Anything but old. I was young! Oh... is he grey? Your species has the most amazing capacity for self-deception, matched only by its ingenuity when trying to destroy itself. If you could touch the alien sand and hear the cries of strange birds, and watch them wheel in another sky, would that satisfy you? You cannot rewrite history! Not one line! You are the only mystery worth solving. The best thing about a machine that makes sense is you can very easily make it turn out nonsense.'),
	('2', 'I\'m Scottish', 'I can complain about things now. I can really complain about things now. Smith. Doctor John Smith. Sorry, must dash! Fantastic! I don\'t like it. You ain\'t seen nothing yet! I\'ll tell you what, then: don\'t...step on any butterflies. What have butterflies ever done to you? Usually called \'The Doctor.\' Or \'The Caretaker.\' Or \'Get off this planet.\' Though, strictly speaking, that probably isn\'t a name. And in that battle there was a man with more blood on his hands than any other. A man who would commit a crime that would silence the universe. And that man was me.'),
	('1', 'Am I having a midlife crisis?', 'You may disguise your features but you can never disguise your intent. We are not of this race. We are not of this earth. Susan and I are wanderers in the fourth dimension of time and space. [The Hand of Omega] is called that because Time Lords have an infinite capacity for pretension. He thinks I\'m a charlatan, my Dear. When I say run, run. ...RUN! But it was a childish dream that made you a doctor. You dreamed you could hold back death. Isn\'t that true? You don\'t understand so you find excuses.'),
	('1', 'No. That is not the question.', 'That is not where we start. Well Kangs, I must say, there\'s no place like home... and this is no place like home. You don\'t understand so you find excuses. Great men are forged in fire. It is the privilege of lesser men to light the flame. Whatever the cost. [The Hand of Omega] is called that because Time Lords have an infinite capacity for pretension. There are fixed points throughout time where things must stay exactly the way they are. This is not one of them. This is an opportunity! Whatever happens here will create its own timeline, its own reality, a temporal tipping point. The future revolves around you, here, now, so do good!');

INSERT INTO `curio_collection`.`items` (`itemName`, `itemAbout`, `quantity`, `itemValue`, `dateFound`, `locId`) 
	VALUES ('Normal Spoon', 'A spoon', '1', '1253.20', '2002-12-12', '2'),
	('Normal Wine Glass', 'A wine glass', '2', '102.32', '1968-11-04', '1'),
	('Normal Mug', 'A mug', '1', '99999.99', '1102-03-03', '3'),
	('Normal Toothbrush', 'A toothbrush', '4', '0.15', '2458-09-02', '3'),
	('Normal Watering Can', 'A watering can', '7', '2.75', '1999-12-06', '4'),
    ('Normal Fork', 'A fork', '1', '99999.99', '1102-03-03', '3'),
    ('Normal Ruler', 'A ruler', '1', '99999.99', '1102-03-03', '3'),
    ('Normal Key', 'A key', '1', '99999.99', '1102-03-03', '3');

INSERT INTO `curio_collection`.`itemarticles` (`itemId`, `articleId`) 
	VALUES ('2', '2'),
	('2', '4'),
	('1', '3'),
	('3', '1'),
	('5', '5');

INSERT INTO `curio_collection`.`itemtype` (`itemId`, `catId`) 
	VALUES ('1', '3'),
	('2', '4'),
	('2', '3'),
	('2', '1'),
	('4', '4'),
	('5', '2'),
	('5', '4');
