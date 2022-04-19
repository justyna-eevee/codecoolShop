DROP TABLE IF EXISTS public.product CASCADE;
CREATE TABLE public.product
(
    id          serial  NOT NULL PRIMARY KEY,
    name        text    NOT NULL,
    description text,
    price       float   NOT NULL,
    currency    text    NOT NULL,
    supplierId  integer NOT NULL,
    categoryId  integer NOT NULL,
    image       text
);

DROP TABLE IF EXISTS public.supplier CASCADE;
CREATE TABLE public.supplier
(
    id     serial  NOT NULL PRIMARY KEY,
    name   text    NOT NULL UNIQUE
);

DROP TABLE IF EXISTS public.shopUser CASCADE;
CREATE TABLE public.shopUser
(
    id     serial  NOT NULL PRIMARY KEY,
    name   text    NOT NULL UNIQUE
);

DROP TABLE IF EXISTS public.category CASCADE;
CREATE TABLE public.category
(
    id     serial  NOT NULL PRIMARY KEY,
    name   text    NOT NULL UNIQUE
);

DROP TABLE IF EXISTS public.productForBasket CASCADE;
CREATE TABLE public.productForBasket
(
    id          serial  NOT NULL PRIMARY KEY,
    productId     integer NOT NULL,
    quantity     integer default 1,
    basketId     integer NOT NULL
);

DROP TABLE IF EXISTS public.basket CASCADE;
CREATE TABLE public.basket
(
    id          serial  NOT NULL PRIMARY KEY,
    userId     integer NOT NULL,
    payment boolean default false
);

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplierId) REFERENCES public.supplier (id) ON
DELETE
CASCADE;

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_category_id FOREIGN KEY (categoryId) REFERENCES public.category (id) ON
DELETE
CASCADE;

ALTER TABLE ONLY public.basket
    ADD CONSTRAINT fk_basket_id FOREIGN KEY (userId) REFERENCES public.shopUser (id) ON
DELETE
CASCADE;

ALTER TABLE ONLY public.productForBasket
    ADD CONSTRAINT fk_product_for_basket_id FOREIGN KEY (basketId) REFERENCES public.basket (id) ON
DELETE
CASCADE;

ALTER TABLE ONLY public.productForBasket
    ADD CONSTRAINT fk_product_name FOREIGN KEY (productId) REFERENCES public.product (id) ON
DELETE
CASCADE;
