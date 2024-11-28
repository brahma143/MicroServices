create table if not exist `loans`(
 `loan_id` int not null Auto Increment,
 `mobile_number` varchar(10) not null,
 `loan_number` varchar(10) not null,
 `loan_type` varchar(20) not null,
 `total_loan` int not null,
 `amount_paid` int not null,
 `outstanding_amount` int not null,
 `created_at` date not null,
 `created_by` varchar(20) not null,
 `updated_at` date not null,
 `updated_by` varchar default null
 primary key(loan_id)
);