const express=require('express');
const mysql = require('mysql');

const app=express();
const conn = mysql.createConnection({
    host    : 'localhost',
    user    : 'root',
    password: '',
    database: 'todoapp'
});

//hoặc
conn.connect((err)=> {
     //nếu có nỗi thì in ra
    if (err){ throw err;}
    //nếu thành công
    console.log('ket noi thanh cong');
});

app.get('/createdb',(req,res) =>{
	let sql="CREATE DATABASE todoapps";
	conn.query(sql,(err,result)=>{
		if(err) {throw err;}
		console.log(result);
		res.send("DATABASE created");
	})
})

app.get("/",function(req,res){
	let sql="Select * from film"
	conn.query(sql,(err,result)=>{
		if(err) {throw err;}
		console.log(result);
		res.send("information acquire");
	})
})

app.get("/insert",function(req,res){
	let sql="insert into film values(1,'Avengers','27/03/2012','Phim qua hay')"
	conn.query(sql,(err,result)=>{
		if(err) {throw err;}
		console.log(result);
		res.send("insert success");
	})
})

app.listen('3000',() =>{
	console.log('success 3000')
})

// conn.end((err) =>{
//     if (err) throw err;
//     console.log('dong ket noi thanh cong');
// });
