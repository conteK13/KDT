#입력 함수에서 단어(숫자, 글자로 이루어짐)를 입력받아 문자열 더하기와 정수형 더하기를 출력
#input 예시 : 514, 1원, 3달러, etc... (숫자와 글자가 혼합된 경우도 포함함)

string_a = input("입력A: ")
string_b = input("입력B: ")

#둘다 숫자로 입력받은 경우
if string_a.isdigit() and string_b.isdigit():   
    int_a = int(string_a)
    int_b = int(string_b)
    print("정수형 더하기:", int_a + int_b)

#둘다 문자로 입력 받은 경우
elif string_a.isalpha() and string_b.isalpha():
    print("문자열 더하기:", string_a + string_b)

#문자와 숫자가 섞여있는 경우
else:
    num1 = ""
    num2 = ""
    data1 = ""
    data2 = ""

    for i in string_a:
        if i.isdigit():
            num1+= i
            
        elif i.isalpha():
            data1 += i
            
    for i in string_b:
        if i.isdigit():
            num2+= i
            
        elif i.isalpha( ):
            data2 += i
            
    print(int(num1)+int(num2), end = "")
    if data1== data2:       #글자가 같은 경우에는 한번만 출력함
        print(data1)

    else:                   #글자가 다른 경우에는 문자열 더하기를 해서 출력함
        print(data1+data2)

