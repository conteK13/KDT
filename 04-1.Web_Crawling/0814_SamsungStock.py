import requests as req
from bs4 import BeautifulSoup as bs

codes= ['122870', '005930', '128940','035720','003490']
#codes의 현재 주가를 화면에 출력하기
#와이지엔터테이먼트, 삼성전자, 한미약품, 카카오, 대한 항공
for cd in codes:
    res = req.get(f"https://finance.naver.com/item/sise.naver?code={cd}")#삼성전자 주식 시세.
    html = res.text
    soup = bs(html, "lxml")

    now = soup.select_one("#_nowVal").text #현재가 id.
    title = soup.select_one("#middle h2 > a").text
    
        
    print("=====================================")
    print(f"{title}의 현재가 : {now}")
    
print("=====================================")

#<strong class="tah p11 under_line" id="_nowVal">20,750</strong>.
#태그 : strong.
#클래스명 : tah, p11, under_line (css tah, p11, under_line을 동시에 strong에 적용).
#id : "_nowVal".

#strong 태그에서 텍스트를 가져오기 위한 접근법.
#id 사용하기 (#_nowVal).
#class 사용하기(.tah.p11.under_line) -> 여러개 값을 가져올때는 class로 호출하는게 더 나을 수있다.
#상위태그1> 상위태그2> strong .