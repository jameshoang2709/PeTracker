const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const userSchema = new Schema({
    username:String,
    password:String,
    customer:{
        type:Schema.Types.ObjectId,
        ref:'customer'
    }
});

module.exports = userSchema;