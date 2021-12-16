const User = require('../models/User');

const login = async (req, res) => {
  const x = ({ userName, password } = req.body);
  try {
    const user = await User.findOne({ userName }).populate('friends');
    if (!user) return res.status(404).json();
    if (password != user.password_hash) return res.status(404).json();
    return res.json(user);
  } catch (error) {
    return res.status(401).json(error.message);
  }
};
//----------------/
module.exports = {
  login,
};
