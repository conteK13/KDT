create database kosmo;
use kosmo;

/* P8의 구조로 테이블 Book, Orders, Customer 를 생성
primary key, foreign Key, 스키마 구조 정의
값을 입력하기*/

create table book (
bookid int primary key,
bookname varchar(45) not null,
publisher varchar(20)not null,
price int not null
);

insert into book value(1, '축구의 역사', '굿스포츠', 7000);
insert into book value(2, '축구 아는 여자', '나무수', 13000);
insert into book value(3, '축구의 이해', '대한미디어', 22000);
insert into book value(4, '골프 바이블', '대한미디어', 7000);
insert into book value(5, '피겨 교본', '굿스포츠', 7000);

insert into book value(6, '역도 단계별 기술', '굿스포츠', 7000);
insert into book value(7, '야구의 추억', '이상미디어', 7000);
insert into book value(8, '야구를 부탁해', '이상미디어', 7000);
insert into book value(9, '올림픽 이야기', '삼성당', 7000);
insert into book value(10, 'Olympic Champions', 'Pearson', 7000);

create table customer (
custid int primary key,
name varchar(10) not null,
address varchar(50) not null,
phone varchar(13)
);

insert into customer value(1, '박지성', '영국 맨체스터', '000-5000-0001');
insert into customer value(2, '김연아', '대한민국 서울', '000-6000-0001');
insert into customer value(3, '장미란', '대한민국 강원도', '000-7000-0001');
insert into customer value(4, '추신수', '미국 클리블랜드', '000-8000-0001');
insert into customer value(5, '박세리', '대한민국 대전','');

delete from customer where custid=5;

insert into customer(custid, name, address) value(5, '박세리', '대한민국 대전');

-- drop table orders;

create table orders(
orderid int primary key,
custid int not null,
bookid int not null,
saleprice int not null,
orderdate date not null,
foreign key(custid) references customer(custid) on delete cascade,
foreign key(bookid) references book(bookid) on delete cascade
);

insert into orders value(1, 1, 1, 6000, 20200701);
insert into orders value(2, 1, 3, 21000, 20200703);
insert into orders value(3, 2, 5, 8000, 20200703);
insert into orders value(4, 3, 6, 6000, 20200704);
insert into orders value(5, 4, 7, 20000, 20200705);

insert into orders value(6, 1, 2, 12000, 20200707);
insert into orders value(7, 4, 8, 13000, 20200707);
insert into orders value(8, 3, 10, 12000, 20200708);
insert into orders value(9, 2, 10, 7000, 20200709);
insert into orders value(10, 3, 8, 13000, 20200710);
