SELECT * FROM 교보문고1.kb_table;

use 교보문고1;
select 책이름, 출판사 from kb_table
order by 책이름 desc;

-- kb_table에서 책이름, 출판사, 원가, 할인가를 검색하시오. 
-- 단, 할인가격이 낮은 것부터 출력하되, 낮은 가격인 경우 책이름에 대해 내림차순으로 정렬하세요.
select 책이름, 출판사, 원가, 할인가 from kb_table
order by 할인가 asc, 책이름 desc;


-- kb_table에서 책 이름에 '인간'이라는 글자가 포함된 것들에 대해
-- 책이름, 출판사, 원가, 할인가를 검색하시오. 
-- 단, 할인가격이 낮은 것부터 출력하되, 낮은 가격인 경우 책이름에 대해 내림차순으로 정렬하세요.
select 책이름, 출판사, 원가, 할인가 from kb_table
where 책이름 like '%인간%'
order by 할인가 asc, 책이름 desc;


-- 미래엔아이세움 출판사의 할인된 가격들의 합계 평균, 최고 비싼 가격, 가장 싼 가격

select 출판사,
sum(할인가) as 가격합계,
avg(할인가) as 가격평균,
max(할인가) as 최고가,
min(할인가) as 최저가
from kb_table
where 출판사 = '미래엔아이세움';


-- 할인된 가격들의 합계 평균, 최고 비싼 가격, 가장 싼 가격

select 출판사,
sum(할인가) as 가격합계,
avg(할인가) as 가격평균,
max(할인가) as 최고가,
min(할인가) as 최저가
from kb_table
group by 출판사
order by 출판사 ;

-- 출판사 별로 도서의 수량과 할인 가격의 합계를 검색하시오.
-- 단, 가격 필드명을 도서수량, 할인가합계로 저장
-- 도서수량에 따라 내림차순하여라
select 출판사, count(*) as 도서수량, sum(할인가) as 할인가합계
from kb_table
group by 출판사
order by 도서수량 desc;


-- 출판사 별로 도서의 수량과 할인 가격의 합계를 검색하시오.
-- 단, 가격 필드명을 도서수량, 할인가합계로 저장
-- 출판사의 출판도서 수가 5 이상인 것을 대상으로 하되 도서수량에 따라 내림차순하여라
select 출판사, count(*) as 도서수량, sum(할인가) as 할인가합계
from kb_table
where 출판사 like '%스%'
group by 출판사출판사
having count(*) >=5
order by 도서수량 desc;

alter table 출판사명 rename publisher;

-- 두개 이상의 테이블
select *
from kb_table, publisher;

-- 두개 이상의 테이블에서의 조인
select 번호, 책이름, 저자, kb_table.출판사, 할인가, 주소, 전화번호 
from kb_table, publisher
where kb_table.출판사 = publisher.출판사;
-- 소속을 정확히 밝힐 것
