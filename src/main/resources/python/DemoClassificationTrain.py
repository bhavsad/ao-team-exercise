from CleaningText import *
import pickle
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize
from sklearn.multiclass import OneVsRestClassifier
import numpy as np
from sklearn.naive_bayes import MultinomialNB
from sklearn.metrics import accuracy_score

var_cols = ['WEATHER',
            'HIGH_WINDS', 'POOR_VISIBILITY', 'VEHICLE', 'FALL', 'LOW_VISIBILITY',
            'THUNDERSTORM', 'NEGLIGENCE', 'POOR_JUDGMENT', 'TRENCHING',
            'ELECTRICAL', 'RAIN', 'HAZARDOUS_CHEMICAL', 'SNOW', 'CHEMICAL', 'FOG']

stopWords = set(stopwords.words('english'))


class DemoClassification(object):
    def __init__(self, clientserver=None):
        self.clientserver = clientserver

    def executeScript(self, url):
        import pandas as pd
        import json, sys, yaml, re
        url = json.loads(url)
        url = json.loads(url)
        path = url['input_dir']
        incidents_path = url['incidents_path']
        msg = "{0} Started execution...".format(sys.argv[0])
        try:
            data_incident = pd.read_csv(incidents_path+'incidents.csv')
            data_incident = data_incident.dropna()

            data = processing(data_incident)

            trainpredictor = data_preprocess_categories(data_incident)

            # train = trainModel()

            # pipeline, X_train = train.model(data)

            NB_pipeline = Pipeline([
                ('tfidf', TfidfVectorizer(stop_words=stopWords)),
                ('clf', OneVsRestClassifier(MultinomialNB(
                    fit_prior=True, class_prior=None))),
            ])
            model_features = {}

            for category in var_cols:
                print('... Processing {}'.format(category))
                # train the model using X_dtm & y
                model_features[category] = NB_pipeline.fit(trainpredictor['incident_description'], trainpredictor[category])
                # compute the testing accuracy
                prediction = NB_pipeline.predict(trainpredictor['incident_description'])
                prediction_c = [x for x in prediction if x > 0]
                print('Test accuracy is {}'.format(accuracy_score(trainpredictor[category], prediction)))

                # model_features[category] = NB_pipeline

                output = open(path+'trainedModel_NB' + category + '.pkl', 'wb')
                pickle.dump(NB_pipeline, output)
                output.close()

            '''
            model_features = {}

            for feature in var_cols:
                y_train = trainpredictor[feature].tolist()
                model = pipeline.fit(X_train, y_train)
                model_features[feature] = model'''


            outputs = {"status": "success", "response": list(model_features)}

            return str(json.dumps(outputs))

        except Exception as e:
            raise e
            msg = "Failure in executing {0}".format(sys.argv[0])

            return str({"status": "error", "response": {"message": str(e)}})

        class Java:
            implements = ["com.apporchid.cloudseer.python.IPythonScript"]


from py4j.clientserver import ClientServer, JavaParameters, PythonParameters

# from AW_logger import awLogger
import sys
# logger = awLogger
import sys

pythonPort = int(sys.argv[1])
Demoops = DemoClassification()

#print(Demoops.executeScript({'input_dir':'C:/apporchid/prediction/'}))

gateway = ClientServer(
    java_parameters=JavaParameters(),
    python_parameters=PythonParameters(port = pythonPort),
    python_server_entry_point = Demoops)
Demoops.clientserver = gateway