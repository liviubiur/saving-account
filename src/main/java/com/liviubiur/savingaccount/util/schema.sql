CREATE TABLE users (
     username character varying(50) UNIQUE NOT NULL PRIMARY KEY,
     password character varying(100) NOT NULL,
     email character varying(50) UNIQUE NOT NULL,
     role character varying(50) NOT NULL,
     enabled boolean NOT NULL DEFAULT true
 );

CREATE TABLE public.saving_accounts
(
	id SERIAL PRIMARY KEY,
    users_username character varying(50) UNIQUE NOT NULL,
    balance decimal NOT NULL
);

ALTER TABLE saving_accounts ADD FOREIGN KEY(users_username) REFERENCES users (username);