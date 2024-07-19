#리스트 예제
"""
#replace
a = [1, 2, 3]
a[2] = 4
print(a)        #result: [1, 2, 4]
"""

"""
#문자열의 더하기
a= [1, 2, 3]
result = str(a[1]) + "안녕"     #int + str은 연산 불가능. str + str로 바꿔서 연산 가능
print(result)   #result: 2안녕
"""

"""
#del
a = [1, 2, 3, 4, 5]
print(a)        #result: [1, 2, 3, 4, 5]
del a[1]
print(a)        #result: [1, 3, 4, 5]
"""

"""
#append
a = [1, 2, 3]
a.append(4)
print(a)    #result: [1, 2, 3, 4]
"""

"""
#sort
a= [1, 4, 3, 2]
a.sort()
print(a)    #result: [1, 2, 3, 4]
"""

"""
#이차원 배열의 sorted 함수(key 사용)
#https://velog.io/@roope97/Python-파이썬-리스트-오름차순-내림차순-정렬sort-sorted-lambda
list = [[1,2], [3,1], [2,3]]
sorted_list = sorted(list, key= lambda x: x[1]) #2차원 배열의 1번째 값을 기준(x[1])으로 오름차순
print(sorted_list)  #result: [[3, 1], [1, 2], [2, 3]]

sorted_list = sorted(list, key= lambda x: -x[1]) #2차원 배열의 1번째 값을 기준(x[1])으로 내림차순(-)
print(sorted_list)  #result: [[2, 3], [1, 2], [3, 1]]
#key 옵션에 lambda 표현식 병행해서 사용할 경우 특정조건을 줄 수 있음

list = ["aa", "bbb", "c", "dddd"]
sorted_list = sorted(list, key = len, reverse= True)
print(sorted_list)
#reverse = False : 오름차순
#reverse = True : 내림차순
"""

"""
#reverse
a = ['a', 'c', 'b']
a.reverse()
print(a)    #result: ['b', 'c', 'a']
"""

"""
#index
a= [1, 2, 3]
print(a.index(3))   #result: 2
"""

"""
#insert
a= [1, 2, 3]
a.insert(2, 5)
print(a)        #result: [1, 2, 5, 3]
"""

"""
#remove
a= [1, 2, 3, 1, 2, 3]
a.remove(3)
print(a)        #result: [1, 2, 1, 2, 3]
"""

"""
#pop    맨 마지막 요소 return
a= [1, 2, 3]
b = a.pop()
print(a)
print(b)
"""