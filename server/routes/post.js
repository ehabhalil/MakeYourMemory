const express = require('express');
const router = express.Router();
const postController = require('../controllers/post');

router.post('/', postController.getAllPosts);
router.post('/new', postController.addNewPost);
router.post('/validateLikeWithUser', postController.validateLikeWithUser);
router.post('/comment', postController.addNewComment);
router.post('/posts', postController.getUserPosts);
router.post('/delete', postController.deletePost);

module.exports = router;
