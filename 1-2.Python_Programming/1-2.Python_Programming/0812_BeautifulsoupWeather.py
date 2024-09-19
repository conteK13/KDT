from urllib import request
from bs4 import BeautifulSoup

target = request.urlopen("http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=108")
# output=target.read()
# print(output)

#soup = BeautifulSoup(target, features="xml")
"""
문구를 해결하기 위해 features를 수정
XMLParsedAsHTMLWarning: It looks like you're parsing an XML document using an HTML parser. If this really is an HTML document (maybe it's XHTML?), you can ignore or filter this warning. If it's XML, you should know that using an XML parser will be more reliable. To parse this document as XML, make sure you have the lxml package installed, and pass the keyword argument `features="xml"` into the BeautifulSoup constructor.
  k = self.parse_starttag(i)
"""
soup = BeautifulSoup(target, "html.parser")

with open("weather0812.txt", "w", encoding= "UTF-8" ) as file:
    for location in soup.select("location"):
        city = location.select_one("city").string
        wf = location.select_one("wf").string
        tmn =location.select_one("tmn").string
        tmx = location.select_one("tmx").string
        
        
        file.write("도시 : {}\n날씨 : {}\n최저기온 : {}\n최고기온 : {}\n\n".format(city, wf, tmn, tmx))

        
        """ print("도시 :", location.select_one("city").string)
        print("날씨 :", location.select_one("wf").string)
        print("최저기온 :", location.select_one("tmn").string)
        print("최고기온 :", location.select_one("tmx").string)
        print()"""