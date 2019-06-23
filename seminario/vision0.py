# execute primeiro 
# export GOOGLE_APPLICATION_CREDENTIALS="[PATH]"

from google.cloud import vision

client = vision.ImageAnnotatorClient()

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