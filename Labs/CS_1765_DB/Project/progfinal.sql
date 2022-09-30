CREATE DATABASE
-- auto-generated definition
create database ourdbproject
   with owner postgres;


CREATE TABLE STATEMENTS

create table if not exists "Attendee"
(
    "contactId" "ContactableId" default nextval('"Attendee_contactId_seq"'::regclass) not null
    primary key
    constraint "contactId"
    references "Contactable",
    "ticketId"  "TicketId"      default nextval('"Attendee_ticketId_seq"'::regclass)  not null
    constraint attendee_tickets_ticketid_fk
    references "Tickets"
    );








create table if not exists "Contactable"
(
    "contactId" "ContactableId" default nextval('"Contactable_contactId_seq"'::regclass) not null
    primary key
    references "Contactable",
    name        "ContactableName"                                                        not null,
    email       "ContactableAddress"                                                     not null,
    address     "ContactableAddress"                                                     not null
    );




create table if not exists "Member"
(
    "contactId" "ContactableId" default nextval('"Member_contactId_seq"'::regclass) not null
    primary key
    references "Contactable",
    date        "PlayDate "                                                         not null
    constraint "member_play listing_date_fk"
    references "Play Listing",
    "duesPaid"  "MemberDuesPaid"                                                    not null
    );





create table if not exists "Play Listing"
(
    title       "PlayTitle"     not null,
    author      "PlayAuthor "   not null,
    type        "PlayType "     not null,
    acts        "ActCount"      not null,
    date        "PlayDate "     not null
    primary key,
    "programId" "ProgramNumber" not null
    references "Program"
);












create table if not exists "Program"
(
    "programId" "ProgramNumber" default nextval('"Program_programId_seq"'::regclass) not null
    primary key,
    "contactId" "ContactableId" default nextval('"Program_contactId_seq"'::regclass) not null
    references "Contactable",
    "creditsId" "CreditNumber"  default nextval('"Program_creditsId_seq"'::regclass) not null,
    text        text
    );






create table if not exists "Sponsors"
(
    "contactId" "ContactableId" default nextval('"Sponsors_contactId_seq"'::regclass) not null
    primary key
    references "Contactable",
    donation    "SponsorDonation"                                                     not null,
    date        "PlayDate "                                                           not null
    constraint "sponsors_play listing_date_fk"
    references "Play Listing",
    type        "SponsorType"                                                         not null
    );






create table if not exists "Tickets"
(
    "ticketId"   "TicketId"      default nextval('"Tickets_ticketId_seq"'::regclass)  not null
    primary key
    references "Tickets",
    "seatNumber" "SeatId"                                                             not null,
    price        "TicketPrice"                                                        not null,
    "contactId"  "ContactableId" default nextval('"Tickets_contactId_seq"'::regclass) not null,
    date         "TicketDate"                                                         not null
    );






VIEWs
create view attendeeview("contactId", donation) as
SELECT p."contactId",
       s.donation
FROM "Program" p
         JOIN "Sponsors" s ON p."contactId"::integer = s."contactId"::integer
WHERE s.donation::double precision > 1000::double precision;




create view playlistingview(type, title, "duesPaid", "contactId") as
SELECT p.type,
       p.title,
       m."duesPaid",
       m."contactId"
FROM "Play Listing" p
         JOIN "Member" m ON p.date::date = m.date::date
WHERE p.author::text = 'Sandro'::text;




create view programview("contactId", text, title, date, acts) as
SELECT p."contactId",
       p.text,
       pl.title,
       pl.date,
       pl.acts
FROM "Program" p
         JOIN "Play Listing" pl ON p."programId"::integer = pl."programId"::integer
WHERE pl.type::text = 'Comedy'::text;




create view sponsorsview(donation, "contactId", "seatNumber", type) as
SELECT s.donation,
       a."contactId",
       t."seatNumber",
       s.type
FROM "Sponsors" s
         JOIN "Tickets" t ON s."contactId"::integer = t."contactId"::integer
        JOIN "Attendee" a ON t."ticketId"::integer = a."ticketId"::integer
WHERE s.date::date = '2021-12-11'::date
  OR s.date::date = '2021-12-13'::date;




create view ticketview(price, "seatNumber", "contactId") as
SELECT t.price,
       t."seatNumber",
       a."contactId"
FROM "Tickets" t
         LEFT JOIN "Attendee" a ON t."ticketId"::integer = a."ticketId"::integer
WHERE a."ticketId"::integer = 2
  OR a."ticketId"::integer = 5;


