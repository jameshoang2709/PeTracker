const mongoose = require("mongoose");

const adoptionCenterSchema = require("../models/adoptionCenter");
const breedSchema = require("../models/breed");
const clinicSchema = require("../models/clinic");
const customerSchema = require("../models/customer");
const petSchema = require("../models/pet");
const userSchema = require("../models/user");


module.exports = function (mongoDBConnectionString) {

    let adoptionCenter;
    let breed;
    let clinic;
    let customer;
    let pet;
    let user;

    return {
        connect: function () {
            return new Promise(function (resolve, reject) {
                mongoose.set('useUnifiedTopology', true);
                let db = mongoose.createConnection(mongoDBConnectionString, { useNewUrlParser: true });

                db.on('error', (err) => {
                    reject(err);
                });

                db.once('open', () => {
                    adoptionCenter = db.model("adoptionCenter", adoptionCenterSchema);
                    breed = db.model("breed", breedSchema);
                    clinic = db.model("clinic", clinicSchema);
                    pet = db.model("pet", petSchema);
                    customer = db.model("customer", customerSchema);
                    user = db.model("user", userSchema);

                    resolve();
                });
            })
        },
        getAllAdoptionCenters: function () {
            return new Promise(function (resolve, reject) {
                adoptionCenter.find()
                    .exec()
                    .then((centers) => {
                        resolve(centers);
                    })
                    .catch(err => {
                        reject(err);
                    });
            })
        },
        getAllClinics: function () {
            return new Promise(function (resolve, reject) {
                clinic.find()
                    .exec()
                    .then((clinics) => {
                        resolve(clinics);
                    })
                    .catch(err => {
                        reject(err);
                    });
            })
        },
        getAllBreeds: function () {
            return new Promise(function (resolve, reject) {

                breed.find()
                    .exec()
                    .then((breeds) => {
                        resolve(breeds);
                    })
                    .catch(err => {
                        reject(err);
                    });
            })
        },
        getAllCustomers: function () {
            return new Promise(function (resolve, reject) {

                customer.find()
                .populate("pet")
                    .populate({
                        path: 'pet',
                        populate:{
                            path:'breed',
                            model:'breed'
                        }
                    })
                    .exec()
                    .then(customers => {
                        resolve(customers);
                    }).catch(err => {
                        reject(err);
                    });
            })
        },
        getAllPets: function () {
            return new Promise(function (resolve, reject) {

                pet.find().populate('breed')
                .exec().then(pets => {
                    resolve(pets);
                }).catch(err => {
                    reject(err);
                });
            })
        },
        getAllUsers:function(){
            return new Promise(function(resolve,reject){

                user.find()
                .populate("customer")
                .populate({
                    path:'customer',
                    populate:{
                        path:'pet',
                        model:'pet',
                        populate:{
                            path:'breed',
                            model:'breed'
                        }
                    }
                })
                .exec().then(users=>{
                    resolve(users);
                }).catch(err=>{
                    reject(err);
                });
            })
        },
        addAdoptionCenter: function (data) {
            return new Promise(function(resolve,reject){

                var newData = new adoptionCenter(data);

                newData.save((err,addedData)=>{
                    if(err){
                        reject(err);
                    }else{
                        resolve(addedData._id);
                    }
                });
            })
        },
        addClinic: function (data) {
            return new Promise(function(resolve,reject){

                var newData = new clinic(data);

                newData.save((err,addedData)=>{
                    if(err){
                        reject(err);
                    }else{
                        resolve(addedData._id);
                    }
                });
            })
        },
        addBreed: function (data) {
            return new Promise(function(resolve,reject){

                var newData = new breed(data);

                newData.save((err,addedData)=>{
                    if(err){
                        reject(err);
                    }else{
                        resolve(addedData._id);
                    }
                });
            })
        },
        addCustomer: function (data) {
            return new Promise(function(resolve,reject){

                var newData = new customer(data);

                newData.save((err,addedData)=>{
                    if(err){
                        reject(err);
                    }else{
                        resolve(addedData._id);
                    }
                });
            })
        },
        addPet: function (data) {
            return new Promise(function(resolve,reject){

                var newData = new pet(data);

                newData.save((err,addedData)=>{
                    if(err){
                        reject(err);
                    }else{
                        resolve(addedData._id);
                    }
                });
            })
        },
        addUser: function (data) {
            return new Promise(function(resolve,reject){

                var newData = new user(data);

                newData.save((err,addedData)=>{
                    if(err){
                        reject(err);
                    }else{
                        resolve(addedData._id);
                    }
                });
            })
        },
        getCustomerById: function (customerId) {
            return new Promise(function(resolve, reject){
                customer.find({_id: customerId})
                // .populate("pet")
                .limit(1)
                .exec()
                .then((customers) => {
                    resolve(customers);
                })
                .catch((err) => {
                    reject(err);
                });
            })
        },
        updateCustomerById: function (customerId, customerData) {
            return new Promise(function (resolve, reject) {
                if (Object.keys(customerData).length > 0) { // if there is data to update
                    customer.update({ _id: customerId }, // replace the current user with data from userData
                        {
                            $set: customerData
                        },
                        { multi: false })
                        .exec()
                        .then((data) => {
                            resolve(customerId);
                        })
                        .catch((err) => {
                            reject(err);
                        });
                } else {
                    resolve("customerData is empty");
                }
            });
        },

        getUserById: function(userId){
            return new Promise(function(resolve,reject){
                user.find({_id: userId})
                .limit(1)
                .exec()
                .then((users)=>{
                    resolve(users);
                })
                .catch(err=>{
                    reject(err);
                })
            });
        },
        getUserByUsername: function(name){
            return new Promise(function(resolve,reject){
                user.find({username: name})
                .limit(1)
                .exec()
                .then((users)=>{
                    resolve(users);
                })
                .catch(err=>{
                    reject(err);
                })
            });
        },
        updateUserById: function (userId, userData) {
            return new Promise(function (resolve, reject) {
                if (Object.keys(userData).length > 0) { // if there is data to update
                    user.update({ _id: userId }, // replace the current user with data from userData
                        {
                            $set: userData
                        },
                        { multi: false })
                        .exec()
                        .then((data) => {
                            resolve(userId);
                        })
                        .catch((err) => {
                            reject(err);
                        });
                } else {
                    resolve("userData is empty");
                }
            });
        }

    }
}