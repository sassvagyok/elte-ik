from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By

terms = ["facebook", "iphone", "apple", "ai", "google", "meta", "microsoft", "mi", "nvidia"]
d = {term: 0 for term in terms}

service = Service("chromedriver.exe")

options = Options()
options.add_argument('--headless')

driver = webdriver.Chrome(service=service, options=options)
driver.get("https://www.hirkereso.hu/tech/")

links = driver.find_elements(By.TAG_NAME, "a")

for link in links:
    text = link.text.lower()

    for term in terms:
        if term in text:
            d[term] += 1

driver.quit()

for term, value in d.items():
    print(f"{term}: {value}")