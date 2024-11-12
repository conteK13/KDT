use 교보문고1;

-- book 이라는 table 만들기
create table book (
bookid int,
bookname varchar(45),
publisher varchar(20),
price int,
primary key (bookid)
);

-- drop table book;

-- book 이라는 table 만들 때 옵션 넣기
create table book (
bookid int not null,
bookname varchar(45) not null,
publisher varchar(20) UNIQUE,
price int default 10000 check(price > 1000),
primary key (bookid)
);

create table customer (
custid int not null primary key,
name varchar(20) not null,
address varchar(40),
phone varchar(30)
);

drop table orders;

create table orders (
orderid int not null primary key,
custid int not null,
bookid int not null,
saleprice int not null,
orderdata date,
foreign key (custid) references customer(custid) on delete cascade on update cascade,
foreign key (bookid) references book(bookid) on delete cascade
);

create table newbook(
bookid int,
bookname varchar(20),
publisher varchar(20),
price int
);

alter table newbook
add primary key (bookid);

-- isbn 컬럼 추가 varchar(13)
alter table newbook
add isbn varchar(13);

-- isbn 컬럼 타입을 int로 수정
alter table newbook
modify isbn int;

alter table newbook
drop column isbn;

-- new book 테이블 자체를 삭제

drop table newbook;

insert into book
values(1, '축구의 역사', '굿스포츠', 7000);

insert into book(price, bookid, bookname, publisher)
values(7000, 2, '축구의 역사', '스포츠');

-- book에 있는 모든 값 삭제
delete from book;

-- 조건에 만족하는 모든 값 삭제
delete from book
where bookid =2;


update book
set price = 10000
where bookname = '축구의 역사'; 