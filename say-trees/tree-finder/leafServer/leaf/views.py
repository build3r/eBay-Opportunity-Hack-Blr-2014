from django.shortcuts import render
import os
import sys
import cv2
import numpy as np
import math
from sklearn import svm
from cPickle import dump,HIGHEST_PROTOCOL,load
from sklearn.metrics import classification_report,confusion_matrix
import matplotlib.pyplot as plt
import shutil
from BoW import getImmediateSubdirectories,getWordsInImages,getCentroids,computeHistrogram,computeHistrogramByLevel
from django.http import HttpResponse
import json
from django.views.decorators.csrf import csrf_exempt
from django.template import RequestContext
from django.shortcuts import render_to_response
PRE_ALLOCATION_BUFFER=1000

@csrf_exempt
def filename_listener(request):
    predict, name = None, None
    if request.method == 'POST':
        filename =  request.POST.get('filename')
        print filename
        imgFilesPathsWithLabels = {}
        imgFilesPathsWithLabels[filename] = 0 
        dictWordsImages=getWordsInImages(imgFilesPathsWithLabels, Dense=True, disableSIFTWrite=True)
        print "Using trained vocabulary or visual words"
        print "------------------------------------------------------"
        centroidFile = open('leaf/CENTROID.file')
        centroids=load(centroidFile)
        print "Computing Histogram from centroids"
        allwordVocabularyHistrogram=computeHistrogramByLevel(centroids,dictWordsImages)
        print "------------------------------------------------------"
        print "write the histogram.out"
        modelFile = open('leaf/model.file')
        clf = load(modelFile)
        predict, name =  clf.predict(allwordVocabularyHistrogram.values())[0], allwordVocabularyHistrogram.keys()[0]
	
    jsonData = json.dumps({'predict':predict})
    return HttpResponse(jsonData, content_type='application/json')


@csrf_exempt
def mainView(request):
    context = RequestContext(request)
    context_dict = {}
    return render_to_response('main/SayTrees.html', context_dict, context)
