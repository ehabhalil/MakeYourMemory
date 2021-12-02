const express = require("express");
const router = express.Router();
const postController = require("../controllers/post");

// TODO: add all routes
router.get("/:id", postController.getAllPosts);
router.post("/:id", postController.addNewPost);
////////////

module.exports = router;