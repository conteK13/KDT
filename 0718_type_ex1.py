#문자열 예제

"""
#문자열 인덱싱과 슬라이싱

a = 'Life is too short, You need Python'
print(a[3]) #result: e

print(a[0])     #result: L
print(a[12])    #result: s
print(a[-1])    #result: n

b = a[0] + a[1] + a[2] + a[3]
print(b)        #result: Life

print(a[0:4])   #result: Life
#[n:m] 은 n부터 m-1까지의 값을 슬라이싱 하므로, 0부터 3까지 가져오고 싶다면 3+1인 4로 설정해야 함
"""


"""
#슬라이싱으로 문자열 나누기
a = "20240718Rainy"
date = a[:8]
weather = a[8:]
print(date)     #result: 20240718
print(weather)  ##result: Rainy

year = a[:4]
day = a[4:8]
weather = a[8:]

print(year)     #result: 2024
print(day)      #result: 0718
print(weather)  #result: Rainy

print(len(a))   #result: 13
"""


"""
#문자열 포매팅
print("I eat %d apples" % 3)        #result: I eat 3 apples
print("I eat %s apples" % "five")   #result: I eat five apples
print("I ate %d apples. so I was sick for %s days." % (10, "three"))
#result: I ate 10 apples. so I was sick for three days.
"""

"""
print("나는 %s에 가고 싶다." % "집")    #result: 나는 집에 가고 싶다.
print("I want to go %s" % "home")       #result: I want to go home
"""

"""
#문자열 더해서 연결하기
head = "Python "
tail = "is fun"

result = head + tail
print(result)
"""

name = "Kang"
age = 28

print(f"제 이름은 {name}입니다. 나이는 {age}입니다.")