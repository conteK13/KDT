# 결과를 저장할 파일 이름을 지정합니다.
output_file_name = "info_result2.txt"
input_file_name = "info.txt"

# 결과를 저장할 파일을 쓰기 모드로 열기
with open(output_file_name, "w", encoding='utf-8') as output_file:
    # 입력 파일을 읽기 모드로 열기
    with open(input_file_name, "r", encoding='utf-8') as file:
        for line in file:
            # 변수를 선언합니다.
            (name, weight, height) = line.strip().split(", ")

            # 데이터가 문제없는지 확인합니다: 문제가 있으면 지나감
            if (not name) or (not weight) or (not height):
                continue

            # 결과를 계산합니다.
            bmi = int(weight) / ((int(height) / 100) ** 2)
            bmi= round(bmi, 1)      #소수점 자리수를 1만 남기기 위함(지금은 가독성 때문에 추가함)
            result = ""
            if 25 <= bmi:
                result = "과체중"
            elif 18.5 <= bmi:
                result = "정상 체중"
            else:
                result = "저체중"

            # 결과를 출력합니다.
            output = '\n'.join([
                "이름: {}",
                "몸무게: {}",
                "키: {}",
                "BMI: {}",
                "결과: {}"
            ]).format(name, weight, height, bmi, result)

            print(output)
            print()

            # 결과를 파일에 저장합니다.
            output_file.write(output + "\n\n")  # 각 결과 뒤에 빈 줄 추가

print(f"결과가 '{output_file_name}' 파일에 저장되었습니다.")
