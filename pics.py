import io
import os
import os.path
import re
import time
import urllib.request
import SqliteCommands
from PIL import Image

import ImageClassification

DATA_URL = 'http://download.tensorflow.org/models/image/imagenet/inception-2015-12-05.tgz'
MODEL_DIR = '/Users/andrew7brown/tmp/imagenet'
SAVE_DIR = '/Users/andrew7brown/tmp/'
SEARCH_TERM = 'Window'
NUM_TOP_PREDICTIONS = 20

def get_open_and_save_image(url):
    file = urllib.request.urlopen(url).read()
    f = io.BytesIO(file)
    img = Image.open(f)
    # cool but annoying
    # img.show()

    savepath = (SAVE_DIR + url[7:20] + '-' + str(time.time())[:10] + '.jpg')
    print(f"Saving: {savepath}")
    img.save(savepath, 'JPEG')

    f.close()
    img.close()
    return savepath


def main():
    url = 'http://susandennis.noip.me/jpg/image.jpg'
    found = False

    ImageClassification.maybe_download_and_extract()

    while True:
        print(f"Getting Image from {url}")
        image = get_open_and_save_image(url)
        print("Classifying image")
        scores = ImageClassification.run_inference_on_image(image)

        print("What was found and its score:")
        for item in scores:
            search_obj = re.search(SEARCH_TERM, item, re.M | re.I)
            if search_obj:
                print(f"Found {item} with a score of {scores[item]}")
                found = True
                break
            else:
                found = False

        if not found and os.path.exists(image):
            print(f"{SEARCH_TERM} not found. Removing {image}")
            os.remove(image)
        SqliteCommands.save_scores(1, image, scores)


if __name__ == '__main__':
    main()
