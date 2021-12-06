create table user_subscriptions (
    subscriber_id int8 not null references user,
    book_id int8 not null references user,
    primary key (subscriber_id, book_id)
)