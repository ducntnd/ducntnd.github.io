const express=require("express")
// const MongoClient = require("mongodb").MongoClient
const bodyParser=require("body-parser");
const mysql=require('mysql')
const app=express()

// MongoClient.connect("mongodb://localhost:27017",(err,client) => {
// 	if(err){
// 		return console.log(err)
// 	}
// 	db=client.db("local")
// 	console.log("Đã kết nối với database")
// })

app.use(bodyParser.urlencoded({extended:true}))
app.use(express.static('public'));

let sql="Create"

app.set("view engine","ejs")

const conn=mysql.createConnection({
	host:'localhost',
	user:'root',
	password:'',
	database:'formdatabase'
})

conn.connect((err)=>{
	if (err) throw err;
	console.log("Success")
})

app.get("/", function(req,res){
	res.render("index.ejs");
})

app.get('/update:id',function(req,res){
	let id=req.params['id'];
	let ObjectID="Select * from newform1 where ID =" +id;
	conn.query(ObjectID,(err,results) => {
		if(err) {throw err};
		console.log(results)
		res.render("update.ejs",{update: results});
})
})

app.post("/",function(req,res){
	let sql="Insert into newform1(Ho_ten,SĐT,Email,Teacher,Rating,Advantage,Morerating) values('"+req.body.Ho_ten+"','"+req.body.SĐT+"','"+req.body.Email+"','"+req.body.Teacher+"',"+req.body.Rating+",'"+req.body.Advantage+"','"+req.body.Morerating +"')"
	conn.query(sql,(err,results)=>{
		if(err) throw err;
		res.redirect('/table');
	})
})

app.post("/delete",function(req,res){
	let id=req.body.ID;
	let deleteID = "Delete from newform1 where ID ="+id;  
	conn.query(deleteID,(err,results)=>{
		if(err) throw err;
		res.redirect('/table');
	})
})

app.post("/new-update",function(req,res){
	let updateId="Update newform1 Set Ho_ten='"+req.body.Ho_ten+"', SĐT='"+req.body.SĐT+"', Email='"+req.body.Email+"', Teacher='"+req.body.Teacher+"', Rating="+req.body.Rating+", Advantage='"+req.body.Advantage+"', Morerating='"+req.body.Morerating+"' where ID="+req.body.ID;
	conn.query(updateId,(err,results)=>{
		if(err) throw err
		res.redirect('/table');
	})
})

app.get("/table",function(req,res){
	let sql="Select * from newform1";
	conn.query(sql,(err,results)=>{
		if (err) throw err;
		res.render("table.ejs",{result: results})
	})
})

app.listen('3000', () => {
	console.log("hello nodejs running on port 3000")
	
})