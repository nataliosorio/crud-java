CREATE TABLE "Document_Types" (
  "id" int PRIMARY KEY,
  "name" varchar(50)
);

CREATE TABLE "Customers" (
  "id" int PRIMARY KEY,
  "first_name" varchar(50),
  "last_name" varchar(50),
  "document_type_id" int UNIQUE,
  "document_number" varchar(20) UNIQUE,
  "phone" varchar(15),
  "email" varchar(100) UNIQUE
);

CREATE TABLE "Cities" (
  "id" int PRIMARY KEY,
  "name" varchar(50)
);

CREATE TABLE "Hotels" (
  "id" int PRIMARY KEY,
  "name" varchar(100),
  "city_id" int,
  "address" varchar(255),
  "phone" varchar(20),
  "email" varchar(100),
  "stars" int
);

CREATE TABLE "Employees" (
  "id" int PRIMARY KEY,
  "first_name" varchar(50),
  "last_name" varchar(50),
  "role_id" int,
  "phone" varchar(15),
  "email" varchar(100) UNIQUE,
  "hotel_id" int
);

CREATE TABLE "Roles" (
  "id" int PRIMARY KEY,
  "name" varchar(50)
);

CREATE TABLE "Room_Types" (
  "id" int PRIMARY KEY,
  "name" varchar(50),
  "price_day" decimal(10,2),
  "price_night" decimal(10,2)
);

CREATE TABLE "Rooms" (
  "id" int PRIMARY KEY,
  "room_number" varchar(10) UNIQUE,
  "hotel_id" int,
  "room_type_id" int,
  "status" varchar(20)
);

CREATE TABLE "Reservations" (
  "id" int PRIMARY KEY,
  "customer_id" int,
  "check_in" datetime,
  "check_out" datetime,
  "total_price" decimal(10,2),
  "payment_method_id" int
);

CREATE TABLE "Reservation_Rooms" (
  "reservation_id" int,
  "room_id" int,
  "primary" key(reservation_id,room_id)
);

CREATE TABLE "Payment_Methods" (
  "id" int PRIMARY KEY,
  "name" varchar(50)
);

CREATE TABLE "Invoices" (
  "id" int PRIMARY KEY,
  "reservation_id" int,
  "issue_date" datetime,
  "total_amount" decimal(10,2)
);

ALTER TABLE "Customers" ADD FOREIGN KEY ("document_type_id") REFERENCES "Document_Types" ("id");

ALTER TABLE "Hotels" ADD FOREIGN KEY ("city_id") REFERENCES "Cities" ("id");

ALTER TABLE "Employees" ADD FOREIGN KEY ("role_id") REFERENCES "Roles" ("id");

ALTER TABLE "Employees" ADD FOREIGN KEY ("hotel_id") REFERENCES "Hotels" ("id");

ALTER TABLE "Rooms" ADD FOREIGN KEY ("hotel_id") REFERENCES "Hotels" ("id");

ALTER TABLE "Rooms" ADD FOREIGN KEY ("room_type_id") REFERENCES "Room_Types" ("id");

ALTER TABLE "Reservations" ADD FOREIGN KEY ("customer_id") REFERENCES "Customers" ("id");

ALTER TABLE "Reservations" ADD FOREIGN KEY ("payment_method_id") REFERENCES "Payment_Methods" ("id");

ALTER TABLE "Reservation_Rooms" ADD FOREIGN KEY ("reservation_id") REFERENCES "Reservations" ("id");

ALTER TABLE "Reservation_Rooms" ADD FOREIGN KEY ("room_id") REFERENCES "Rooms" ("id");

ALTER TABLE "Invoices" ADD FOREIGN KEY ("reservation_id") REFERENCES "Reservations" ("id");
