input_file_name = "info_result.txt"
new_output_file_name = "new_info_result.txt"


with open(input_file_name, "r", encoding='utf-8') as read_file:
    content = read_file.read()

with open(new_output_file_name, "w", encoding='utf-8') as new_output_file:
    new_output_file.write(content)

print(f"결과가 '{new_output_file_name}' 파일에 다시 저장되었습니다.")
