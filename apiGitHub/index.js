const express = require("express");
const bodyParser = require("body-parser");
const cors = require("cors");
require('dotenv/config');

const controller = require("./controllers/controller.js");

const mongoDBConnectionString = process.env.DB_CONNECTION;
const HTTP_PORT = process.env.PORT || 8081;

const data = controller(mongoDBConnectionString);

const app = express();
app.use(bodyParser.json());
app.use(cors());

app.get("/users",(req,res)=>{
    data.getAllUsers().then((dat)=>{
        res.json(dat);
    }).catch(err=>{
        res.status(500).end();
    })
});

app.get("/pets",(req,res)=>{
    data.getAllPets().then((dat)=>{
        res.json(dat);
    }).catch(err=>{
        res.status(500).end();
    })
});

app.get("/customers",(req,res)=>{
    data.getAllCustomers().then((dat)=>{
        res.json(dat);
    }).catch(err=>{
        res.status(500).end();
    })
});

app.get("/breeds",(req,res)=>{
    data.getAllBreeds().then((dat)=>{
        res.json(dat);
    }).catch(err=>{
        res.status(500).end();
    })
});

app.get("/clinics",(req,res)=>{
    data.getAllClinics().then((dat)=>{
        res.json(dat);
    }).catch(err=>{
        res.status(500).end();
    })
});

app.get("/adoptionCenters",(req,res)=>{
    data.getAllAdoptionCenters().then((dat)=>{
        res.json(dat);
    }).catch(err=>{
        res.status(500).end();
    })
});

app.post("/adoptionCenter",(req,res)=>{
    console.log(req.body);
    data.addAdoptionCenter(req.body).then((dat)=>{
        res.json({"message": "Adoption Center " + dat + "added successfully"});
    }).catch((err)=>{
        res.send(500).end();
    })
});

app.post("/clinic",(req,res)=>{
    console.log(req.body);
    data.addClinic(req.body).then((dat)=>{
        res.json({"message": "Clinic " + dat + "added successfully"});
    }).catch((err)=>{
        res.send(500).end();
    })
});

app.post("/breed",(req,res)=>{
    console.log(req.body);
    data.addBreed(req.body).then((dat)=>{
        res.json({"message": "Breed " + dat + "added successfully"});
    }).catch((err)=>{
        res.send(500).end();
    })
});

app.post("/customer",(req,res)=>{
    console.log(req.body);
    data.addCustomer(req.body).then((dat)=>{
        res.json({"id": dat});
    }).catch((err)=>{
        res.send(500).end();
    })
});

app.post("/pet",(req,res)=>{
    console.log(req.body);
    data.addPet(req.body).then((dat)=>{
        res.json({"message": "Pet " + dat + "added successfully"});
    }).catch((err)=>{
        res.sendStatus(500).end();
    })
});

app.post("/user",(req,res)=>{        
    console.log(req.body);
    data.addUser(req.body).then((dat)=>{
        res.json({"id": dat});
    }).catch((err)=>{
        res.send(500).end();
    })
});

app.get("/customer/:customerId", (req,res) => {
    data.getCustomerById(req.params.customerId).then((data)=>{
        if(data.length > 0){
            //res.json(data);
            // var cus = JSON.parse(data[0]);
            var cus = data[0];
            var cusName = cus.cust_name;
            var cusEmail = cus.cust_email;
            var cusPhone = cus.cust_phone;
            res.send('<h1 style=font-size:50px>' +cusName+' - <a href="mailto: '+cusEmail+'">'+cusEmail+'</a> - <a href="tel:'+cusPhone+'">'+cusPhone+'</a></h1>'
                + '<h1>Please contact me if you find my lost pet. Thank you!</h1>');
        }else{
            res.status(404).end();
        }
    })
    .catch((err)=>{
        res.status(500).end();
    })
});

app.get("/customerRaw/:customerId", (req,res) => {
    data.getCustomerById(req.params.customerId).then((data)=>{
        if(data.length > 0){
            res.json(data);
        }else{
            res.status(404).end();
        }
    })
    .catch((err)=>{
        res.status(500).end();
    })
});

app.put("/updateCustomer/:customerId", (req, res) => {

    data.updateCustomerById(req.params.customerId, req.body).then((data)=>{
        res.json({"message": "Customer " + data + " updated successfully"});
    })
    .catch((err)=>{
        res.send(err);
        res.status(500).end();
    })
});

app.get("/user/:userId", (req,res)=>{
    data.getUserById(req.params.userId).then(data=>{
        res.json(data);
    }).catch(err=>{
        res.status(500).end();
    })
});

app.get("/users/:name", (req,res)=>{
    console.log(req.params.name);
    data.getUserByUsername(req.params.name).then(data=>{
        res.json(data);
    }).catch(err=>{
        res.status(500).end();
    })
});

app.put("/updateUser/:userId", (req, res) => {

    data.updateUserById(req.params.userId, req.body).then((data)=>{
        res.json({"message": "User " + data + " updated successfully"});
    })
    .catch((err)=>{
        res.send(err);
        res.status(500).end();
    })
});


data.connect().then(()=>{
    app.listen(HTTP_PORT, ()=>{console.log("API listening on " + HTTP_PORT)});
}).catch(err=>{
    console.log("unable to start the server " + err);
    process.exit();
})