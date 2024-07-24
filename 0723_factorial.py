def factorial(n):
    result = 1
    for i in range(1,n+1):
        result *= i
    return result

print(factorial(5))


def factorial_return(n):
    if n == 0:
        return 0
    else:
        return n * factorial(n-1)

print(factorial_return(5))
