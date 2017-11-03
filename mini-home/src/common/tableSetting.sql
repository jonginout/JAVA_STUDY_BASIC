-- member 테이블 생성
create table member(
    member_no number(30) primary key,
    id varchar2(30) not null,
    pw varchar2(50) not null,
    name varchar2(30) not null,
    birth varchar2(10) not null,
    reg_date date default sysdate
);

-- member_no 시퀀스 생성
create sequence member_no_sq;

-- category_group 테이블 생성
create table category_group(
    category_group_no number(30) primary key,
    name varchar2(30) not null
);

-- category_group_no 시퀀스 생성
create sequence category_group_no_sq;

-- category 테이블 생성
create table category(
    category_no number(30) primary key,
    category_group_no number(30) not null,
    name varchar2(30) not null
);

-- category_no 시퀀스 생성
create sequence category_no_sq;

-- hompage 테이블 생성
create table hompage(
    hompage_no number(30) primary key,
    id varchar2(30) not null, 
    bgm varchar2(50) null,
    profile varchar2(50) null,
    background_img varchar2(50) null,
    introduce varchar2(1000) default '소개글을 입력하세요.',
    title varchar2(200) not null,
    visits number(30) default 0,
    photo_gallary_use_yn char(1) default 'Y', 	
    guest_book_use_yn char(1) default 'Y',
    diary_use_yn char(1) default 'Y',
    file_gallary_use_yn char(1) default 'Y'
);

-- hompage_no 시퀀스 생성
create sequence hompage_no_sq;


-- board 테이블 생성
create table board(
    no number(30) primary key,
    hompage_no number(30) not null,
    category_no number(30) not null,
    title varchar2(200) not null,
    writer varchar2(30) not null,
    content varchar2(4000) not null,
    reg_date date default sysdate,
    update_date date default sysdate,
    secret char(1) default 'F'
);

-- no 시퀀스 생성
create sequence no_sq;

-- board_comment 테이블 생성
create table board_comment(
    comment_no number(30) primary key,
    no number(30) not null,
    writer varchar(30) not null,
    content varchar(4000) not null,
    reg_date date default sysdate,
    update_date date default sysdate
);

-- comment_no 시퀀스 생성
create sequence comment_no_sq;

-- board_file 테이블 생성
create table board_file(
    file_no number(30) primary key,
    no number(30) not null,
    file_path varchar(30) not null,
    ori_name varchar(200) not null,
    system_name varchar(200) not null,
    file_size number(30) not null
);

-- file_no 시퀀스 생성
create sequence file_no_sq;


-- friend 테이블 생성
create table friend(
    member_no number(30) not null,
    friend_member_no number(30) not null,
    status varchar2(10) default 'wait'
);