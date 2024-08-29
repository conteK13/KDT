import pymysql


# 1.MySQL DB에 연결 - connect() 함수 사용
conn = pymysql.connect(host='localhost', port =3306, user = 'root', password = 'mysql904#', db = 'solodb', charset = 'utf8')
#utf-8로 쓰면 AttributeError: 'NoneType' object has no attribute 'encoding' 에러 발생
print("DB가 연결되었습니다.")

# 2.커서(cursor) 생성 - cursor()함수 사용
cur = conn.cursor()
cur.execute("SELECT * FROM dbinfo")

# 3.데이터 접근
result = cur.fetchall()

"""
fetchall()   모든 데이터를 한 번에 가져올 때 사용
fetchone()   한 번 호출에 하나의 행만 가져올 때 사용
fetchmany(n)   n개만큼의 데이터를 가져올 때 사용
"""
print(result)

# 4.연결 종료
conn.close()
print("DB가 연결 해제되었습니다.")