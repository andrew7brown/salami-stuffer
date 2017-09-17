from flask import Flask

import pics
import json

app = Flask(__name__)


@app.route('/')
def hello_world():
    return 'Hello World!'

@app.route('/test')
def test():
    scores = pics.run_inference_on_image("c:/Users/andre/Documents/tmp/susandennis.n-1505658311.jpg")
    return json.dumps(str(scores))



if __name__ == '__main__':
    app.run()
