# 결과 파일을 저장
output_file_name = "info_result.txt"
input_file_name = "info.txt"

with open(input_file_name, "r", encoding="UTF-8") as infile, open(output_file_name, "w", encoding="UTF-8") as outfile:
    for line in infile:
        (name, weight, height) = line.strip().split(", ")
        
        if (not name) or (not weight) or (not height):
            continue
        
        bmi = int(weight) / ((int(height) / 100) ** 2)
        bmi= round(bmi, 1)      #소수점 자리수를 1만 남기기 위함(지금은 가독성 때문에 추가함)
        result = ""
        
        if 25 <= bmi:
            result = "과체중"
        elif 18.5 <= bmi:
            result = "정상 체중"
        else:
            result = "저체중"
        
        outfile.write(f"이름: {name}\n몸무게: {weight}\n키: {height}\nBMI: {bmi}\n결과: {result}\n\n")
        
        
print(f"결과가 '{output_file_name}'에 저장됨")
