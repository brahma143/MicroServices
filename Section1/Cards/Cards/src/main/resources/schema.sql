create create if not exists 'cards'(
        `card_id` int not null Auto Increment,
        `mobile_number` varchar(15) not null,
        `card_number` varchar(100) not null,
        `card_type` varchar(100) not null,
        `total_limit` int not null,
        `amount_used` int not null,
        `available_amount` int not null,
        `created_at` data not null,
        `created_by` varchar2(20) not null,
        `updated_at` data default null,
        `updated_by` varchar2(20) default null,
        primary key(`card_id`)
);