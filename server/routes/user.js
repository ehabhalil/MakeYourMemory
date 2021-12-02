const express = require("express");
const router = express.Router();

const userController = require("../controllers/user");
const postRoutes = require("../routes/post");

router.use("/post",postRoutes)

////////////// TODO add all routes
router.get("/:id/friends",userController.getAllFriends);
router.post("/",userController.addNewUser);
////////////
module.exports = router;