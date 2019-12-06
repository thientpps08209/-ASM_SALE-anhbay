var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);
var mongodb = require('mongodb');

var MongoClient = mongodb.MongoClient;
var url = 'mongodb://localhost:27017/account';

MongoClient.connect(url, function (err, db) {
    if (err) {
        console.log('Unable to connect to the mongoDB server.Error:', err);
    } else {
        //Hurray! we are connected.
        console.log('Connection established to', url);
        collection = db.collection('sale_phone');
     //   showuser = db.collection('User');
        sinhvien = db.collection('anhbay')
    }
});
http.listen(2000, function () {
    console.log('listening on * : 2000');
});


io.on('connection', function (socket) {
    //lang nghe login
    console.log("Socket id:" + socket.id);
    socket.on('login', function (email, password) {
        console.log("Eventlogin:" + email + "va pass:" + password);
        var cursor = collection.find({ email: email });
        cursor.each(function (err, doc) {
            if (err) {
                console.log(err);
                socket.emit('login', false);
            } else {
                if (doc != null) {
                    if (doc.password == password) {
                        console.log("Đăng nhập thành công");
                        console.log(doc.password);
                        socket.emit('login', true);
                    } else {
                        console.log("Đăng nhập thành công");
                        socket.emit('login', false);
                    }
                } else {
                    socket.emit('login', false);
                }
            }
        });
    });
    // Thêm user
    socket.on('register', function ( name,email,confirmpass,password) {
        console.log(name + "register");
        var user = { name: name, email:email,confirmpass:confirmpass,password:password};
        collection.insert(user, function (err, result) {
            if (err) {
                console.log(err);
                socket.emit('register', false);
            } else {
                console.log('Inserted to users');
                socket.emit('register', true);

            }

        
        });
    });
       // update usermr
    socket.on('updateUser',function(_id, password,confirmpass,newpassword){
        console.log(password+ " updateUser");
         collection.update({_id:new mongodb.ObjectID(_id)},{$set:{password:password,confirmpass:confirmpass,newpassword:newpassword}}, function(err, result){
            if(err){
                console.log(err);
                socket.emit('updateUser', false);
            }else{
                    socket.emit('updateUser', true);

                }
            });
        });
});

