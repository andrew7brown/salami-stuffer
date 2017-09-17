import sqlite3


def createTable():
    conn = sqlite3.connect('test.db')
    print("Opened database successfully")
    # conn.execute('''CREATE TABLE CAMERAS
    #          (URL TEXT PRIMARY KEY     NOT NULL,
    #          NAME           TEXT    NOT NULL,
    #          LOCATION       TEXT     NOT NULL);''')
    # print("Table created successfully")

    conn.execute("INSERT INTO CAMERAS (URL,NAME,LOCATION) VALUES ('http://susandennis.noip.me/jpg/image.jpg', 'Susans Cam', 'California')")
    conn.commit()
    conn.close()

createTable()

