def factorial_self(n):
    if n == 0:
        return 1
    else:
        return n * factorial_self(n-1)
    
print("1! :", factorial_self(1))
print("2! :", factorial_self(2))
print("3! :", factorial_self(3))
print("4! :", factorial_self(4))
print("5! :", factorial_self(5))

print("12! :", factorial_self(12))