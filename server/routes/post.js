const express = require('express');
const router = express.Router();
const postController = require('../controllers/post');

// TODO: add all routes
router.get('/', postController.getAllPosts);
router.post('/', postController.addNewPost);
router.get("/validateLikeWithUser",postController.validateLikeWithUser)
////////////

module.exports = router;
