def factorial_for(n):
    output=1
    for i in range(1, n+1):
        output *= i
    return output

print("1! :", factorial_for(1))
print("2! :", factorial_for(2))
print("3! :", factorial_for(3))
print("4! :", factorial_for(4))
print("5! :", factorial_for(5))

print("12! :", factorial_for(12))
