import pymysql

# pip install pymysql

# 1.MySQL DB에 연결 - connect() 함수 사용
# 2.커서(cursor) 생성 - cursor()함수 사용
# 3.생성된 커서를 이용해서 sql 명령어를 실행한다 - execute()
# 4.입력한 데이터를 저장한다 - commit() 사용
# 5.MySQL 연결해제 - close() 함수 사용 

#==================================================



# 1.MySQL DB에 연결 - connect() 함수 사용
conn = pymysql.connect(host='localhost', port =3306, user = 'root', password = 'mysql904#', db = 'kosmo', charset = 'utf8')
#utf-8로 쓰면 AttributeError: 'NoneType' object has no attribute 'encoding' 에러 발생

# 2.커서(cursor) 생성 - cursor()함수 사용
cur = conn.cursor()

# 3.생성된 커서를 이용해서 sql 명령어를 실행한다 - execute()
cur.execute("insert into customer values(6, '강경연', '대한민국 서울', '010-9655-9428')")
cur.execute("insert into customer values(7, '이순신', '대한민국 남해시', '062-9655-9428')")

# 4.입력한 데이터를 저장한다 - commit() 사용
conn.commit()

# 5.MySQL 연결해제 - close() 함수 사용 
conn.close()



