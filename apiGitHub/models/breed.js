const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const breedSchema = new Schema({
    breed_type:String
});

module.exports = breedSchema;