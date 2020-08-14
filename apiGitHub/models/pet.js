const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const petSchema = new Schema({
    pet_name:String,
    pet_age:Number,
    breed:{
        type: Schema.Types.ObjectId,
        ref:'breed'
    }
});

module.exports = petSchema;