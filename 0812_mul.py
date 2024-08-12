def mul(*values):
    result =1
    for i in values:
        result *= i 
    return result

print(mul(5, 7, 9, 10))
print(mul(8, 3, 5, 7, 9, 10))
