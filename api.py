from flask import Flask, url_for, redirect
from flask.ext.admin import Admin, form
from flask.ext.admin.contrib.sqla import ModelView
import json
from models import *
import logging
from flask.ext.admin.contrib import fileadmin
from flask_wtf import Form
from wtforms import StringField
from jinja2 import Markup
from requests import post

app  = Flask(__name__)

@app.route("/")
def index():
    return render_template("index.html")

@app.route("/donation", methods=["GET","POST"])
def donation():
    return render_template("donation.html")

@app.route("/generateReceipt", methods=["GET","POST"], defaults={'path': ''})
@app.route("/generateReceipt<path:path>", methods=["GET","POST"])
def generateReceipt(path):
    name = request.args['name']
    amount = request.args["amount"]
    payment_type = request.args["payment_type"]
    print name, amount, payment_type, "******************"
    return render_template("receipt.html", name=name,amount=amount, payment_type=payment_type)


@app.route('/child', methods=['GET','POST','DELETE'])
@app.route('/child/<child_id>', methods=['GET','DELETE'])
def child(child_id=None):
    if request.method == 'GET':
        if not child_id:
            allChildren = Child.query.all()
            return render_template("donationdetails.html", details=allChildren)
        else:
            Children = Child.query.filter_by(id=child_id)
            return render_template("donationdetails.html", details=ddonors)
    try:
        if request.method == 'POST':
            # Decide create/update
            args = request.form
            child_exists = Child.query.filter_by(name=args.get('name')).all()
            if child_exists:
                assert len(child_exists) ==  1, logging.warn("Too many child records found in db")
                oldChild = child_exists[0]
                if request.files:
                    # upload files
                    bytestring = request.files.get('imgFile')[0].body
                    img = Image.fromstring(bytestring)
                    file_path = base_path+"images/" + datetime.datetime.utctimenow().strftime("%d-%B-%Y-%H:%M")
                    img.save(file_path)
                    pic_paths = json.loads(oldChild.pic_paths)
                    oldChild.pic_paths = pic_paths.append(file_path)
                    oldChild.pic_paths = oldChild.pic_paths[-5:] # Max 5 images
                for k,v in args.iteritems():
                    if k in utils.get_user_attributes(Child):
                        oldChild[k] = v
                db.session.add(oldChild)
                db.session.commit()
                # update
                return json.dumps({'status':'success'})
            else:
                # create request
                newChild = Child(args.get('name'), args.get('cost'), args.get('status','new'))
                db.session.add(newChild)
                db.session.commit()
                return json.dumps({'id':newChild.id})

        if request.method == 'DELETE':
            if not child_id:
                logging.warn("Found multiple chiled records for one name ")
                return json.dumps({'status':'fail'})
            else:
                children = Child.query.filter_by(id=child_id).all()
                assert len(children) == 1, logging.warn("Found multiple chiled records for one name ")
                for child in children:
                    db.session.delete(child)
                db.session.commit()
                return json.dumps({'status':'success'})
    except Exception:
        db.session.rollback()

@app.route('/surgery', methods=['GET','POST','DELETE'])
@app.route('/surgery/<surg_id>', methods=['GET','DELETE'])
def SurgeryApi():
    if request.method == 'GET':
        if not surg_id:
            allSurgeries = Surgery.query.all()
            return json.dumps(allSurgeries)
        else:
            Surgeries = Surgery.query.filter_by(id=surg_id)
            return json.dumps(Surgeries)
    try:
        if request.method == 'POST':
            args = request.form
            surgery_exists = Surgery.query.filter_by(name=args.get('name')).all()
            if surgery_exists:
                assert len(surgery_exists) ==  1, logging.warn("Too many surgery records found in db")
                oldSurgery = surgery_exists[0]
                for k,v in args.iteritems():
                    if k in utils.get_user_attributes(Surgery):
                        oldSurgery[k] = v
                db.session.add(oldSurgery)
                db.session.commit()
                # update
                return json.dumps({'status':'success'})
            else:
                # create request
                newSurgery = Surgery(args.get('name'), args.get('cost'), args.get('status','new'))
                db.session.add(newSurgery)
                db.session.commit()
                return json.dumps({'id':newSurgery.id})
        if request.method == 'DELETE':
            if not surg_id:
                logging.warn("Found multiple chiled records for one name ")
                return json.dumps({'status':'fail'})
            else:
                Surgeries = Surgery.query.filter_by(id=surg_id).all()
                assert len(Surgeries) == 1, logging.warn("Found multiple surgery records for one name ")
                for surg in Surgeries:
                    db.session.delete(surg)
                db.session.commit()
                return json.dumps({'status':'success'})
    except Exception:
        db.session.rollback()

