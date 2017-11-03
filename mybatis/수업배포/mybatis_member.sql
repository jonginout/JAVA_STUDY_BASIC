drop table mybatis_member;

create table mybatis_member(
    id      varchar2(20),
    name    varchar2(20),
    addr    varchar2(100),
    email   varchar2(20),
    join_date date default sysdate
);

insert into mybatis_member values('member-1', 'sbc',    'seoul',    'sbc@yahoo.co.kr', sysdate);
insert into mybatis_member values('member-2', 'spring', 'busan',    'abc@yahoo.co.kr', sysdate);
insert into mybatis_member values('member-3', 'jsp',    'daejeon',  'ddd@yahoo.co.kr', sysdate);
insert into mybatis_member values('member-4', 'spring', 'gyeonggi', 'eee@yahoo.co.kr', sysdate);

commit;

select * from mybatis_member;