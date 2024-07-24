#4로 나누어떨어지는 해는 윤년으로 한다. (2016년, 2020년, 2024년 등등)
#4와 100으로 나누어떨어지는 해는 평년으로 한다. (1900년, 2100년, 2200년)
#4, 100, 400으로 나누어떨어지는 해는 윤년으로 한다. (2000년, 2400년)


input_year = int(input("윤년 여부를 확인할 연도를 입력하세요 :"))


#1
if input_year % 400==0:
    result = "윤년"

elif input_year % 100==0:
    result = "평년"

elif input_year % 4==0:
    result = "윤년"

else:
    result = "평년"
    
print(input_year, "은 ", result, "입니다", sep = "")


#2
if input_year%400 ==0 or (input_year%100 !=0 and input_year%4) :
    result = "윤년"
else:
    result = "평년"

print(input_year, "은 ", result, "입니다", sep = "")



def leap_year(year):
    if year % 400==0:
        result = "윤년"

    elif year % 100==0:
        result = "평년"

    elif year % 4==0:
        result = "윤년"

    else:
        result = "평년"
    
    return result

input_year = int(input())
print(leap_year(input_year))


print(leap_year(2020))
print(leap_year(2021))
print(leap_year(2022))
print(leap_year(2023))
print(leap_year(2024))
print(leap_year(2025))

for i in range(2020, 2026):
    print(i, ":", leap_year(i))
    