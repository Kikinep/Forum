create table posts(
    id bigint not null auto_increment,
    title varchar(100) not null unique,
    message varchar(500) not null unique,
    date datetime not null,
    status varchar(10) not null,
    user_id bigint not null,
    course_id bigint not null,

    primary key(id),

    constraint fk_posts_user_id foreign key(user_id) references users(id),
    constraint fk_posts_course_id foreign key(course_id) references courses(id)
);