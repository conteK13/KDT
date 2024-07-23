"""---

## 파일 입출력(File Input/Output)

### 파일 입출력 과정

* 파일의 입출력 과정은 단계를 가짐
* 파일을 열고, 파일을 읽거나 쓰고, 파일을 닫는 순서

### 파일 열기/닫기

* file.txt 파일을 생성하고, 열고 닫기
"""


file = open("file.txt", "w")
file.close()


"""### 파일 모드(File Mode)

### 텍스트 파일 쓰기

#### write()

* `write()`를 이용하여 파일에 쓰기
"""

file = open("file.txt", "w")
file.write("즐거운 이야기를 해주세요")
file.close()


"""#### writelines()

* `writelines()`를 이용하여 list를 파일에 쓰기
"""
file2 = open("file2.txt", "w")
data = ["One\n", "Two\n", "Three\n"]
file2.writelines(data)
file2.close()


"""#### 표준 입력 → 파일 쓰기

* 표준 입력을 `input()` 함수를 통해 입력 받고, `write()` 함수를 통해 파일에 쓰기
"""
input_data= input("성함을 입력하세요 : ")
file3 = open("file3.txt", "w")
file3.write(input_data)
file3.close()

#여러값 입력 

text = [str(text + '\n') for text in input("여러값 입력: ").split()]
f= open("file4.txt", "w")
f.writelines(text)
f.close()