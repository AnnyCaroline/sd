# execute primeiro 
# export GOOGLE_APPLICATION_CREDENTIALS="/home/anny/dev/key.json"








from google.cloud import vision

client = vision.ImageAnnotatorClient()

response = client.label_detection({
   'source': {'image_uri': 'https://boygeniusreport.files.wordpress.com/2016/11/puppy-dog.jpg?quality=98&strip=all'},
})

# print(response)

# print(len(response.label_annotations));

for label in response.label_annotations:
    print(label.description)