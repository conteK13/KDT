def solution(data, ext, val_ext, sort_by):
    row = ["code", "date", "maximum", "remain"]
    answer= []
    ext_index = row.index(ext)
    sort_index = row.index(sort_by)
       
    for i in range(len(data)):
        if data[i][ext_index] < val_ext:
            answer.append(data[i])
    
    sorted_answer = sorted(answer, key=lambda x: x[sort_index])
    return sorted_answer 

result = solution([[1, 20300104, 100, 80], [2, 20300804, 847, 37], [3, 20300401, 10, 8]],"date",20300501,"remain")
print(result)