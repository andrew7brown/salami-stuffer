from flask import Flask

import ImageClassifier
import json

app = Flask(__name__)


@app.route('/')
def hello_world():
    return 'Hello World!'

@app.route('/test')
def test():
    scores = ImageClassifier.classifyImage("/Users/andrew7brown/tmp/susandennis.n-1505773338.jpg",5)
    return scores

if __name__ == '__main__':
    app.run()
