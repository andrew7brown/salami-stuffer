import json

with open('cams.json') as data:
    json_data = json.load(data)

for item in json_data:
    item['latitude'] = float(item['latitude'])
    item['longitude'] = float(item['longitude'])

new_file = open('cams_update.json', 'w')
new_file.write(json.dumps(json_data, indent=4, sort_keys=True))
new_file.close()
