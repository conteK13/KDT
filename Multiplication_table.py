"""#### [Lab] 구구단 중의 하나의 단을 입력받아 계산

* 출력할 단을 입력
* 해당 단의 계산 결과 출력
"""

num = int(input("출력할 단을 입력하세요 : "))
for i in range(1, 10):
    print(f"{num} x {i} = {num*i}")
    
    
"""#### [Lab] 구구단 출력

* `print()` 함수를 사용하여 각 단마다 횡으로 출력

"""

for i in range(2, 10):
    for j in range(2, 10):
        print(f"{i} x {j} = {i*j}", end='   ')
    print()