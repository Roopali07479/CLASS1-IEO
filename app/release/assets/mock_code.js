var pos = 0, wrongpos=0,wrongcount=0, wrongcountrepeat=0,test, test_status, question, choice, choices, chA, chB, chC,chD, correct = 0;
var wrongquestion=new Array(5);
var wrongchoice=new Array(5);
var wron_ques = [
				["",""],
];

function _(x){
	return document.getElementById(x);
}

if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
//xmlhttp.open("GET","science.xml",false);


<!--xmlhttp.send();-->
<!--xmlDoc=xmlhttp.responseXML;-->
<!--var x=xmlDoc.getElementsByTagName("CD");-->

// Set the date we're counting down to
	var d = new Date();
	var countDownDate = new Date();
	countDownDate.setMinutes(d.getMinutes());
	countDownDate.setSeconds(d.getSeconds());

// Update the count down every 1 second
    var x = setInterval(function() {
    // Get todays date and time
    var now = new Date();
    //var countDownDate = new Date("Jan 5, 2018 15:37:25").getTime();
	//var countDownDate = new Date().getTime();
    // Find the distance between now an the count down date
    var distance = now - countDownDate;
	 
    // Time calculations for days, hours, minutes and seconds
    var days = Math.floor(distance / (1000 * 60 * 60 * 24));
    var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((distance % (1000 * 60)) / 1000);
        // Output the result in an element with id="demo"
    document.getElementById("demo").innerHTML = +minutes + " mins " + seconds + " secs";
        // If the count down is over, write some text 
    if (distance < 0) {
        clearInterval(x);
        document.getElementById("demo").innerHTML = "EXPIRED";
    }
}, 1000);


 		var startTime = new Date();
		var sMinutes = startTime.getMinutes();
		sMinutes = parseInt(sMinutes,10)
		
		
