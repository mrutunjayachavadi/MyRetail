CREATE SCHEMA myretail AUTHORIZATION postgres;
GRANT ALL ON SCHEMA myretail TO postgres;


CREATE TABLE myretail.current_price
(
    id bigserial PRIMARY KEY,
    value double precision,
    currency character varying(10)
);
ALTER TABLE myretail.current_price
    OWNER to postgres;
CREATE TABLE myretail.product
(
    id bigserial,
    name character varying(100),
    current_price_id bigint,
    CONSTRAINT product_current_price_id_fkey FOREIGN KEY (current_price_id)
        REFERENCES myretail.current_price (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
ALTER TABLE myretail.product
    OWNER to postgres;