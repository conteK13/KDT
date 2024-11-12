count =0
def fibonacci(n):
    global count
    """if n == 1:
        return 1
    elif n ==2 :
        return 1"""
    count+=1
    if n == 1 or n == 2:
        return 1
    
    else:
        return fibonacci(n-1) + fibonacci(n-2)



"""print("fibonacci(1):", fibonacci(1))
print("fibonacci(2):", fibonacci(2))
print("fibonacci(3):", fibonacci(3))
print("fibonacci(4):", fibonacci(4))
print("fibonacci(5):", fibonacci(5))"""
print("fibonacci(20):", fibonacci(20))
#print("fibonacci(40):", fibonacci(40))
#print("fibonacci(80):", fibonacci(80))

print(count)