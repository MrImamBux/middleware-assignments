const express = require('express');
var bodyParser = require('body-parser');

const app = express();

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.all('*', function callbackAccessControl (req, res, next) {
    res.header('Access-Control-Allow-Origin', '*');
    res.header('Access-Control-Allow-Methods', 'PUT, GET, POST, DELETE, OPTIONS');
    res.header('Access-Control-Allow-Headers', 'Content-Type');
    next();
});

app.get('/', function (req, res) {
    res.sendFile(__dirname + "/public/index.html");
});

app.get('/script.js', function (req, res) {
    res.sendFile(__dirname + "/public/js/script.js");
});

app.post('/calculateTax', function (req, res) {
    console.log(`${req.method} request for ${res.url}`);
    console.log(req.body.amount + " amount");
    console.log(req.body.tax + "    tax percent");
    var amount = parseFloat(req.body.amount);
    var tax = parseFloat(req.body.tax);

    tax = tax/100;
    var calculatedPct 	= (tax)*(amount);
    var totalAmount 	= parseFloat(calculatedPct) + parseFloat(amount);
    console.log(totalAmount+  "   totalAmount");
    var data =  {};
    if (isNaN(totalAmount)) {
        data = {
            status: 'Error',
            value: 'Please enter a Number'
        };
    } else {
        data =  {
            status: 'Success',
            value: totalAmount
        }
    }

    res.send(data);

});

app.listen(3000, function () {
    console.log('App listening on port : 3000');
})