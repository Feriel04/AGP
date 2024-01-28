-- Création de la table Destination
CREATE TABLE Destination (
    idDestination INT PRIMARY KEY,
    nomIle VARCHAR(255),
    url_photo varchar(200),
    description TEXT
);

INSERT INTO Destination (idDestination, nomIle, url_photo) VALUES
(1, 'Ile de Praslin','https://www.autigrevanille.ch/wp-content/uploads/2020/10/ile-praslin-nature-seychelles.jpg'),
(2,'Ile de Digue','https://as2.ftcdn.net/v2/jpg/03/21/91/15/1000_F_321911598_ZsE7VnXXz7FdGg8BiKRRK6lhfeCbEEue.jpg'),
(3,'Ile de Mahe','https://s1.travix.com/af/africa-seychelles-medium.jpg');

-- Création de la table Hotel
CREATE TABLE Hotel (
    idHotel INT PRIMARY KEY,
    idDestination INT,
    nom VARCHAR(255),
    adresse VARCHAR(255),
    url_photo varchar(200),
    plageIncluse BOOLEAN,
    gamme ENUM('Luxe', 'Standard', 'Intermédiaire'),
    prix DECIMAL(10, 2),
    FOREIGN KEY (idDestination) REFERENCES Destination(idDestination)
);

-- Exemples d'hôtels aux Seychelles
INSERT INTO Hotel (idHotel, idDestination, nom,adresse, url_photo, plageIncluse, gamme, prix) VALUES
(1, 1, 'Les lauriers Eco Hotel','Cote D''Or, Anse Volbert Village, Seychelles','https://cf.bstatic.com/xdata/images/hotel/max1024x768/247524136.jpg?k=32eec267b1114d48d0f0d8bf208296bc3cd50d4453a67e4aafa6761b5a0b7266&o=&hp=1', true, 'Luxe', 350.00),
(2, 1, 'Villa Anse La Blague','Anse La Blague, Baie Sainte-Anne, Seychelles','https://www.seyvillas.com/img/objects/104/1366x792_75/villa-anse-lablague-a-0063.jpg', true, 'Standard', 170.00),
(3, 2, 'Le Nautique Waterfront Hotel La Digue','La pass, La Digue, Seychelles','https://lh3.googleusercontent.com/proxy/gByVwRAgbuabBOJVsKJEFz80OgugZ2NQHBtgdsZk_ZZrsxvKwkozChyiww2-UZX_gBlrHYAIBFkAGkTgfKiGksdrWcfm9JU-NVapp8QGeA0uXaPsVJYG7hyMaXY7JMF04u-nmvT8iVqwRqQXyIbiTcsbBq1DWEg_QD75Uvr4D7mdsY2KhAtWIqVR2UJkQ8V6kaG1Gm6HZ-c1F86Kmm40lgvLjx78L5-iSM8YINu0qs411JQC1NVUrI4', true, 'Luxe', 450.00),
(4, 3, 'Horizon view','Anse a la mouche Anse boileau, Victoria, Seychelles','https://lh3.googleusercontent.com/p/AF1QipMDH_0JU4211Lw_aPKRkJAIrkuNbkHRArftajGE=s680-w680-h510', true, 'Intermediaire', 220.00),
(5,3,'Anantara Maia Seychelles Villas','West Coast Road, Anse Louis, Mahé, Seychelles','https://cf.bstatic.com/xdata/images/hotel/max1024x768/209884876.jpg?k=4f4774adc6d7cf0c7832db77608e81c3c5bbe9fd75032d1d93403c78c17e9ef9&o=&hp=1',true,'Luxe',600.00);
-- Création de la table Excursion
CREATE TABLE Excursion (
    idExcursion INT PRIMARY KEY,
    idHotel INT,
    dateExcursion DATE,
    nombreExcursionsMax INT,
    FOREIGN KEY (idHotel) REFERENCES Hotel(idHotel)
);
-- Exemples d'excursions pour un hôtel aux Seychelles
INSERT INTO Excursion (idExcursion, idHotel, dateExcursion, nombreExcursionsMax) VALUES
(1, 1, '2024-02-01', 2),
(2, 2, '2024-02-02', 1),
(3, 3, '2024-03-10', 2),
(4, 4, '2024-06-10', 2),
(5, 5, '2024-08-03', 0);



