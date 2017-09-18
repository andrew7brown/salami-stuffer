import sqlite3


def createTable():
    conn = sqlite3.connect('test.db')
    conn.execute("DROP TABLE IF EXISTS CAMERAS;")
    conn.execute("DROP TABLE IF EXISTS SAVEDIMAGES;")

    conn.execute("PRAGMA FOREIGN_KEYS = ON")

    conn.execute('''CREATE TABLE IF NOT EXISTS CAMERAS
             ( 
             CAMERA_URL            TEXT    PRIMARY KEY   NOT NULL,
             NAME           TEXT    NOT NULL,
             LATITUDE       REAL    NOT NULL,
             LONGITUDE      REAL    NOT NULL );''')

    conn.execute('''CREATE TABLE IF NOT EXISTS SAVEDIMAGES
             ( SAVEDIMAGE_ID                  INTEGER    PRIMARY KEY   AUTOINCREMENT,
             CAMERA_URL             TEXT    NOT NULL, 
             FILEPATH              TEXT       NOT NULL,
             CLASSIFICATIONS       TEXT       NOT NULL,
             TIMESTAMP             TEXT       NOT NULL,
             FOREIGN KEY(CAMERA_URL) REFERENCES CAMERAS(CAMERA_URL)  );''')

    conn.commit()

    conn.close()

def save_scores(url, file, scores, time):
    conn = sqlite3.connect('test.db')
    conn.execute("INSERT INTO SAVEDIMAGES (CAMERA_URL, FILEPATH, CLASSIFICATIONS) VALUES (?, ?, ?)", (url, file, str(scores)))
    conn.commit()
    conn.close()

createTable()

