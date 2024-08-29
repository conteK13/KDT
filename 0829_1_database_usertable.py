import pymysql

# pip install pymysql

# 1.MySQL DB에 연결 - connect() 함수 사용
# 2.커서(cursor) 생성 - cursor()함수 사용
# 3.생성된 커서를 이용해서 sql 명령어를 실행한다 - execute()
# 4.입력한 데이터를 저장한다 - commit() 사용
# 5.MySQL 연결해제 - close() 함수 사용 

#==================================================

# 1.MySQL DB에 연결 - connect() 함수 사용
conn = pymysql.connect(host='localhost', port =3306, user = 'root', password = 'mysql904#', db = 'solodb', charset = 'utf8')
#utf-8로 쓰면 AttributeError: 'NoneType' object has no attribute 'encoding' 에러 발생
print("DB가 연결되었습니다.")

# 2.커서(cursor) 생성 - cursor()함수 사용
cur = conn.cursor()

# 3.생성된 커서를 이용해서 sql 명령어를 실행한다 - execute()
# 테이블 생성하기
# cur.execute("use solodb") #conn에서 설정했기 때문에, use 설정을 하지 않아도 됨

cur.execute("create table usertable(id varchar(20), username varchar(20) not null, email varchar(20), birthyear int, primary key(id))")

#데이터 입력하기
cur.execute("insert into usertable values('hong', '홍지윤', 'hong@naver.com', 1996)")
cur.execute("insert into usertable values('kim', '김태연', 'kim@daum.net', 2011)")
cur.execute("insert into usertable values('star', '별사랑', 'star@paran.com', 1990)")
cur.execute("insert into usertable values('yang', '양지은', 'yang@google.com', 1993)")

# 4.입력한 데이터를 저장한다 - commit() 사용
# DDL은 commit 안해도 됨
# insert, delete, update는 commit 필요
conn.commit()

# 5.MySQL 연결해제 - close() 함수 사용 
conn.close()
print("DB가 연결 해제되었습니다.")