-- Création de la table SiteTouristique
CREATE TABLE SiteTouristique (
    idSite INT PRIMARY KEY,
    idDestination INT,
    idExcursion INT,
    nom VARCHAR(255),
    adresse VARCHAR(255),
    descriptionTextuelle TEXT,
    url_photo varchar(200),
    typeSite ENUM('historique', 'loisir'),
    FOREIGN KEY (idDestination) REFERENCES Destination(idDestination),
    FOREIGN KEY (idExcursion) REFERENCES Excursion(idExcursion)
);



-- Sites historiques
INSERT INTO SiteTouristique (idSite, idDestination,idExcursion, nom,adresse,descriptionTextuelle,url_photo, typeSite) VALUES
(1, 1, 3, 'SAGITTARIUS TAXI BOAT','Côte d''Or Côte D''or','Sagittarius est en activité depuis 1999 et est situé sur la célèbre plage de Cote D''or, a cote du Berjaya Praslin Beach Resort. Nous proposons des excursions vers toutes les iles environnantes, pour la plongee en apnee, la peche, des croisieres d''une journée avec barbecue, ou simplement une croisière au coucher du soleil.','https://www.kreoladventures.com/app/web/upload/large/taxi-boat-850.jpg', 'loisir'),
(2, 2, 2, 'Les tortues géantes','JRPG+FCH, La Digue, Seychelles','Dans l''enclos de l''Union Estate et ailleurs sur l''ile, on peut observer de près une tortue géante endémique à l''atoll d''Aldabra, situé au sud de l''archipel. Cette tortue est l''espèces la plus grosse au monde puisqu''elle peut peser jusqu''a 300 kilos et mesurer plus d''un mètre de long.','https://www.seychelles.fr/img/tortue-pondeuse.jpg', 'loisir'),
(3, 3, 1, 'clock tower','Independence Avenue, Victoria, Mahe Island, Seychelles', 'La Clock Tower Victoria est aux Seychellois ce que Big Ben est aux Anglais : une horloge emblématique servant de point de repère aux habitants. Ce monument est situé à Victoria, la capitale des Seychelles, sur l''ile de Mahé. Il s''agit de la réplique de l''horloge londonienne Vauxhall Clock Tower. Erigée en 1903, elle est haute de 12 mètres et carillonne toutes les heures.','https://images-ext-2.discordapp.net/external/6rsKYuhZJjSXiLopmWzfLz8KFlstHVid6jnA0LM7GQw/%3Fw%3D500%26h%3D-1%26s%3D1/https/dynamic-media-cdn.tripadvisor.com/media/photo-o/17/62/b5/9c/photo0jpg.jpg?format=webp&width=625&height=468', 'historique');



-- Création de la table TrajetHotel
CREATE TABLE Trajet (
    idTrajet INT PRIMARY KEY,
    distance DECIMAL(10, 2),
    prix DECIMAL(10, 2),
    transport ENUM('à pied', 'autobus', 'bateau'),
    idExcursion INT,
    idHotelDepart INT,
    idHotelArrivee INT,
    idSiteDepart INT,
    idSiteArrivee INT,
    FOREIGN KEY (idHotelDepart) REFERENCES Hotel(idHotel),
    FOREIGN KEY (idHotelArrivee) REFERENCES Hotel(idHotel),
    FOREIGN KEY (idSiteDepart) REFERENCES SiteTouristique(idSite),
    FOREIGN KEY (idSiteArrivee) REFERENCES SiteTouristique(idSite),
    FOREIGN KEY (idExcursion) REFERENCES Excursion(idExcursion)
);


INSERT INTO Trajet (idTrajet, distance,prix, transport, idExcursion, idHotelDepart, idHotelArrivee,idSiteDepart,idSiteArrivee) VALUES
(1, 10.5, 50.00, 'autobus', 1 , 1,null,null,1),
(2, 20.0, 75.00, 'bateau', 2 , null,1,null,2);
