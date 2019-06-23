# use local image

import io
import os

from google.cloud import vision
from google.oauth2 import service_account

creds = service_account.Credentials.from_service_account_file('./key.json')

client = vision.ImageAnnotatorClient(
    credentials=creds,
)

# The name of the image file to annotate
file_name = os.path.join(
    os.path.dirname(__file__),
    "./dog.jpg")

# Loads the image into memory
with io.open(file_name, 'rb') as image_file:
    content = image_file.read()

request = {
    "image": {
        "content": content
    },    
    "features": [
        {
            "max_results": 2,
            "type": "LABEL_DETECTION"
        },
        {
            "type": "SAFE_SEARCH_DETECTION"
        }
    ]
}

response = client.annotate_image(request)

print(response)

print(response.safe_search_annotation.adult)

for label in response.label_annotations:
   print(label.description)