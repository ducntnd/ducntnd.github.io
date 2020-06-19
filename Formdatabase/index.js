const express=require("express")
const bodyParser = require("body-parser")
const MongoClient = require("mongodb").MongoClient
const app=express()

let db

MongoClient.connect("mongodb://localhost:27017",(err,client) => {
	if(err){
		return console.log(err)
	}
	db=client.db("local")
	console.log("Đã kết nối với database")
})

let quotes=["Code HTML","Style CSS","Đẩy code lên github"];

app.use(bodyParser.urlencoded({extended:true}))
app.use(express.static('public'));

app.set("view engine","ejs")

app.get("/", function(req,res){
	res.render("index.ejs");
})

app.get('/update:id',function(req,res){
	let id=req.params['id'];
	let objectId=require('mongodb').ObjectID;
	db.collection("newlocal").findOne({_id: new objectId(id)}).then(results =>{
			res.render("update.ejs",{update: results});
		}).catch(error =>{
			console.log(error)
		})
})

app.post("/",function(req,res){
	db.collection("newlocal").insertOne(req.body).then(results =>{
			console.log(results)
		}).catch(error =>{
			console.log(error)
		})

})

app.post("/delete",function(req,res){
	let objectId=require('mongodb').ObjectID;
	db.collection("newlocal").deleteOne(
		{_id: new objectId(req.body.ID)}
	).then(results =>{
		}).catch(error =>{
			console.log(error)
		})

})

app.post("/new-update",function(req,res){
	let objectId=require('mongodb').ObjectID;
	db.collection("newlocal").findOneAndUpdate(
		{_id: new objectId(req.body.ID)},
		{ $set: {name: req.body.name,phone: req.body.phone,email: req.body.email,tutor: req.body.tutor,rate:req.body.rate,attr:req.body.attr,
			comment:req.body.comment}}
	).then(results =>{
		}).catch(error =>{
			console.log(error)
		})

})

app.get("/table",function(req,res){
	let id=req.params['id'];
	db.collection("newlocal").find().toArray().then(results =>{
		res.render("table.ejs",{result : results})
		}).catch(error =>{
			console.log(error)
		})
})

app.listen(3000, function(){
	console.log("hello nodejs running on port 3000")
})