@app.route('/transaction', methods=['GET','POST','DELETE'])
@app.route('/transaction/<trxn_id>', methods=['GET','DELETE'])
def Transaction():
    if request.method == 'GET':
        if not trxn_id:
            allTrxns = Trxns.query.all()
            return json.dumps(allTrxns)
        else:
            Trxns = Trxns.query.filter_by(id=trxn_id)
            return json.dumps(Trxns)
    try:
        if request.method == 'POST':
            import pdb; pdb.set_trace()
            args = request.form
            trxn_exists = Trxns.query.filter_by(name=args.get('name')).all()
            if trxn_exists:
                assert len(trxn_exists) ==  1, logging.warn("Too many trxn records found in db")
                oldTransaction = trxn_exists[0]
                for k,v in args.iteritems():
                    if k in utils.get_user_attributes(Trxns):
                        oldTransaction[k] = v
                db.session.add(oldTransaction)
                db.session.commit()
                return json.dumps({'status':'success'})
                # update
            else:
                # create request
                newTransaction = Transaction(args.get('name'), args.get('cost'), args.get('status','new'))
                db.session.add(newTransaction)
                db.session.commit()
                return json.dumps({'id':newTransaction.id})

        if request.method == 'DELETE':
            if not trxn_id:
                logging.warn("Found multiple chiled records for one name ")
                return json.dumps({'status':'fail'})
            else:
                Trxns = Transaction.query.filter_by(id=trxn_id).all()
                assert len(Trxns) == 1, logging.warn("Found multiple surgery records for one name ")
                for trxn in Trxns:
                    db.session.delete(trxn)
                db.session.commit()
                return json.dumps({'status':'success'})
    except Exception:
        db.session.rollback()

@app.route('/donor', methods=['GET','POST','DELETE'])
@app.route('/donor/<donor_id>', methods=['GET','DELETE'])
def Donors(donor_id=None):
    print donor_id,"@@@@@@@@@@@@"
    if request.method == 'GET':
        if not donor_id:
            allDonors = Donor.query.all()
            return render_template("donationdetails.html", details=allDonors)
        else:
            ddonors = Donor.query.filter_by(email_id=donor_id)
            print ddonors,"##########"
            return render_template("donationdetails.html", details=ddonors)
    try:
        if request.method == 'POST':
            args = request.form
            donor_exists = Donor.query.filter_by(name=args.get('name')).all()
            if donor_exists:
                assert len(donor_exists) ==  1, logging.warn("Too many donor records found in db")
                oldDonor = donor_exists[0]
                for k,v in args.iteritems():
                    if k in utils.get_user_attributes(Donor):
                        oldDonor[k] = v
                db.session.add(oldDonor)
                db.session.commit()
            else:
                # create request
                newDonor = Donor(args.get('name'), args.get('donated_amnt'), args.get('email',None), args.get('p_type','onetime'))
                db.session.add(newDonor)
                db.session.commit()
                # update
        if request.method == 'DELETE':
            if not donor_id:
                logging.warn("Found multiple chiled records for one name ")
                return json.dumps({'status':'fail'})
            else:
                Donors = Donor.query.filter_by(id=donor_id).all()
                assert len(Donors) == 1, logging.warn("Found multiple surgery records for one name ")
                for donor in Donors:
                    db.session.delete(donor)
                db.session.commit()
                return json.dumps({'status':'success'})

    except Exception,e:
        logging.error(e)
        db.session.rollback()


@app.route("/makeDonation", methods= ["POST"])
def makeDonation():
    args = request.form

    try:
        newDonor = Donor(args.get('name'), args.get('donated_amnt'), args.get('email',None), args.get('p_type','onetime'))
        db.session.add(newDonor)
        db.session.commit()
        
        #r = post("http://rang-de.org/sumukha", data=data)
        #url = r.json().url
        #redirect(url)
        


    except Exception,e:
        logging.error(e)
        db.session.rollback()

@app.route("/paymentResult", methods=["POST"])
def payment_result():
    txn = Trxns(**request.form)
    db.session.add(txn)
    db.session.commit()

    if request.form.get("status") == "success":
        render_template("receipt.html")
    else:
        render_template("failed.html")
    
        
admin = Admin(app)

admin.add_view(ModelView(Donor,db.session))
admin.add_view(ModelView(Trxns,db.session))
admin.add_view(ModelView(Surgery,db.session))

admin.add_view(fileadmin.FileAdmin(base_path+ "/images", name="images"))


class UserView(ModelView):
    def _list_thumbnail(view, context, model, name):
        if not model.pic_paths:
            return ''

        return Markup('<img src="%s">' % url_for('static',
                                                     filename="images/"+form.thumbgen_filename(model.pic_paths)))

    column_formatters = {
        'pic_paths': _list_thumbnail
    }

    # Alternative way to contribute field is to override it completely.
    # In this case, Flask-Admin won't attempt to merge various parameters for the field.
    form_extra_fields = {
        'pic_paths': form.ImageUploadField('Image',
                                      base_path=base_path+"/images",
                                      thumbnail_size=(100, 100, True))
    }

admin.add_view(UserView(Child,db.session))


if __name__ == '__main__':

    app.config.update(
        DEBUG=True,
        SECRET_KEY='sdfjalskdfj',
    )
    # Create upload directory
    try:
        os.mkdir(base_path)
    except OSError:
        pass
    # Create DB
    db.create_all()
    # Start app
    app.run(debug=True)


