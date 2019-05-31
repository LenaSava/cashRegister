create schema if not exists CashRegister collate utf8_general_ci;

create table if not exists bill
(
  id int auto_increment,
  totalCost int(10) default 0 null,
  dates timestamp(6) not null,
  status varchar(45) not null,
  user_id int not null,
  constraint id_UNIQUE
    unique (id)
);

create index fk_bill_invoice1_idx
  on bill (user_id);

alter table bill
  add primary key (id);

create table if not exists invoice
(
  id int auto_increment,
  product_id int not null,
  cost varchar(45) not null,
  quantity double not null,
  status varchar(45) null,
  user_id int not null,
  user_role_id int not null,
  bill_id int null,
  constraint id_UNIQUE
    unique (id)
);

create index bill_id
  on invoice (bill_id);

create index fk_invoice_user1_idx
  on invoice (user_id, user_role_id);

create index product_id
  on invoice (product_id);

alter table invoice
  add primary key (id);

create table if not exists products
(
  id int auto_increment,
  code int not null,
  name varchar(45) not null,
  name_ua varchar(45) not null,
  cost double not null,
  quantity int not null,
  invoice_id int null,
  constraint id_UNIQUE
    unique (id)
);

create index fk_products_invoice1_idx
  on products (invoice_id);

alter table products
  add primary key (id);

create table if not exists role
(
  id int auto_increment,
  roleType varchar(45) not null,
  constraint id_UNIQUE
    unique (id)
);

alter table role
  add primary key (id);

create table if not exists user
(
  id int auto_increment,
  login varchar(45) not null,
  email varchar(45) not null,
  password varchar(45) not null,
  role_id int not null,
  primary key (id, role_id),
  constraint email_UNIQUE
    unique (email),
  constraint id_UNIQUE
    unique (id),
  constraint fk_user_role1
    foreign key (role_id) references cashRegister.role (id)
);

create index fk_user_role1_idx
  on user (role_id);

