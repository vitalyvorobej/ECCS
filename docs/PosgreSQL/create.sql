CREATE TABLE "card" (
	"id" serial NOT NULL,
	"date_registration" TIMESTAMP NOT NULL,
	"client_id" integer NOT NULL,
	"active" BOOLEAN NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT card_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "client" (
	"id" serial NOT NULL,
	"first_name" character varying(30) NOT NULL,
	"last_name" character varying(50) NOT NULL,
	"birthday_date" TIMESTAMP NOT NULL,
	"phone_number" character varying(25),
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT client_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "order_object" (
	"id" serial NOT NULL,
	"start_time" TIMESTAMP NOT NULL,
	"end_time" TIMESTAMP NOT NULL,
	"card_id" integer NOT NULL,
	"ticket_type_id" integer NOT NULL,
	"ticket_price" DECIMAL NOT NULL,
	"bracelet_id" integer NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT order_object_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "ticket_type" (
	"id" serial NOT NULL,
	"name" character varying(30) NOT NULL,
	"price" DECIMAL NOT NULL,
	"deleted" BOOLEAN NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT ticket_type_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "payment" (
	"id" serial NOT NULL,
	"amount" DECIMAL NOT NULL,
	"order_id" integer NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"payment_type" character varying(15) NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT payment_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "bracelet" (
	"id" serial NOT NULL,
	"uuid" character varying(25) NOT NULL UNIQUE,
	"free" BOOLEAN NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT bracelet_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "user_account" (
	"id" serial NOT NULL,
	"email" character varying(40) NOT NULL UNIQUE,
	"password" character varying(30) NOT NULL,
	"role" character varying(15) NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT user_account_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "card" ADD CONSTRAINT "card_fk0" FOREIGN KEY ("client_id") REFERENCES "client"("id");


ALTER TABLE "order_object" ADD CONSTRAINT "order_object_fk0" FOREIGN KEY ("card_id") REFERENCES "card"("id");
ALTER TABLE "order_object" ADD CONSTRAINT "order_object_fk1" FOREIGN KEY ("ticket_type_id") REFERENCES "ticket_type"("id");
ALTER TABLE "order_object" ADD CONSTRAINT "order_object_fk2" FOREIGN KEY ("bracelet_id") REFERENCES "bracelet"("id");


ALTER TABLE "payment" ADD CONSTRAINT "payment_fk0" FOREIGN KEY ("order_id") REFERENCES "order_object"("id");


