CREATE TABLE PAGING_BOARD (
  NO          NUMBER(6)          primary key, 
  TITLE       VARCHAR2(200)      NOT NULL, 
  WRITER      VARCHAR2(30)       NOT NULL, 
  CONTENT     VARCHAR2(4000)     NOT NULL, 
  REG_DATE    DATE               DEFAULT sysdate
);

CREATE SEQUENCE SEQ_PAGING_BOARD_NO; 

INSERT INTO PAGING_BOARD (
	NO, title, 
	writer, content
) values (
    SEQ_PAGING_BOARD_NO.NEXTVAL, 'title' || SEQ_PAGING_BOARD_NO.CURRVAL, 
    'writer' || SEQ_PAGING_BOARD_NO.CURRVAL, 'content' || SEQ_PAGING_BOARD_NO.CURRVAL
);

insert into paging_board(no, title, writer, content)
select SEQ_PAGING_BOARD_NO.NEXTVAL, 'title' || SEQ_PAGING_BOARD_NO.CURRVAL, 
    'writer' || SEQ_PAGING_BOARD_NO.CURRVAL, 'content' || SEQ_PAGING_BOARD_NO.CURRVAL
  from paging_board;   
    

