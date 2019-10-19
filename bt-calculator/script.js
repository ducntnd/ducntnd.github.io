	let number=""
	let status=""
	let result=""
	var str=""
	let bar=$("h2:eq(0)").text()
	bar=""
	let displayText=$("h2:eq(0)")
	displayText.text('')
	let i=0;

	$(".container").on("click",function(event){
		if(((result=="")&&(!isNaN($(event.target).text())))||(($(event.target).text()==".")&&(result==""))){
		bar+=$(event.target).text()
		displayText.text(bar)
		str+=$(event.target).text()
		if(i==2){
			displayText.html(str)
		}
		if(i!=2){
		i=1
	}
	}
})
	function notNumber(result){
			alert("Lỗi rồi")
			bar=""
			number=""
			displayText.html('')
			result=""
			str=""
			i=0
	}

	$("button:eq(0)").on("click",function(){
		displayText.text("")
		bar=""
		result=""
		str=""
		i=0
	})
	$("button:eq(1)").on("click",function(){
		if(i==1){
		 result=Math.pow(Number(bar),2)
		displayText.text(result);
		bar=result
		str=result
	}
	if(isNaN(result)){
			notNumber(result)
		}
	})
	
	$("button:eq(2)").on("click",function(){
		if(i==1){
		result=Number(bar)/100
		displayText.text(result);
		bar=result
		str=result
		}
		if(isNaN(result)){
			notNumber(result)
		}
	})
	$("button:eq(3)").on("click",function(){
		if(i==1){
			number=Number(bar)
			bar=""
			result=""
			status="divide"
			str+='&divide'
			displayText.html(str)
			i=2
		}
	})
	$("button:eq(7)").on("click",function(){
		if(i==1){
			number=Number(bar)
			bar=""
			result=""
			status="x"
			str+='x'
			displayText.html(str)
			i=2
		}
	})
	$("button:eq(11)").on("click",function(){
		if(i==1){
			number=Number(bar)
			bar=""
			result=""
			status="-"
			str+='-'
			displayText.html(str)
			i=2
		}
		if(i==0){
			bar+="-"
			displayText.html("-")
		}
	})
	$("button:eq(15)").on("click",function(){
		if(i==1){
			number=Number(bar)
			bar=""
			result=""
			status="+"
			str+='+'
			displayText.html(str)
			i=2
		}
	})

	$("button:eq(18)").on("click",function(){
				if(status=="divide"){
			 result=Number(number)/Number(bar)
			displayText.html(result)
			bar=result
			i=1
			status=""
			
		}
		if(status=="x"){
			 result=Number(number)*Number(bar)
			displayText.html(result)
			bar=result
			i=1
			status=""
			
		}
		if(status=="-"){
			 result=Number(number)-Number(bar)
			displayText.html(result)
			bar=result
			i=1
			status=""
			
		}
 		if(status=="+"){
			 result=Number(number)+Number(bar)
			displayText.html(result)
			bar=result
			i=1
			status=""	
		}
		if(status==""){
			result=Number(bar)
				displayText.html(result)
				i=1
		}
		str=result
		if(isNaN(result)){
			notNumber(result)
		}
	})