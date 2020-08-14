const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const adoptionCenterSchema = new Schema({
    name:String,
    address:String
});

module.exports = adoptionCenterSchema;