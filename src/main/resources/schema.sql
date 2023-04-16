create table if not exists customers (
     id SERIAL PRIMARY KEY,
     name TEXT NOT NULL
);

create table if not exists accounts (
    id SERIAL PRIMARY KEY,
    country text not null,
    customer_id INTEGER NOT NULL REFERENCES customers(id)
    );

create table if not exists currencies (
      id SERIAL PRIMARY KEY,
      code TEXT NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS balances (
    id SERIAL,
    account_id INTEGER NOT NULL REFERENCES accounts(id),
    currency_id INTEGER NOT NULL REFERENCES currencies(id),
    available_amount NUMERIC NOT NULL,
    PRIMARY KEY (account_id, currency_id)
    );

create table if not exists transactions (
    id SERIAL PRIMARY KEY,
    account_id INTEGER NOT NULL REFERENCES accounts(id),
    amount NUMERIC NOT NULL,
    currency_id INTEGER NOT NULL REFERENCES currencies(id),
    direction TEXT NOT NULL,
    description TEXT NOT NULL
    );