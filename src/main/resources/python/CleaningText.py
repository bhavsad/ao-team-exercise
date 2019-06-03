import pandas as pd
import re
import string
import nltk
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize
import numpy as np

stopWords = set(stopwords.words('english'))


def remove_stopword(text):
    stop_words = set(stopwords.words('english'))
    word_tokens = word_tokenize(text)
    filtered_sentence = [w.lower() for w in word_tokens if not w.lower() in stop_words]
    filtered_sentence = []
    for w in word_tokens:
        if w not in stop_words:
            filtered_sentence.append(w)
    return ' '.join(filtered_sentence).lower()


def processing(df):
    # lowering and removing punctuation
    df['incident_description'] = df['incident_description'].apply(lambda x: re.sub(r'[^\w\s]', '', x.lower()))
    df['incident_description'] = df['incident_description'].apply(lambda x: re.sub(r'[^\w\s]', '', x.lower()))
    # numerical feature engineering
    # total length of sentence
    df['length'] = df['incident_description'].apply(lambda x: len(x))
    # get number of words
    df['words'] = df['incident_description'].apply(lambda x: len(x.split(' ')))
    df['words_not_stopword'] = df['incident_description'].apply(
        lambda x: len([t for t in x.split(' ') if t not in stopWords]))
    # get the average word length
    df['avg_word_length'] = df['incident_description'].apply(
        lambda x: np.mean([len(t) for t in x.split(' ') if t not in stopWords]) if len(
            [len(t) for t in x.split(' ') if t not in stopWords]) > 0 else 0)
    # get the average word length
    df['commas'] = df['incident_description'].apply(lambda x: x.count(','))

    return (df)


def data_preprocess_categories(data):
    regex = re.compile('[%s]' % re.escape(string.punctuation))

    # data['incident_description'] = data['incident_description'].apply(lambda x: remove_stopword(x))
    data['incident_description'] = data['incident_description'].apply(lambda x: regex.sub('', x))
    data['primary_cause'] = data['primary_cause'].apply(lambda x: [s.strip() for s in x.split('-') if s != ''])

    categories = data['primary_cause'].tolist()
    uniq_list = []
    uniq_list = [cat1 for cat in categories for cat1 in cat if cat1 not in uniq_list]
    categories = list(set(uniq_list))

    for cat in categories:
        data[cat] = 0
    for cat in categories:
        for idx, row in data.iterrows():
            if cat in row['primary_cause']:
                data.loc[idx, cat] = 1
    return data


from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.pipeline import Pipeline
from sklearn.base import BaseEstimator, TransformerMixin
import pandas as pd


class TextSelector(BaseEstimator, TransformerMixin):
    """
    Transformer to select a single column from the data frame to perform additional transformations on
    Use on text columns in the data
    """

    def __init__(self, key):
        self.key = key

    def fit(self, X, y=None):
        return self

    def transform(self, X):
        return X[self.key]


class NumberSelector(BaseEstimator, TransformerMixin):
    """
    Transformer to select a single column from the data frame to perform additional transformations on
    Use on numeric columns in the data
    """

    def __init__(self, key):
        self.key = key

    def fit(self, X, y=None):
        return self

    def transform(self, X):
        return X[[self.key]]


class trainModel(object):
    def model(self, data):
        # features = [c for c in data.columns.values if c not in ['primary_cause']]
        features = ['incident_description', 'length', 'words', 'words_not_stopword', 'avg_word_length', 'commas']
        target = 'primary_cause'
        data = data.dropna()
        X_train = data[features]

        text = Pipeline([
            ('selector', TextSelector(key='incident_description')),
            ('tfidf', TfidfVectorizer(stop_words=stopWords))
        ])

        text.fit_transform(X_train)

        from sklearn.preprocessing import StandardScaler

        length = Pipeline([
            ('selector', NumberSelector(key='length')),
            ('standard', StandardScaler())
        ])

        length.fit_transform(X_train)

        words = Pipeline([
            ('selector', NumberSelector(key='words')),
            ('standard', StandardScaler())
        ])
        words_not_stopword = Pipeline([
            ('selector', NumberSelector(key='words_not_stopword')),
            ('standard', StandardScaler())
        ])
        avg_word_length = Pipeline([
            ('selector', NumberSelector(key='avg_word_length')),
            ('standard', StandardScaler())
        ])
        commas = Pipeline([
            ('selector', NumberSelector(key='commas')),
            ('standard', StandardScaler()),
        ])

        from sklearn.pipeline import FeatureUnion

        feats = FeatureUnion([('incident_description', text),
                              ('length', length),
                              ('words', words),
                              ('words_not_stopword', words_not_stopword),
                              ('avg_word_length', avg_word_length),
                              ('commas', commas)])

        feature_processing = Pipeline([('feats', feats)])
        feature_processing.fit_transform(X_train)

        from sklearn.ensemble import RandomForestClassifier

        pipeline = Pipeline([
            ('features', feats),
            ('classifier', RandomForestClassifier(random_state=42)),
        ])
        return pipeline, X_train