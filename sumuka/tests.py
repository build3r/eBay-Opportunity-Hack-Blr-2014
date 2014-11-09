import requests
import random
from models import *
resources = ['/child', '/donor', '/surgery','/transaction']
url_params = {'/child':{'name':'testchild' + str(random.choice(range(15))), 'cost':10000,},
        '/donor':{'name':'testdonor'+ str(random.choice(range(15))),'donated_amnt':2000, 'email':'testmail@google.com'},
              '/surgery':{'name':'testsurgery'+ str(random.choice(range(15))),},
              '/transaction':{'name':'testtrxn'+ str(random.choice(range(5)))}
              }
hostname = "http://127.0.0.1:5000"
def test_insert_record():
    db.engine.execute('use sumukha;')
    child_count_query = 'select count(*) from child;'
    donor_count_query = 'select count(*) from donor;'
    child_count_b4 = db.engine.execute(child_count_query).fetchall()
    donor_count_b4 = db.engine.execute(donor_count_query).fetchall()
    for url in resources:
        post_url = hostname + url
        r = requests.post(post_url, data=url_params.get(url))
    child_count_after = db.engine.execute(child_count_query).fetchall()
    donor_count_after = db.engine.execute(donor_count_query).fetchall()
    assert child_count_b4[0][0] +1 == child_count_after[0][0]
    assert donor_count_b4[0][0] +1 == donor_count_after[0][0]


if __name__ == '__main__':
    test_insert_record()