function renderQuestion(){
	var flag;
	test = _("test");
		var endTime = new Date();
		var eMinutes = endTime.getMinutes();
		eMinutes = parseInt(eMinutes,10)
		var timeTaken = (eMinutes- sMinutes);
		
	if(pos >= questions.length){
		test.innerHTML = "<h2 class='you-got'>You got <span class='ans'>"+correct+"</span> of <span class='length'>"+questions.length+"</span> questions correct.</h2>";
		_("test_status").innerHTML = "<BR>Test Completed in <u>" + timeTaken +" Minutes </u><br><br>";
 
		generate_table(correct,questions.length );

		if (correct==questions.length)
		{
		}
		else
		{

		 test.innerHTML +="<BR>";
		test.innerHTML += "<button onclick='WrongAnswer()' id='visit_return_button' class='review-text-btn'>Review</button>";
		
		}
		pos = 0;
		correct = 0;
		return false;
	}
	_("test_status").innerHTML = "Question "+(pos+1)+" of "+questions.length;
	question = questions[pos][0];
	chA =questions[pos][1];
	chB = questions[pos][2];
	chC = questions[pos][3];
	chD = questions[pos][4];
	
	test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
	test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A'><span  id = 'A'  onclick='SelectAnswer(this);' >"+chA+"</span></div>";
	test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B'><span  id = 'B'  onclick='SelectAnswer(this);' >"+chB+"</span></div>";
	test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C'  ><span id = 'C'  onclick='SelectAnswer(this);'>"+chC+"</span></div>";
	test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D'  ><span  id = 'D' onclick='SelectAnswer(this);'>"+chD+"</span></div>";

	
	test.innerHTML += "<div class='footer-btn'><button onclick='checkAnswer()' id='visit_return_button' class='next-btn' >Submit Answer</button><br class='cl'/></div>";
}
function renderPreviousQuestion(){
var ID;

	if(pos == 0)
	{
	pos=pos;
	}
	else{
		pos--;
	}
	test = _("test");
	if(pos >= questions.length){
		test.innerHTML = "<h2 class='you-got'>You got <span class='ans'>"+correct+"</span> of <span class='length'>"+questions.length+"</span> questions correct</h2>";
		_("test_status").innerHTML = "Test Completed";
		pos = 0;
		correct = 0;

		return false;

	}
	_("test_status").innerHTML = "Question "+(pos+1)+" of "+questions.length;
	question = questions[pos][0];
	chA =questions[pos][1];
	chB = questions[pos][2];
	chC = questions[pos][3];
	chD = questions[pos][4];
	right =questions[pos][5];
    wrong = questions[pos][6];
//alert(wrong);

	test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
	if (wrong=="A")
	{
test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A'  checked='checked'><span  id = 'A'  onclick='SelectAnswer(this);' style='color: black; cursor: pointer'>"+chA+"</span></div>";
	test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' ><span  id = 'B'  onclick='SelectAnswer(this);' style='color: black; cursor: pointer'>"+chB+"</span></div>";
	test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C'  ><span id = 'C'  onclick='SelectAnswer(this);' style='color: black; cursor: pointer'>"+chC+"</span></div>";
	test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D'  ><span  id = 'D' onclick='SelectAnswer(this);' style='color: black; cursor: pointer'>"+chD+"</span></div>";
	}else if (wrong=="B")
		{
test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A'><span  id = 'A'  onclick='SelectAnswer(this);' style='color: black; cursor: pointer'>"+chA+"</span></div>";
	test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B'  checked='checked' ><span  id = 'B'  onclick='SelectAnswer(this);' style='color: black; cursor: pointer'>"+chB+"</span></div>";
	test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C'  ><span id = 'C'  onclick='SelectAnswer(this);' style='color: black; cursor: pointer'>"+chC+"</span></div>";
	test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D'  ><span  id = 'D' onclick='SelectAnswer(this);' style='color: black; cursor: pointer'>"+chD+"</span></div>";
	}else if (wrong=="C")
		{
test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A'><span  id = 'A'  onclick='SelectAnswer(this);' style='color: black; cursor: pointer'>"+chA+"</span></div>";
	test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' ><span  id = 'B'  onclick='SelectAnswer(this);' style='color: black; cursor: pointer'>"+chB+"</span></div>";
	test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C'  checked='checked'  ><span id = 'C'  onclick='SelectAnswer(this);' style='color: black; cursor: pointer'>"+chC+"</span></div>";
	test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D'  ><span  id = 'D' onclick='SelectAnswer(this);' style='color: black; cursor: pointer'>"+chD+"</span></div>";
	}else if (wrong=="D")
		{
test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A'><span  id = 'A'  onclick='SelectAnswer(this);' style='color: black; cursor: pointer'>"+chA+"</span></div>";
	test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' ><span  id = 'B'  onclick='SelectAnswer(this);' style='color: black; cursor: pointer'>"+chB+"</span></div>";
	test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C'  ><span id = 'C'  onclick='SelectAnswer(this);' style='color: black; cursor: pointer'>"+chC+"</span></div>";
	test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D'  checked='checked' ><span  id = 'D' onclick='SelectAnswer(this);' style='color: black; cursor: pointer'>"+chD+"</span></div>";
	}
		
		
		
	test.innerHTML += "<div class='footer-btn'><button onclick='renderPreviousQuestion()' id='back_button' class='back-btn'>&larr; Back</button> <button class='next-btn' onclick='checkAnswer()' id='visit_return_button' >Submit Answer</button><br  class='cl' /></div>";
}
function generate_table( correct,  total){
if (total ==0)
{
}
else
	{
	var table = document.createElement('table');
	table.setAttribute('border','1');
table.setAttribute('class','result-table');
	table.setAttribute('width','100%')
table.setAttribute('cellpadding','0')
table.setAttribute('border','1')
	var row = table.insertRow(0);
	wrong=total-correct;
	for (i=1;i<=2 ;i++ )
	{
		var row = table.insertRow(0);

	for(j=1; j<=3; j++){
		//var text = document.createTextNode(String.fromCharCode(j+64));
		var cell = row.insertCell(j-1);
		cell.setAttribute('align','center')
		//cell.appendChild(text);

	  if (i==2)
	  {

		  if(j==1)
		   var cellText = document.createTextNode("Total");
		  if(j==2)
		   var cellText = document.createTextNode("Correct");
		  if(j==3)
		   var cellText = document.createTextNode("Wrong");
	  }
	  		//cell.appendChild(cellText);

	  if (i==1)
	  {
		  if(j==1)
		   var cellText = document.createTextNode(total);
		  if(j==2)
		   var cellText = document.createTextNode(correct);
		  if(j==3)
		   var cellText = document.createTextNode(wrong);
	  }
	  		cell.appendChild(cellText);
	}
	}
	}
document.getElementById("test_status").appendChild(table);
}



