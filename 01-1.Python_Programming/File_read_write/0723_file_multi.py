"""### [Lab] 구구단 결과를 파일에 쓰기

* 구구단 결과를 파일에 쓰기
"""

with open("file_multi.txt", "w") as file:
    for i in range(2,10):
        file.write(f"<{i}단 구구단>\n")
        
        for j in range(1, 10):
            file.write(f"{i} x {j} = {i * j}\n")
            
        file.write("\n")
