d = {1:1, 2:1}

count = 0
def fibonacci(n):
    global count
    count +=1
    if n in d:
        return d[n]
    else:
        output = fibonacci(n-1) + fibonacci(n-2)
        d[n] = output
        return output
    
# print("fibonacci(1):", fibonacci(1))
# print(count)
# count =0
# print("fibonacci(2):", fibonacci(2))
# print(count)
# count =0
# print("fibonacci(3):", fibonacci(3))
# print(count)
# count =0
# print("fibonacci(4):", fibonacci(4))
# print(count)
# count =0
# print("fibonacci(5):", fibonacci(5))
# print(count)
# count =0
# print("fibonacci(20):", fibonacci(20))
# print(count)
# count =0
# print("fibonacci(40):", fibonacci(40))
# print(count)
# count =0
print("fibonacci(80):", fibonacci(80))
print(count)
count =0
