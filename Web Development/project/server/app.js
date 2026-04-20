const express = require('express');
const bodyParser = require('body-parser');
const cookieParser = require('cookie-parser');
var cors = require('cors')
const swaggerDocs = require("./swagger.js")
const dotenv = require("dotenv");
dotenv.config();

const initDatabaseConnection = require('./dbConnection.js');



const app = express();


app.use(cors({
  origin:"http://localhost:5173",
  credentials:true
}))

//default port
let port =3030;

// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: false }))
// parse application/json
app.use(bodyParser.json())
app.use(cookieParser());


app.use('/images', express.static(__dirname + '/images'));


initDatabaseConnection('cookbook');

require('./routes/session/session')(app);
require('./routes/cookbook/routes')(app);
swaggerDocs(app,port)
app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`)
});




