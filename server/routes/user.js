const express = require('express');
const router = express.Router();

const userController = require('../controllers/user');
const postRoutes = require('../routes/post');

router.use('/post', postRoutes);

router.get('/friends', userController.getAllFriends);
router.post('/changeFriendRelation', userController.changeFriendRelation);

module.exports = router;
