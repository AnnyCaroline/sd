import io
import os

# Imports the Google Cloud client library
from google.cloud import vision
from google.cloud.vision import types

# Import for authentication
from google.oauth2 import service_account

# Instantiates a client
# client = vision.ImageAnnotatorClient()
creds = service_account.Credentials.from_service_account_file('./key.json')

client = vision.ImageAnnotatorClient(
    credentials=creds,
)

# The name of the image file to annotate
file_name = os.path.join(os.path.dirname(__file__), 'imgs/dog.jpg')

# Loads the image into memory
with io.open(file_name, 'rb') as image_file: content = image_file.read()

image = types.Image(content=content)

# Performs label detection on the image file
response = client.label_detection(image=image)
labels = response.label_annotations

print(response)

# print('Labels:')
# for label in labels:
#     print(label.description)