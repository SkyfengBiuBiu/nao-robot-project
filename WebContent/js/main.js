/**
 * 
 */

var order=1;
var color=1;
var led=1;
var time=1;
var textToSpeech="";

 //get the target option from html
document.onkeydown=function(event){
  var e = event || window.event || arguments.callee.caller.arguments[0];
  if(e && e.keyCode==37){
   test1();
   }
  if(e && e.keyCode==38){ 
     test2();
    }      
   if(e && e.keyCode==39){
     test3();
  }
  if(e && e.keyCode==40){ 
    test4();
  }
  if(e && e.keyCode==32){ 
    test5();
  }
}; 

$(function(){
			$().timelinr({
				arrowKeys: 'true'
			})
		});

//walk left
function test1() {
	order=1;
	sendData();
}

//walk forard
function test2() {
	order=2;
	sendData();
}

//walk right
function test3() {
	order=3;
	sendData();
}

//walk back
function test4() {
	order=4;
	sendData();
}

//stop walking
function test5() {
	order=5;
	sendData();
}

//detect the face
function test6() {
	order=6;
	sendData();
}


//set time for LED
function test9(){
      time=0.5;
}
function test10(){
      time=1.0;
}
function test11(){
      time=1.5;
}
function test12(){
      time=2.0;
}
function test13(){
      time=2.5;
}
function test14(){
      time=3.0;
}
function test15(){
      time=3.5;
}
function test16(){
      time=4.0;
}
function test17(){
      time=4.5;
}
function test18(){
      time=5.0;
}
//Launch LED according to selected color and type of LED
function test19(){
	order=7;
	var options=$("#seC option:selected");
	var options1=$("#seL option:selected");
	color=options.val(); 
	led=options1.val();
	sendData();
}

//Speech according to the textArea
function test20(){
	order=8;
	textToSpeech = ("Value:" + $("#d1").val());
    sendData();
}

//look forward
function test21(){
	order=9;
	sendData();
}

//look down
function test22(){
	order=10;
	sendData();
}

//look around
function test23(){
	order=11;
	sendData();
}


//stand
function test27(){
	order=12;
	sendData();
}

//rest
function test26(){
	order=13;
	sendData();
}

//move arm
function test25(){
	order=14;
	sendData();
}

//say hello
function test24(){
	order=15;
	sendData();
}

//reactToEvent
function test30(){
	order=16;
	sendData();
}

function sendData(){
	var params = {};
	 params["caseNO"] = order;
	 params["colorNO"]=color;
	 params["ledNO"]=led;
	 params["time"]=time;
	 params["text"]=textToSpeech;
	$.ajax({
		url : "walkAhead.do",
		type : "GET",
		data: params,
		dataType : "xml",
		success : function(returnValue) {
		var provinceId;
			$(returnValue).find("root").each(function(){                           
                provinceId=$(this).children("yesNum").text();  
                });
			console.log("test"+order+provinceId);
		},
		error : function(request, error) {
			//alert("An error occurred attempting to get new command");
			console.log("An error occurred attempting to get new command");
		}
	})
}











