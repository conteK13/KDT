def test1(a, b):
    return a+b  

print(test1(1,2))       #result = 3

def test2(a, b):        #return이 없는 함수
    print(a+b)
    
    
result1 = test1(1,2)    #result = 3
result2 = test2(1,2)    
print(result1)          #result : 3
print(result2)          #result : None 
