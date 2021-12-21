const User = require('../models/User');
const bcrypt = require('bcrypt');
const getAllFriends = async (req, res) => {
  try {
    const user = await User.find({}).populate('friends');
    if (!user) return res.status(404).json({ messege: 'user not found' });
    else return res.json(user);
  } catch (error) {
    return res.status(401).json(error.message);
  }
};
const changeFriendRelation = async (req, res) => {
  const { userId, friendId } = req.body;
  try {
    const resp = await User.findById(userId);
    if (!resp.friends.includes(friendId)) {
      resp.friends.push(friendId);
      await resp.save();
      return res.status(200).json({ user: await resp.populate('friends'), statue: true });
    } else {
      let a = [];
      resp.friends.filter((v) => {
        if (v != friendId) {
          a.push(v);
        }
      });
      resp.friends = a;
      await resp.save();
      return res.status(200).json({ user: await resp.populate('friends'), statue: false });
    }
  } catch (error) {
    return res.status(401).json(error.message);
  }
};

module.exports = {
  getAllFriends,
  changeFriendRelation,
};
