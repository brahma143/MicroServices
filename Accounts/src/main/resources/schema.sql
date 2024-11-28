
create table if not exists `customer`(
 `customer_id` int primary key auto_increment,
  `name` varchar(255) not null,
  `email` varchar(255) not null
  `mobile_number` varchar(255) not null,
  `created_at` date not null,
  `created_by` varchar not null,
  `updated_at` date default null,
  `updated_by` varchar default null
);

create table if not exists `accounts`(
 `account_id` int nit null,
 `account_number` int auto_increment primary key,
 `account_type` varchar(255) not null,
 `branch_address` varchar(255) not null,
  `created_at` date not null,
  `created_by` varchar not null,
  `updated_at` date default null,
  `updated_by` varchar default null
);