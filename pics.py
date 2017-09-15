import io
import time
import urllib.request
from PIL import Image
from time import sleep


def get_open_and_save_image(url):
    print('running')
    file = urllib.request.urlopen(url).read()
    f = io.BytesIO(file)
    img = Image.open(f)
    img.show()
#    savepath = ('//' + url[7:20] + '-' + str(time.time())[:10] + '.jpg')
#    img.save(savepath, 'JPEG')


url = 'http://119.246.24.60:50000/SnapshotJPEG?Resolution=640x480&amp;amp;Quality=Clarity&amp;amp;'

while True:
    get_open_and_save_image(url)
    sleep(5)
