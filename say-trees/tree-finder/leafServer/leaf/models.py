from django.db import models
from time import time

def get_upload_file_name(instance, filename):
    return 'file_image'
    #return 'new_image/%s_%s' % (str(time()).replace('.', '_'), filename)

class LeafFile(models.Model):
    image = models.FileField(upload_to = get_upload_file_name)

