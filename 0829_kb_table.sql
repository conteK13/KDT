SELECT * FROM 교보문고1.publisher;

-- 나로 시작하는 출판사들에 대해 주소를 "서울시 서초구"라고 넣기
-- 다로 시작하는 출판사들의 주소를 "대전시 유성구"라고 넣기
use 교보문고1;

update publisher set 주소 = "경기도 안양시";

-- 트랜젝션(임의의 작업을 수행하기 위한 명령어들의 집합)을 만들어야 롤백 가능
-- rollback; 
-- commit;

update publisher set 주소 = "서울시 서초구" where 출판사 like "나%" or 출판사 like "%스%";
update publisher set 주소 = "대전시 유성구" where 출판사 like "다%" or 출판사 like "%이%" or 출판사 like "%대%";

alter table kb_table add 주소 varchar(45);

-- foreign key 설정x
update kb_table set 주소 = (select 주소
							from publisher
							where publisher.출판사 = kb_table.출판사);

SELECT * FROM 교보문고1.kb_table
order by 출판사;