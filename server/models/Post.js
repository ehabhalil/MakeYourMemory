const mongoose = require("mongoose");

const like = new mongoose.Schema({
  user: {
    type: mongoose.Schema.Types.ObjectId,
    ref: "User",
  },
});

const comment = new mongoose.Schema(
  {
    text: {
      type: String,
      maxLength: 200,
    },
  },
  { timestamps: true }
);

const post = new mongoose.Schema(
  {
    text: {
      type: String,
      maxLength: 200,
    },
    imageURL: {
      type: String,
    },
    likes: [like],
    comment: [comment],
    user: {
      type: mongoose.Schema.Types.ObjectId,
      ref: "User",
    },
  },
  { timestamps: true }
);

module.exports = mongoose.model("Post", post);
