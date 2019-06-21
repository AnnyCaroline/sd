from google.cloud import vision
from google.oauth2 import service_account

creds = service_account.Credentials.from_service_account_file('./apresentacao.json')

client = vision.ImageAnnotatorClient(
    credentials=creds,
)

response = client.label_detection({
   'source': {'image_uri': 'https://boygeniusreport.files.wordpress.com/2016/11/puppy-dog.jpg?quality=98&strip=all'},
})

print(response)

# for label in response.label_annotations:
#     print(label.description)

# print(len(response.label_annotations));