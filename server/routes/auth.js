const express = require('express');
const router = express.Router();
const authController = require('../controllers/auth');

// TODO: add all routes
router.post('/signin', authController.login);
////////////

module.exports = router;
