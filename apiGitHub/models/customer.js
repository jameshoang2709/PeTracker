const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const customerSchema = new Schema({
    cust_name:String,
    cust_email:String,
    cust_tracker:String,
    cust_phone:String,
    pet:[{
        type: Schema.Types.ObjectId,
        ref: 'pet'
    }]
});

module.exports = customerSchema;