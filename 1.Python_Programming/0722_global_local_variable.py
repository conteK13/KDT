a = 10
def func1():
    a = 20
    print(a)    #result : 20

def func2():
    print(a)    #result : 10

func1()
func2()


a = 10
def func1():
    global a    #전역변수 선언
    a = 20
    print(a)    #result : 20

def func2():
    print(a)    #result : 20

func1()
func2()
