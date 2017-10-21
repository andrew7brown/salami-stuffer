import requests
import json

with open('cams_update.json') as data:
    json_data = json.load(data)

for item in json_data:
    if ".jpg" in item['url']:
        requests.post('http://localhost:8080/cameras', json=item)
        print(json.dumps(item))
