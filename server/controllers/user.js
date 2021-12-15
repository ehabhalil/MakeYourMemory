const User = require('../models/User');
const bcrypt = require('bcrypt');

//--------------------/
const getAllFriends = async (req, res) => {
  try {
    const user = await User.find({}).populate("friends");
    if (!user) return res.status(404).json({ messege: 'user not found' });
    else return res.json(user);
  } catch (error) {
    return res.status(401).json(error.message);
  }
};

const addNewUser = async (req, res) => {
  const { userName, firstName, lastName, password, password2, avatar } = req.body;
  try {
    const user = await User.findOne({ userName });
    if (user) {
      return res.status(400).json('username already exists');
    }
    if (password !== password2) {
      return res.status(400).json('passwords do not match');
    }
    // hash the password using bcrypt and salt
    let hashed_password = await bcrypt.hash(password, 10);
    const resp = await User.create({
      userName: userName,
      firstName: firstName,
      lastName: lastName,
      avatar: avatar,
      password_hash: hashed_password,
    });
    return res.status(201).json(resp);
  } catch (error) {
    return res.status(401).json(error.message);
  }
};
//--------------------/

module.exports = {
  getAllFriends,
  addNewUser,
};
