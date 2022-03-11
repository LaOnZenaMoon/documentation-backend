create table users
(
    user_id            사용자 ID generated by default as identity,
    created_date       생성일시,
    last_modified_date 수정일시,
    created_by         생성자,
    last_modified_by   수정자,
    used               사용 여부,
    email              이메일  not null,
    password           비밀번호 not null,
    name               이름   not null,
    primary key (user_id)
)