function checkAnswer(){
if(pos<questions.length)
{
	choices = document.getElementsByName("choices");
	choice='';
	for(var i=0; i<choices.length; i++){
		if(choices[i].checked){
			choice = choices[i].value;
			questions[pos][6]=choices[i].value;
		}
	}
	if(choice!='')
	{
			if(choice == questions[pos][5]){
				correct++;
			}
			else{
			 wrongquestion[wrongcount]=wrongpos;
			 wrongchoice[wrongcount]=choice;
			 wrongcount++;

			}
			pos++;
			wrongpos++;

			renderQuestion();
	}
	else alert ('Select option');
	
	}
}
function WrongAnswer(){

	if(wrongcountrepeat<wrongcount)
{
	var right, wrong;
    var choice = wrongcountrepeat;
	pos=wrongquestion[wrongcountrepeat];
	wrongcountrepeat++;
	question = questions[pos][0];

    chA =questions[pos][1];
	chB = questions[pos][2];
	chC = questions[pos][3];
	chD = questions[pos][4];
	
	test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
right = questions[pos][5];
wrong = wrongchoice[choice];

if(right=="A")
		{

			if(wrong =="B")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='A'  id='A' ><span> "+chA+"</span></div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='B' id='B' checked='checked'><span> "+chB+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C'id='C'> "+chC+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' id='D'> "+chD+"</div>";
				}
				else if(wrong =="C")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='A'  id='A' ><span> "+chA+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' id='B'>"+chB+"</div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='C'id='C' checked='checked'> <span> "+chC+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' id='D'> "+chD+"</div>";
				}
				else if(wrong =="D")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='A'  id='A' ><span> "+chA+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' id='B'>"+chB+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C'id='C'>  "+chC+"</div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='D' id='D' checked='checked'><span> "+chD+"</span></div>";
				}
		}

else if(right=="B")
		{

			if(wrong =="A")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='A'id='A' checked='checked'> <span>"+chA+"</div>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='B' id='B' ><span> "+chB+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' id='C'> "+chC+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' id='D'> "+chD+"</div>";
				}
				else if(wrong =="C")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A'  id='A' >"+chA+"</div>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='B' id='B'><span> "+chB+"</span></div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='C'id='C' checked='checked'> <span> "+chC+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' id='D'> "+chD+"</div>";
				}
				else if(wrong =="D")
				{

				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A'  id='A' > "+chA+"</div>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='B' id='B'><span> "+chB+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C'id='C'>  "+chC+"</div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='D' id='D' checked='checked'><span> "+chD+"</span></div>";
				}
		}
else if(right=="C")
		{
			if(wrong =="A")
				{
					test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='A'id='A' checked='checked'> <span>"+chA+"</div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' id='B' > "+chB+"</div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='C' id='C' ><span> "+chC+"</span></div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' id='D'> "+chD+"</div>";
				}
				else if(wrong =="B")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A' id='A'> "+chA+"</div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='B' id='B' checked='checked'><span> "+chB+"</span></div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='C' id='C' ><span> "+chC+"</span></div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' id='D'> "+chD+"</div>";
				}
				else if(wrong =="D")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A' id='A'> "+chA+"</div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' id='B' > "+chB+"</div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='C' id='C' ><span> "+chC+"</span></div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='D' id='D' checked='checked'><span> "+chD+"</span></div>";
				}
		}
else if (right=="D")
		{
			if(wrong =="A")
				{
					test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
					test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='A'id='A' checked='checked'> <span>"+chA+"</div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' id='B'  > "+chB+"</div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' id='C' > "+chC+"</div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='D'  id='D' ><span> "+chD+"</span></div>";
				}
				else if(wrong =="B")
			{
					test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A' id='A'> "+chA+"</div>";
					test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='B' id='B' checked='checked'><span> "+chB+"</span></div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' id='C' > "+chC+"</div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='D'  id='D' ><span> "+chD+"</span></div>";
					}
				else if(wrong =="C")
				{
					test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A' id='A'> "+chA+"</div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' id='B'  > "+chB+"</div>";
					test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='C'id='C' checked='checked'> <span> "+chC+"</span></div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='D'  id='D' ><span> "+chD+"</span></div>";
				}
		}
	
	test.innerHTML += "<div class='footer-btn'><button onclick='WrongAnswer()' id='visit_return_button' class='next-btn' >Next &rarr;</button><br class='cl' /></div>";
	    generate_table(0,0);
}
	else
	 document.write("<b>Test completed</b>");
	 
}

function SelectAnswer(object){
	choices = document.getElementsByName("choices");

    if(object.id =="A"){
		choices[0].checked = true;
		
    }
	if(object.id =="B"){
		choices[1].checked = true;
    }
	if(object.id =="C"){
		choices[2].checked = true;
    }
	if(object.id =="D"){
		choices[3].checked = true;
    }
	questions[pos][6] = object.id;

}
window.addEventListener("load", renderQuestion, false);
