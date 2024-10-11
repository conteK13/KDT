use kosmo;

-- 질의 3-21 고객과 고객의 주문에 관한 데이터를 모두 보이시오
select *
from customer, orders
where customer.custid = orders.custid;

-- 질의 3-22 고객과 고객의 주문에 관한 데이터를 고객 번호 순으로 정렬하여 보이시오
select *
from customer, orders
where customer.custid = orders.custid
order by customer.custid;

-- 질의 3-23 고객의 이름과 고객이 주문한 도서의 판매가격을 검색하시오
select customer.name, orders.saleprice
from customer, orders
where customer.custid = orders.custid;


-- 질의 3-24 고객별로 주문한 모든 도서의 총 판매액을 구하고, 고객별로 정렬하시오
select name, sum(saleprice)
from customer, orders
where customer.custid = orders.custid
group by customer.name
order by customer.name;


-- 질의 3-25 고객의 이름과 고객이 주문한 도서의 이름
select customer.name, book.bookname
from customer, orders, book
where (customer.custid = orders.custid) and (book.bookid = orders.bookid);

-- 질의 3-26 가격이 20,000원인 도서를 주문한 고객의 이름과 도서의 이름을 구하시오
select customer.name, book.bookname
from customer, orders, book
where (customer.custid = orders.custid) and (book.bookid = orders.bookid) and (book.price = 20000);

-- 질의 3-27 도서를 구매하지 않은 고객을 포함하여 고객의 이름과 고객이 주문한 도서의 판매가격을 구하시오
select customer.name, orders.saleprice
from customer left outer join orders on customer.custid = orders.custid;

-- 질의 3-28 가장 비싼 도서의 이름을 보이시오 
select bookname
from book
where price = (select max(price) from book);

/*
Error Code: 1111. Invalid use of group function
max(price) 같은 그룹함수는 select문에만 사용할 수 있다.
select bookname
from book
where price = max(price);
*/
-- 질의 3- 29 도서를 구매한 적이 있는 고객의 이름을 구하시오 
select distinct customer.name
from customer, orders
where customer.custid = orders.custid;

select name
from customer
where custid in (select custid from orders);

-- 질의 3-30 대한 미디어에서 출판한 도서를 구매한 고객의 이름을 보이시오 
select customer.name
from customer, orders, book
where (customer.custid = orders.custid) and (book.bookid = orders.bookid)
and (book.publisher = '대한미디어');

select name
from customer
where custid 
in (select custid from orders where bookid 
in (select bookid from book where publisher = '대한미디어'));


-- 질의 3-31 출판사 별로 출판사의 평규 도서 가격보다 비싼 도서를 구하시오
select b1.bookname
from book b1
where b1.price > (select avg(b2.price) from book b2 where b2.publisher = b1.publisher);

-- where절의 b1과 b2의 위치에 대한 이야기
select b1.bookname
from book b1
where b1.price > (select avg(b2.price) from book b2 where b1.publisher = b2.publisher);
-- 기준을 b1으로 보고, b2의 값을 가져와야 한다.



select publisher, avg(price)
from book
group by publisher;


-- 질의 3-32 도서를 주문하지 않은 고객의 이름을 보이시오 
select distinct name
from customer, orders
where customer.custid not in (select orders.custid from orders);

select name
from customer
except
select name
from customer
where custid in (select custid from orders);

-- 질의 3-33 주문이 있는 고객의 이름과 주소를 보이시오 
select distinct customer.name, customer.address
from customer, orders
where (customer.custid = orders.custid);

select name, address
from customer cs
where exists (select * from orders od where cs.custid = od.custid);

-- 연습문제 1-5 박지성이 구매한 도서의 출판사의 수
select count(distinct publisher)
from customer, orders, book
where (customer.custid = orders.custid) and (book.bookid = orders.bookid)
and (customer.name = '박지성');

select count(publisher)
from book
where book.bookid in (select orders.bookid
						from orders
						where orders.custid = (select customer.custid
												from customer
												where name = '박지성'));



-- 연습문제 1-6 박지성이 구매한 도서의 이름 ,가격, 정가와 판매가격의 차이
select book.bookname, book.price, (book.price-orders.saleprice) as '정가와 판매가격의 차이'
from customer, orders, book
where (customer.custid = orders.custid) and (book.bookid = orders.bookid)
and (customer.name = '박지성');


-- 연습문제 1-7 박지성이 구매하지 않은 도서의 이름
select book.bookname
from customer, orders, book
where (customer.custid = orders.custid) and (book.bookid = orders.bookid)
and (customer.name ='박지성');


-- 연습문제 2-8 주문하지 않은 고객의 이름(부속질의 이용)
select name
from customer
where custid not in (select custid from orders);

-- group by를 사용하기 위한 코드
SELECT b1.bookname
FROM book b1
JOIN (
    SELECT publisher, AVG(price) AS avg_price
    FROM book
    GROUP BY publisher
) b2 ON b1.publisher = b2.publisher
WHERE b1.price > b2.avg_price;

-- 연습문제 2-9 주문 금액의 총액과 주문의 평균 금액
select sum(saleprice) as '주문총액', avg(saleprice) as '주문평균'
from orders;

-- 연습문제 2-10 고객의 이름과 고객별 구매액
select customer.name, sum(orders.saleprice)as 고객별구매액
from customer, orders
where customer.custid = orders.custid
group by customer.custid;

-- 연습문제 2-11 고객의 이름과 고객이 구매한 도서목록
select customer.name, book.bookname
from customer, orders, book
where customer.custid = orders.custid and book.bookid = orders.bookid;

-- 연슴문제 2-12 도서의 가격(book 테이블)과 판매가격(orders 테이블)의 차이가 가장 많은 주문
select orders.*, book.price, orders.saleprice
from book left outer join orders on book.bookid = orders.bookid
where (price - saleprice) = 
(select max(book.price -orders.saleprice)
from book, orders
where book.bookid = orders.bookid
);

select orders.*, book.price, orders.saleprice
from book, orders
where (price - saleprice) = 
(select max(book.price -orders.saleprice)
from book, orders
where book.bookid = orders.bookid
);


select max(book.price -orders.saleprice)
from book, orders
where book.bookid = orders.bookid;


-- 연습문제 2- 13 도서의 판매액 평균보다 자신의 무개액 평균이 더 높은 고객의 이름
select price
from customer, orders
where customer.custid = orders.custid
group by customer.custid;















-- orders 테이블에 bookname 속성 추가
-- book 테이블의 bookname을 oders 테이블의 bookname 필드에 가져오시오.
-- 단, 두개 테이블의 bookid가 일치하는 경우에 명령을 수행하도록 하시오.

alter table orders add bookname  varchar(45);
update orders
set bookname = (
	select bookname 
	from book 
    where book.bookid =orders.bookid);
    
select * from orders;