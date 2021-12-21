const express = require('express');
require('dotenv').config();

const connectToMongo = require('./db/connection');
//////////
const userRoutes = require('./routes/user');
const authRoutes = require('./routes/auth');
const postRoutes = require('./routes/post');

///////////
const app = express();
const port = process.env.NODE_LOCAL_PORT;

app.use(express.urlencoded({ extended: false }));
app.use(express.json());
////////////////
app.use('/auth', authRoutes);
app.use('/user', userRoutes);
app.use('/post', postRoutes);
//app.use("/auth",authRoutes)
////////////
app.listen(port, () => {
  console.log(`Server listening on port ${port}`);
  connectToMongo();
});

module.exports = app;
