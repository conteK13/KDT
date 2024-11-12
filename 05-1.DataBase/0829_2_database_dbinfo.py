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

cur.execute("create table dbinfo(dbms varchar(20), production varchar(20), os varchar(30), etc varchar(50), primary key(dbms))")

#데이터 입력하기
cur.execute("insert into dbinfo values('MySQL', 'Oracle', 'Unix, Linux, Windows, Mac', '오픈 소스(무료), 상용')")
cur.execute("insert into dbinfo values('MariaDB', 'MariaDB', 'Unix, Linux, Windows', '오픈 소스(무료), MySQL 초기 개발자들이 독립해서 만듦')")
cur.execute("insert into dbinfo values('PostgreSQL', 'PostgreSQL', 'Unix, Linux, Windows, Mac', '오픈 소스(무료)')")
cur.execute("insert into dbinfo values('Oracle', 'Oracle', 'Unix, Linux, Windows', '상용 시장 점유율 1위')")
cur.execute("insert into dbinfo values('SQL Server', 'Microsoft', 'Windows', '주로 중/대형급 시장에서 사용')")
cur.execute("insert into dbinfo values('DB2', 'IBM', 'Unix, Linux, Windows', '메인프레임 시장 점유율 1위')")
cur.execute("insert into dbinfo values('Access', 'Microsoft', 'Windows', 'PC용')")
cur.execute("insert into dbinfo values('SQLite', 'SQLite', 'Android, iOS', '모바일 전용, 오픈 소스(무료)')")
#자주하는 실수 마지막의 괄호를 누락하는 경우 -> ')")


# 4.입력한 데이터를 저장한다 - commit() 사용
# DDL은 commit 안해도 됨
# insert, delete, update는 commit 필요
conn.commit()

# 5.MySQL 연결해제 - close() 함수 사용 
conn.close()
print("DB가 연결 해제되었습니다.")



