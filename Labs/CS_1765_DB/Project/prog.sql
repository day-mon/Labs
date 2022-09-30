
CREATE DOMAIN PlayTitle AS VARCHAR(255)
CREATE DOMAIN PlayAuthor AS VARCHAR(255)
CREATE DOMAIN PlayType AS VARCHAR(255)
CREATE DOMAIN account AS INT(255)
CREATE DOMAIN PlayDate AS DATE
CREATE DOMAIN ProgramNumberCheck AS INT(10)
CHECK (VALUE IN (SELECT id
FROM Program));
CREATE TABLE PlayList
(
    title PlayTitle NOT NULL,
    author PlayAuthor NOT NULL,
    type PlayType NOT NULL,
    numOfActs actCount NOT NULL,
    date PlayDate NOT NULL,
    programId ProgramNumberCheck NOT NULL
        PRIMARY KEY ( date)
        FOREIGN KEY (programNumber) REFERENCES Program(number)
)
CREATE DOMAIN ContactableId AS INT(10)
CREATE DOMAIN ContactableName as VARCHAR(255)
CREATE DOMAIN ContactableEmail as VARCHAR(255)
CREATE DOMAIN ContactableAddress AS VARCHAR(255)
CREATE TABLE Contactable
(
    id ContactableId NOT NULL,
    name ContactableName NOT NULL,
    email ContactableEmail,
    address ContactableAddress,
    PRIMARY KEY (id)
)
CREATE DOMAIN ContactIDCheck AS INT(10)
CHECK (VALUE IN (SELECT id FROM Contactable))
CREATE DOMAIN TicketNumberCheck AS INT(10)
CHECK (VALUE IN (SELECT id from Tickets))
CREATE TABLE Attendee
(
    contactId ContactIDCheck NOT NULL,
    ticketId TicketNumberCheck NOT NULL
        PRIMARY KEY(contactId)
        FOREIGN KEY (ticketId) REFERENCES Tickets(id)
)
CREATE DOMAIN CreditNumber as INT(10)
CREATE DOMAIN Text AS VARCHAR(255)
CREATE TABLE Credits
(
    id CreditNumber NOT NULL,
    programNumber ProgramNumberCheck NOT NULL,
    text Text NOT NULL
        PRIMARY KEY (creditNumber)
        FOREIGN KEY (programNumber) REFERENCES Program(id)
);
CREATE DOMAIN TicketId AS INT(10)
CREATE DOMAIN SeatId AS INT(10)
CREATE DOMAIN Price AS DECIMAL(6, 2)
CREATE TABLE Tickets
(
    id TicketId NOT NULL,
    seatId SeatId NOT NULL,
    price Price NOT NULL,
    contactId ContactID NOT NULL,
    date PlayDate NOT NULL
        PRIMARY KEY (id)
        FOREIGN KEY (contactId) REFERENCES Contactable(id)
        FOREIGN KEY (date) REFERENCES PlayList (date)
)
CREATE DOMAIN SponsorDonation AS DECIMAL(6, 2)
CREATE DOMAIN SponsorType AS VARCHAR(255)
CREATE TABLE Sponsors
(
    contactId ContactID NOT NULL,
    donation SponsorDonation NOT NULL,
    date PlayDate NOT NULL,
    type SponsorType NOT NULL
        PRIMARY KEY (contactId)
        FOREIGN KEY (date) REFERENCES PlayList(date)
)
CREATE DOMAIN MemberDuesPaid AS BOOL
CREATE TABLE Member
(
    contactId ContactIDCheck NOT NULL,
    date PlayDate NOT NULL,
    duesPaid MemberDuesPaid NOT NULL
        PRIMARY KEY (contactId)
        FOREIGN KEY (date) REFERENCES PlayList(date)
)
CREATE DOMAIN ProgramNumber AS INT(10)
CREATE DOMAIN CreditNumberCheck AS INT(10)
CHECK (VALUE IN (SELECT id FROM Credits))
CREATE TABLE Program
(
    id ProgramNumber NOT NULL,
    contactId ContactIDCheck NOT NULL,
    creditNumber CreditNumberCheck NOT NULL
        PRIMARY KEY (id)
        FOREIGN KEY (contactId) REFERENCES Contactable(id)
        FOREIGN KEY (creditNumber) REFERENCES Credits(id)
)
