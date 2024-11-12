"""
print("1\t2\t3\t4")     #출력(tap)
print("1\n2\n3\n4")     #출력(enter)

print("1", "2", "3", sep = "    ") #출력(tap)
print("1", "2", "3", sep = "\n") #출력(tap)
"""

"""
#인치를 입력받아서 센치미터로 변환하기
#입력
#변환
#출력

num = int(input("인치를 입력하세요 : "))
result = num * 2.54

print(result)
"""
"""
#두 수를 입력받아, 더하는 함수 작성
def add_num(a, b):
    return a + b

print(add_num(1,2))
"""

"""
#입력값에 1200을 곱한 결과를 출력하세요
num = input("숫자를 입력하세요 : ")
try :
    won = int(num)
    result = won * 1200
    print(won,"에 1200을 곱하면 결과는 ", result, "입니다", sep="")

except ValueError:  #숫자형이 아닌, 문자형이 입력된 경우 등
    print("오류가 발생했습니다:")
"""

"""
#입력 함수에서 두 수를 입력받아 문장ㄹ 더하기와 정수형 더하기로 출력

a, b = map(int, input().split())

print("문자열 : ", str(a)+str(b))
print("정수형 : ", a + b)



a, b = input().split()

print("문자열 : ", a + b)
print("정수형 : ", int(a)+ int(b))
"""


"""#실수형 자료를 입력받아서 더하기, 빼기, 곱하기, 나누기 출력

a, b = map(float, input().split())

print("덧셈 :", int(a+b))
print("뺄셈 :", int(a-b))
print("곱셈 :", int(a*b))
print("나눗셈 :", round((a/b), 2))"""

#원의 둘레와 원주율 계산하기
#변수 선언과 할당

"""import math         #math 모둘 추가
r = 10 #반지름

print("원주율 =", math.pi)      #math 모듈의 pi사용 
print("반지름 =", r)
print("원의 둘레 =", math.pi* r*2)
print("원의 넓이 =", math.pi* r**2)
"""
"""
import datetime
week_day = ["월", "화", "수", "목", "금", "토", "일"]

now = datetime.datetime.now()
print(now.year, "년", sep = "")
print(now.month, "월", sep = "")
print(now.day, "일", sep = "")
print(now.hour, "시", sep = "")
print(now.minute, "분", sep = "")
print(now.second, "초", sep = "")
print(week_day[now.weekday()], "요일", sep = "")

day1 = datetime.date(2024, 7, 31)
day2 = datetime.date.today()

diff = day1-day2
print(diff)
print(diff.days)
"""
"""
import time

for i in range(1, 6):
    print(i)
    time.sleep(1)
"""
