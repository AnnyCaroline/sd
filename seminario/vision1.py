from google.cloud import vision
from google.oauth2 import service_account

creds = service_account.Credentials.from_service_account_file('[PATH]')

client = vision.ImageAnnotatorClient(credentials=creds)

request = {
   "image": {
      "source": {
         "image_uri": "http://encurtador.com.br/qKZ39"
      }
   },    
   "features": [
      {
         "max_results": 2,
         "type": "LABEL_DETECTION"
      },
      {
         "type": vision.enums.Feature.Type.SAFE_SEARCH_DETECTION
      }
   ]
}

response = client.annotate_image(request)

print(response)

print(response.safe_search_annotation.adult)

for label in response.label_annotations:
   print(label.description)