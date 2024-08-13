import requests as req

res = req.get("https://www.naver.com")

print(f"상태코드 : {res.status_code}") #response 200 : 정상적인 접속 허용 및 응답
print(f"헤더정보 : {res.headers}")
print(f"소스코드 : {res.text}")
