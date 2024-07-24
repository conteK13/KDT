def test(b, *a):
    result = sum(a) / b
    return result


print(test(2, 1,2,3,4))


def test(*a, b):            #가변변수는 맨 뒤에 호출해야 함. 가변변수가 무한히 받으므로 뒤의 매개변수 b가 값을 입력받지 못함.
    result = sum(a) / b
    return result


print(test(2, 1,2,3,4))
