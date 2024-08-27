alter table 멜론차트hot100 rename mcthot100;
alter table 멜론차트100 rename mct100;

-- 주석문
/*주석문 2*/


-- 커서가 있는 한줄 실행 : ctrl +enter
-- 여러줄 실행 : 여러줄 드래그 후 ctrl + shift + enter
-- 전체 소스 실행 : ctrl + shift + enter

use 멜론차트;                              /*멜론차트를 더블클릭한 것과 같은 효과*/
SELECT * FROM mcthot100;              /*멜론 차트 hot100의 모든 내용*/
SELECT 순위, 가수, 좋아요 from mcthot100; /*순위 가수 좋아요만 가져오기*/
/*delete from 멜론차트hot100 where 순위 >= 1; */ /*안전모드를 해제하지 않고 제거하기 위해 where절 추가*/

SELECT 순위, 가수, 좋아요
from mcthot100
where 좋아요 >= 100000;


-- 좋아요가 7000이상 100000사이인 곡의 가수, 제목, 좋아요 출력 
SELECT 가수, 제목, 좋아요
from mcthot100
where 좋아요 between 7000 and 10000;

SELECT distinct 가수
from mcthot100
where 좋아요 >= 7000 and 좋아요 <=10000;

-- 좋아요가 10만 이상인 가수 중 이름에 'New'가 들어가는 가수, 곡명, 좋아요 출력
SELECT 가수, 제목, 좋아요
from mcthot100
where (좋아요 >= 100000) and (가수 like '%New%');

-- 가수명이 NewJeans, aespa, NMIXX 중 하나인 순위, 제목, 가수, 좋아요
SELECT 순위, 제목, 가수, 좋아요
from mcthot100
where (가수 = 'newJeans') or (가수 = 'aespa') or (가수 = 'NMIXX');

SELECT 순위, 제목, 가수, 좋아요
from mcthot100
where 가수 in ('NewJeans', 'aespa', 'NMIXX');
