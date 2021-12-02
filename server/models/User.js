const mongoose = require('mongoose');

const userSchema = mongoose.Schema({
  userName: {
    type: String,
    required: true,
    unique: true,
  },
  firstName: {
    type: String,
  },
  lastName: {
    type: String,
  },
  password_hash: {
    type: String,
    required: true,
  },
  registered_at: {
    type: Date,
    default: Date.now,
  },
  avatar: {
    type: String,
  },
  friends: [{
    type: mongoose.Schema.Types.ObjectId,
    ref: "User"
  }]
});

userSchema.virtual('fullname').get(function () {
  if (!this.firstname) {
    return this.username;
  } else if (!this.lastname) {
    return this.firstname;
  }
  return `${this.firstname} ${this.lastname}`;
});

module.exports = mongoose.model('User', userSchema);
