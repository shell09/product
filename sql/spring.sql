DROP TABLE PRODUCT;

CREATE TABLE PRODUCT (
  product_id           NUMBER,
  product_name       VARCHAR2(50),
  product_price       Number(10),
  product_fig          NUMBER(10)
);

--기본키생성
alter table PRODUCT add Constraint product_product_id_pk primary key (product_id);

--시퀀스
create sequence product_product_id_seq
start with 1
increment by 1
minvalue 0
maxvalue 99999999
nocycle;

--자료생성
insert into PRODUCT (product_id,product_name,product_price,product_fig)
values (PRODUCT_PRODUCT_ID_SEQ.nextval,'상품2','30000','20');

commit;
rollback;

SELECT * FROM PRODUCT;

-- 삭제
delete from product where product_id=1;

commit;