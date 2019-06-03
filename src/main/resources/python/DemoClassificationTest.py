from CleaningText import *
from sklearn.multiclass import OneVsRestClassifier
import numpy as np
from sklearn.naive_bayes import MultinomialNB
from sklearn.metrics import accuracy_score

var_cols = ['WEATHER',
            'HIGH_WINDS', 'POOR_VISIBILITY', 'VEHICLE', 'FALL', 'LOW_VISIBILITY',
            'THUNDERSTORM', 'NEGLIGENCE', 'POOR_JUDGMENT', 'TRENCHING',
            'ELECTRICAL', 'RAIN', 'HAZARDOUS_CHEMICAL', 'SNOW', 'CHEMICAL', 'FOG']


class TestModel(object):
    def __init__(self, clientserver=None):
        self.clientserver = clientserver

    def executeScript(self, url):
        import json, sys
        import pickle

        msg = "{0} Started execution...".format(sys.argv[0])

        # data = yaml.safe_load(url)
        url = json.loads(url)
        url = json.loads(url)
        text = url['input_text']
        path = url['input_dir']
        try:
            test = pd.DataFrame()
            test['incident_description'] = [text]
            test = processing(test)

            category_classify = pd.DataFrame()

            # pickle_in = open("trainedModel" + ".pkl", "rb")
            # model = pickle.load(pickle_in)
            # pickle_in.close()
            for category in var_cols:
                pickle_in = open(path + "trainedModel_NB" + category + ".pkl", "rb")
                model = pickle.load(pickle_in)
                pickle_in.close()
                if model.predict(test['incident_description'])[0] == 1:
                    print(category)
                # category_classify[category] = model[category].predict(test)
                category_classify[category] = model.predict(test['incident_description'])

            category_classify['label'] = ''

            for col in var_cols:
                for idx, row in category_classify.iterrows():
                    if category_classify.loc[idx, col] >= 0.6:
                        if category_classify.loc[idx, 'label'] == '':
                            category_classify.loc[idx, 'label'] = col
                        else:
                            category_classify.loc[idx, 'label'] = category_classify.loc[idx, 'label'] + str('-') + col

            output = category_classify[['label']].to_dict('records')

            outputs = {"status": "success", "response": np.array(output).tolist()}

            return str(json.dumps(outputs))

        except Exception as e:
            raise e
            msg = "Failure in executing {0}".format(sys.argv[0])
            return str({"status": "error", "response": {"message": str(e)}})

        class Java:
            implements = ["com.apporchid.cloudseer.python.IPythonScript"]


from py4j.clientserver import ClientServer, JavaParameters, PythonParameters

import sys

pythonPort = int(sys.argv[1])
test = TestModel()
# print(test.executeScript({
#                              'input': 'After opening the Well vault hatch, the employee completed a visual inspection of the vault before entering.  The employee noticed a snake coiled around the hinge to the vault lid.  The snake was safely removed using a couple of long sticks and returned to the wooded area next to the facility'}))
gateway = ClientServer(
    java_parameters=JavaParameters(),
    python_parameters=PythonParameters(port=pythonPort),
    python_server_entry_point=test)
test.clientserver = gateway