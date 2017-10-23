import requests
import json
import ImageClassifier
from PIL import Image
import urllib
import io
import time
import uuid

SAVE_DIR = '../server/images/'

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
        try:
            image = get_image(item['url'])
        except:
            print("Error getting and saving image.")
            continue
        try:
            tags = ImageClassifier.classifyImage(image=image, score_count=5)
        except:
            print("Gross error... when classifying image. Image probably wasn't fully gotten.")
            continue

        # Setup the json to send
        photos = {}
        photos['results'] = tags
        photos['uuid'] = str(uuid.uuid4())
        photos['saveUri'] = image
        photos_json = json.dumps(photos)
        j = json.loads(photos_json)

        # Send it
        photo_response = requests.post('http://localhost:8080/photoCaptures', json=j)
        photo_response_json = json.loads(photo_response.text)
        photo_href = photo_response_json['_links']['self']['href']

        # Send the associated camera
        camera_response = requests.post('http://localhost:8080/cameras', json=item)
        camera_response_json = json.loads(camera_response.text)
        camera_href = camera_response_json['_links']['self']['href']

        # Link em
        put_response = requests.put(photo_href + '/camera', headers = {'Content-Type':'text/uri-list'}, data=camera_href)

        print(put_response.status_code)
