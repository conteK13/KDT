from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from webdriver_manager.chrome import ChromeDriverManager
from bs4 import BeautifulSoup as bs
import time

t = 5

serv = Service(executable_path=ChromeDriverManager().install())
driver = webdriver.Chrome(service=serv)
url = "https://www.coffeebeankorea.com/store/store.asp"

driver.get(url)
time.sleep(t) 
print("커피빈", driver.window_handles)

main_window = driver.current_window_handle

driver.execute_script("window.open('https://www.google.com/')")
time.sleep(t)
print("구글 추가", driver.window_handles)

try:
    for handle in driver.window_handles:
        #handle이 main이 아니면
        if handle != main_window:
            # handle 변경
            driver.switch_to.window(handle)
            #현재 handle 출력
            print(driver.current_window_handle)
            time.sleep(t)
            break
    #현재 handle을 닫는다.
    driver.close()
    
    #main_window로 handle을 변경
    driver.switch_to.window(main_window)
    print(driver.current_window_handle)
    time.sleep(5)
    driver.quit()
except:
    pass

