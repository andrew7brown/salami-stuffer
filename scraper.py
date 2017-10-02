import requests
import json
from bs4 import BeautifulSoup

def get_soup(page):
    headers = {'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36'}
    page = requests.get(page, headers=headers)
    return BeautifulSoup(page.text, 'html.parser')

def get_images(soup):
    img = soup.find_all('img')
    images = {}
    for item in img:
        try:
            images[item['title']] = item['src']
        except:
            print()
    return images
      
def get_details_pages(soup):
    thumbnails = BeautifulSoup(str(soup.find_all('div', class_='thumbnail-item')), 'html.parser')
    details_pages = []
    links = thumbnails.find_all('a')
    for link in links:
        details_pages.append(link.get('href'))
    return details_pages

def find_detail(soup, detail):
    details = soup.find_all('div', class_='camera-details__cell')
    for index, item in enumerate(details):
        if item.text.strip() == detail:
            return details[index + 1].text.strip()

def get_details(soup):
    details = {}
    img = soup.find('img')
    details['name'] = img['title'][42:]
    details['url'] = img['src']
    details['latitude'] = find_detail(soup, 'Latitude:')
    details['longitude'] = find_detail(soup, 'Longitude:')
    return details
    


url = 'https://www.insecam.org/en/byrating?page='
details_url = 'https://www.insecam.org'
stuff = []

details_pages = []
for x in range(1, 501):
    print(f"Scraping {url}{x}")
    soup = get_soup(url + str(x))
    details_pages += get_details_pages(soup)
    
for page in details_pages:
    print(f"Getting details for {details_url}{page}")
    details_soup = get_soup(details_url + page)
    stuff.append(get_details(details_soup))

f = open('cams.out', 'w')
f.write(json.dumps(stuff))
f.close()



#get_images(soup)