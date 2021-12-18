const Post = require('../models/Post');
const mongoose = require('mongoose');
const { json } = require('express/lib/response');

//----------------/
const getAllPosts = async (req, res) => {
  const { id } = req.params;
  try {
    const resp = await Post.find({});
    return res.json(resp);
  } catch (error) {
    return res.status(401).json(error.message);
  }
};

const addNewPost = async (req, res) => {
  const { id, text, imageURL, likes } = req.body;
  console.log(req.body);
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
const validateLikeWithUser = async (req, res) => {
  const { userId, postId } = req.body;
  try {
    console.log(req.body);
    const resp = await Post.findById(postId);
    const a = [];
    resp.likes.filter((element) => {
      a.push(element.user.toString());
    });
    if (!a.includes(userId)) res.sendStatus(404);
    else return res.sendStatus(200);
  } catch (error) {
    return res.status(401).json(error.message);
  }
};
//----------------/
module.exports = {
  getAllPosts,
  addNewPost,
  validateLikeWithUser,
};
