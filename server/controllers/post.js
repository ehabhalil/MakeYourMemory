const Post = require('../models/Post');
const mongoose = require("mongoose");

//----------------/
const getAllPosts = async (req,res)=>{
  const {id} = req.params
  try {
      const resp = await Post.find({user: id})
      return res.json(resp)
  } catch (error) {
      return res.status(401).json(error.message)
  }
}

const addNewPost = async (req,res)=>{
  const {id} = req.params
  const {text,imageURL} = req.body
  try {
        const resp = await Post.create({
          text,
          imageURL,
          user: id
        })
        return res.json(resp)
  } catch (error) {
      return res.status(401).json(error.message)
  }
}
//----------------/
module.exports= { 
  getAllPosts,
  addNewPost 
}