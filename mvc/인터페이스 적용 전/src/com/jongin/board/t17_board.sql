create table t17_board (
    no  number(6) primary key,
    writer varchar2(30) not null,
    title varchar2(200) not null,
    content varchar2(4000) not null,
    reg_date date default sysdate
);

create sequence s17_board_no;

select s17_board_no.nextval 
  from dual; 
  
insert into t17_board(
    no, writer, title, content
) values ( 
    s17_board_no.nextval, 'tester', '제목', '내용'
);

select *
  from t17_board;

commit;  



