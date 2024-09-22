import requests as req


#F12 개발자 화면 > network > doc > index.htm >User-Agent
head = {"User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36"}
#멜론 사이트는 클라이언트를 비정상적인 로봇의 접속으로 보고 응답을 하지 않음
res = req.get("https://www.melon.com/chart/index.htm",headers = head)

print(f"상태코드 : {res.status_code}") #response 406 : 클라이언트를 로봇으로 보고 비정상적인 요청으로 간주
print(f"헤더정보 : {res.headers}")
print(f"소스코드 : {res.text}")