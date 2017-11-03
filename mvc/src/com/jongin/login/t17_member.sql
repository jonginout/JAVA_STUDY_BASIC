create table t17_member (
	id	varchar2(20) primary key,
	name varchar2(30) not null,
	pass varchar2(20) not null
);


insert into T17_MEMBER values('admin', '관리자', '1111');