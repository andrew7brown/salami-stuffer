import requests
import json
import ImageClassifier
from PIL import Image
import urllib
import io
import time
import uuid

SAVE_DIR = '../server/src/main/resources/images/'

def get_image(url):
    file = urllib.request.urlopen(url).read()
    f = io.BytesIO(file)
    img = Image.open(f)

    savepath = (SAVE_DIR + url[7:20] + '-' + str(time.time())[:10] + '.jpg')
    print(f"Saving: {savepath}")
    img.save(savepath, 'JPEG')

    f.close()
    img.close()
    return savepath

with open('cams_update.json') as data:
    json_data = json.load(data)

for item in json_data:
    if ".jpg" in item['url'] and "faststream" not in item['url']:
        print(f'Processing {item["url"]}')
        image = get_image(item['url'])
        tags = ImageClassifier.classifyImage(image=image, score_count=5)

        photos = {}
        photos['results'] = tags
        photos['uuid'] = str(uuid.uuid4())
        photos['saveUri'] = image
        photos_json = json.dumps(photos)
        j = json.loads(photos_json)

        print("POSTING: " + json.dumps(j, indent=4, sort_keys=True))

        response = requests.post('http://localhost:8080/photoCaptures', json=j)

        print("REPSONSE: " + response.text)
        # cam_dict = {}
        # cam_dict['latitude'] = item['latitude']
        # cam_dict['longitude'] = item['longitude']
        # cam_dict['url'] = item['url']
        # cam_dict['photos'] = []
        # cam_dict['photos'].append(dict())
        # cam_dict['photos'][0]['uuid'] = str(uuid.uuid4())
        # cam_dict['photos'][0]['saveUri'] = image
        # cam_dict['photos'][0]['results'] = json.loads(tags)


        # requests.post('http://localhost:8080/cameras', json=item)
