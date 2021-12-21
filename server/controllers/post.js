const Post = require('../models/Post');
const User = require('../models/User');
const mongoose = require('mongoose');

const getAllPosts = async (req, res) => {
  const { userId } = req.body;
  try {
    const mainUser = await User.findById(userId);
    mainUser.friends.push(mongoose.Types.ObjectId(userId));
    const resp = await Post.find({
      user: {
        $in: mainUser.friends,
      },
    })
      .populate('user')
      .sort({ createdAt: -1 });
    return res.json(resp);
  } catch (error) {
    return res.status(401).json(error.message);
  }
};
const getUserPosts = async (req, res) => {
  const { userId } = req.body;
  try {
    const resp = await Post.find({ user: mongoose.Types.ObjectId(userId) })
      .populate('user')
      .sort({ createdAt: -1 });
    return res.json(resp);
  } catch (error) {
    return res.status(401).json(error.message);
  }
};
const addNewPost = async (req, res) => {
  const { id, text, imageURL, likes } = req.body;
  try {
    const resp = await Post.create({
      text,
      imageURL,
      likes: likes,
      user: id,
    });
    return res.json(resp);
  } catch (error) {
    return res.status(401).json(error.message);
  }
};
const deletePost = async (req, res) => {
  const { postId } = req.body;
  try {
    const resp = await Post.findByIdAndDelete(postId);
    return res.json(resp);
  } catch (error) {
    return res.status(401).json(error.message);
  }
};
const addNewComment = async (req, res) => {
  const { userId, postId, text, imageURL, userName } = req.body;
  try {
    const resp = await Post.findById(postId);
    resp.comments.push({
      text,
      user: {
        id: userId,
        imageURL: imageURL,
        userName: userName,
      },
    });
    await resp.save();
    return res.json(resp);
  } catch (error) {
    return res.status(401).json(error.message);
  }
};
const validateLikeWithUser = async (req, res) => {
  const { userId, postId } = req.body;
  try {
    const resp = await Post.findById(postId);
    let a = [];
    resp.likes.filter((element) => {
      a.push(element.user.toString());
    });
    if (!a.includes(userId)) {
      resp.likes.push({ user: userId });
      await resp.save();
      return res.status(404).json(resp);
    } else {
      a = [];
      resp.likes.filter((v) => {
        if (v.user.toString() != mongoose.Types.ObjectId(userId).toString()) {
          a.push(v);
        }
      });
      resp.likes = a;
      await resp.save();
      return res.status(200).json(resp);
    }
  } catch (error) {
    return res.status(401).json(error.message);
  }
};
module.exports = {
  getAllPosts,
  addNewPost,
  validateLikeWithUser,
  addNewComment,
  getUserPosts,
  deletePost,
};
