"""#### [Lab] 섭씨 온도를 화씨 온도로 변환

* 섭씨 온도를 화씨 온도로 변환하는 공식: $F = (C * 1.8) + 32$
"""


def c_to_f(c):
    f = c *1.8 +32
    return f

print(c_to_f(0))
print(c_to_f(20))
