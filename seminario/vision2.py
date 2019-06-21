from google.cloud import vision
from google.oauth2 import service_account

creds = service_account.Credentials.from_service_account_file('./key.json')

client = vision.ImageAnnotatorClient(
    credentials=creds,
)

cachorro = 'https://boygeniusreport.files.wordpress.com/2016/11/puppy-dog.jpg?quality=98&strip=all';
cristo = 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/97/Cristo_Redentor_-_Rio_de_Janeiro%2C_Brasil-crop.jpg/290px-Cristo_Redentor_-_Rio_de_Janeiro%2C_Brasil-crop.jpg';
arma = 'https://tribunaonline.com.br/thumbs/body/2018-11/arma-de-fogo-2733b974497adf364effeefe67da7dc5.jpg';

response = client.annotate_image(
    {
        'image': {'source': {'image_uri': cristo}},
        'features': [
            {'type': vision.enums.Feature.Type.LANDMARK_DETECTION},
            {'type': vision.enums.Feature.Type.SAFE_SEARCH_DETECTION},
            {'type': vision.enums.Feature.Type.FACE_DETECTION}
        ],
    }
)

print(response)

# TYPE_UNSPECIFIED = 0
# FACE_DETECTION = 1
# LANDMARK_DETECTION = 2
# LOGO_DETECTION = 3
# LABEL_DETECTION = 4
# TEXT_DETECTION = 5
# DOCUMENT_TEXT_DETECTION = 11
# SAFE_SEARCH_DETECTION = 6
# IMAGE_PROPERTIES = 7
# CROP_HINTS = 9
# WEB_DETECTION = 10
# PRODUCT_SEARCH = 12
# OBJECT_LOCALIZATION = 19

# print(response.safe_search_annotation);

# for label in response.safe_search_annotation:
#     print(label.description)

# print(len(response.label_annotations));