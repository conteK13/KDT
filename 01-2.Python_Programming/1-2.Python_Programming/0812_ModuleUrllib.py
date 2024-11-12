from urllib import request

target = request.urlopen("https://www.naver.com")
output=target.read()

print(output)