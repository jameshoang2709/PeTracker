const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const clinicSchema = new Schema({
    name:String,
    address:String
});

module.exports = clinicSchema;