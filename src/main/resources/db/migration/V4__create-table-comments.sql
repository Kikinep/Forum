create table comments(
    id bigint not null auto_increment,
    message varchar(100) not null,
    post_id bigint not null,
    date datetime not null,
    user_id bigint not null,
    solution tinyint not null,

    primary key(id),

    constraint fk_comments_post_id foreign key(post_id) references posts(id),
    constraint fk_comments_user_id foreign key(user_id) references users(id)
);