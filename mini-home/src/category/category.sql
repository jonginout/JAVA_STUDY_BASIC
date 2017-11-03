
-- category 테이블 생성
create table category(
    category_no number(30) primary key,
    category_group_no number(30) not null,
    homepage_no number(30) not null,
    name varchar2(30) not null
);
create sequence category_no_sq;



insert into category(category_no, category_group_no, homepage_no, name)
values (1, 1, 1, '기본 사진 갤러리');

insert into category(category_no, category_group_no, homepage_no, name)
values (2, 2, 1, '기본 방명록');

insert into category(category_no, category_group_no, homepage_no, name)
values (3, 3, 1, '기본 다이어리');

insert into category(category_no, category_group_no, homepage_no, name)
values (4, 4, 1, '기본 파일 갤러리');
