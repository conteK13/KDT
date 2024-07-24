"""def solution(name, yearning, photo):    
    answer = []
    
    for p in photo:
        score = 0
        for i in p:
            for j in range(len(name)):
                if i == name[j]:
                    score += yearning[j]
        answer.append(score)
                    
    return answer"""

def solution(name, yearning, photo):    
    answer = []
    dct = {}
    for i in range(len(name)):
        dct[name[i]] = yearning[i]
        
    
    for p in photo:
        score = 0
        for i in p:
            if i in dct:
                score += dct[i]
        answer.append(score)
    return answer


name = ["may", "kein", "kain", "radi"]
yearning = [5, 10, 1, 3]
photo = [["may", "kein", "kain", "radi"],["may", "kein", "brin", "deny"], ["kon", "kain", "may", "coni"]]
result = solution(name, yearning, photo)
print(result)


name = ["kali", "mari", "don"]
yearning = [11, 1, 55]
photo = [["kali", "mari", "don"], ["pony", "tom", "teddy"], ["con", "mona", "don"]]
result = solution(name, yearning, photo)
print(result)

name = ["may", "kein", "kain", "radi"]
yearning = [5, 10, 1, 3]
photo = [["may"],["kein", "deny", "may"], ["kon", "coni"]]
result = solution(name, yearning, photo)
print(result)