var pos = 0, wrongpos=0,wrongcount=0, wrongcountrepeat=0,test,CorrectAnswer, test_status, question, choice, choices, chA, chB, chC,chD, correct = 0;
var wrongquestion=new Array(5);
var wrongchoice=new Array(5);
 var seconds =0, minutes =0;
 var stopwatch = false;
 
 var wron_ques = [
				["",""],
];
window.onload = function() {
        what();
        function what() {
            document.getElementById('demo').innerHTML = 'Start';
        };
    }
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

if (stopwatch ==true)
{
	return;
}
    // Get todays date and time
    var now = new Date();
    var distance = now - countDownDate;
	
    // Time calculations for days, hours, minutes and seconds
    var days = Math.floor(distance / (1000 * 60 * 60 * 24));
    var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
     minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
     seconds = Math.floor((distance % (1000 * 60)) / 1000);
        // Output the result in an element with id="demo"
		//showtime= = "<span>" + minutes + "</span>" + " mins " + "<span>" + seconds + "</span>" + " secs";
       document.getElementById("demo").innerHTML = "<span>" + minutes + "</span>" + " mins " + "<span>" + seconds + "</span>" + " secs";
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
	if(pos >= questions.length){
		var endTime = new Date();
		var eMinutes = endTime.getMinutes();
		eMinutes = parseInt(eMinutes,10)
		var timeTaken = (eMinutes- sMinutes);
		test.innerHTML = "<h2 class='you-got'>You got <span class='ans'>"+correct+"</span> of <span class='length'>"+questions.length+"</span> questions correct.</h2>";
		_("test_status").innerHTML = "<BR>Test Completed in <U>" + timeTaken +" Minutes </U><br><br>";
		
		generate_table(correct,questions.length );
		if (correct==questions.length)
		{
		}
		else
		{

		test.innerHTML +="<BR>";
		test.innerHTML += "<button onclick='WrongAnswer()' id='visit_return_button' class='review-text-btn'>Review</button>";
		test.innerHTML +="<BR>";
		
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
if(questions[pos][6]== undefined){
	test.innerHTML = "<h3 class='ques'>"+question+"</h3>";

	test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A' onclick='checkAnswer(this);'><span  id = 'A'  onclick='checkAnswer(this);' >"+chA+"</span></div>";
	test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B'  onclick='checkAnswer(this);' ><span  id = 'B'  onclick='checkAnswer(this);' >"+chB+"</span></div>";
	test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' onclick='checkAnswer(this);' ><span id = 'C'  onclick='checkAnswer(this);' >"+chC+"</span></div>";
	test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' onclick='checkAnswer(this);' ><span  id = 'D' onclick='checkAnswer(this);' >"+chD+"</span></div>";

	test.innerHTML += "<div class='footer-btn'><button onclick='renderPreviousQuestion()' id='visit_return_button' class='back-btn'>&larr; Back</button> <button onclick='renderNextQuestion()' disabled  id='visit_return_button' class='next-btn'>Next &rarr; </button><br class='cl' /></div>";	
}
else
{
	right =questions[pos][5];
wrong = questions[pos][6];

if(right=="A")
		{

			if(wrong =="B")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='A'onclick='clickAction(this);'  id='A' ><span  >"+chA+"</span></div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='B'  onclick='clickAction(this);' id='B' checked='checked'><span>"+chB+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices'  onclick='clickAction(this);' value='C' id='C'>"+chC+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' onclick='clickAction(this);' value='D' id='D'>"+chD+"</div>";
				}
				else if(wrong =="C")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='A' onclick='clickAction(this);'  id='A' ><span>"+chA+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' id='B' onclick='clickAction(this);' >"+chB+"</div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' onclick='clickAction(this);' value='C' id='C' checked='checked'><span>"+chC+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D'onclick='clickAction(this);' id='D'>"+chD+"</div>";
				}
				else if(wrong =="D")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices'onclick='clickAction(this);'  value='A'  id='A' ><span>"+chA+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' onclick='clickAction(this);'   id='B'>"+chB+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' onclick='clickAction(this);' id='C'>"+chC+"</div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' onclick='clickAction(this);' value='D' id='D' checked='checked'><span>"+chD+"</span></div>";
				}
				CorrectAnswer=chA;
		}

else if(right=="B")
		{

			if(wrong =="A")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='A' id='A' checked='checked'><span>"+chA+"</div>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='B' id='B' ><span>"+chB+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' onclick='clickAction(this);'  id='C'>"+chC+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' onclick='clickAction(this);'  id='D'>"+chD+"</div>";
				}
				else if(wrong =="C")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A'   onclick='clickAction(this);' id='A' >"+chA+"</div>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices'  onclick='clickAction(this);' value='B' id='B'><span>"+chB+"</span></div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices'  onclick='clickAction(this);' value='C' id='C' checked='checked'><span>"+chC+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D'  onclick='clickAction(this);' id='D'>"+chD+"</div>";
				}
				else if(wrong =="D")
				{

				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A' onclick='clickAction(this);'   id='A' >"+chA+"</div>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='B' id='B'><span>"+chB+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' onclick='clickAction(this);'  id='C'>"+chC+"</div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='D' id='D' checked='checked'><span>"+chD+"</span></div>";
				}
				CorrectAnswer=chB;
		}
else if(right=="C")
		{
			if (right!=wrong)
			{

			if(wrong =="A")
				{
					test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='A' id='A' checked='checked'><span>"+chA+"</div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B'  onclick='clickAction(this);'  id='B' >"+chB+"</div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='C' id='C' ><span>"+chC+"</span></div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' onclick='clickAction(this);'  id='D'>"+chD+"</div>";
				}
				else if(wrong =="B")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A'  onclick='clickAction(this);' id='A'>"+chA+"</div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices'  onclick='clickAction(this);' value='B' id='B' checked='checked'><span>"+chB+"</span></div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='C' id='C' ><span>"+chC+"</span></div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' onclick='clickAction(this);'  id='D'>"+chD+"</div>";
				}
				else if(wrong =="D")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A'  onclick='clickAction(this);' id='A'>"+chA+"</div>";
				 test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' onclick='clickAction(this);'  id='B' >"+chB+"</div>";
				 test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='C' id='C' ><span>"+chC+"</span></div>";
				 test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='D' id='D' checked='checked'><span>"+chD+"</span></div>";
				}
				}

CorrectAnswer=chC;
		}
else if (right=="D")
		{
			if(wrong =="A")
				{
					test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
					test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='A' id='A' checked='checked'><span>"+chA+"</div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' onclick='clickAction(this);'  id='B'>"+chB+"</div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' onclick='clickAction(this);'  id='C' >"+chC+"</div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='D'  id='D' ><span>"+chD+"</span></div>";
				}
				else if(wrong =="B")
			{
					test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A' onclick='clickAction(this);'  id='A'>"+chA+"</div>";
					test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='B' id='B' checked='checked'><span>"+chB+"</span></div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' onclick='clickAction(this);'  id='C' >"+chC+"</div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='D'  id='D' ><span>"+chD+"</span></div>";
					}
				else if(wrong =="C")
				{
					test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A' onclick='clickAction(this);'  id='A'>"+chA+"</div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' onclick='clickAction(this);'  id='B'>"+chB+"</div>";
					test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices'  onclick='clickAction(this);' value='C' id='C' checked='checked'><span>"+chC+"</span></div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices'  onclick='clickAction(this);' value='D'  id='D' ><span>"+chD+"</span></div>";
				}
				CorrectAnswer=chD;
		}

		if (right==wrong)
{
	
				if(right =="A")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row right-ans'><input type='radio' name='choices' value='A' onclick='clickAction(this);'   id='A' checked='checked'><span>"+chA+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' onclick='clickAction(this);'  id='B' >"+chB+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' onclick='clickAction(this);'  id='C'>"+chC+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' onclick='clickAction(this);'  id='D'>"+chD+"</div>";
				CorrectAnswer=chA;
				}
				if(right =="B")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A' onclick='clickAction(this);'   id='A' >"+chA+"</div>";
				test.innerHTML += "<div class='answer-row right-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='B' id='B' checked='checked'><span>"+chB+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' onclick='clickAction(this);'  id='C'>"+chC+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' onclick='clickAction(this);'  id='D'>"+chD+"</div>";
				CorrectAnswer=chB;
				}
				else if(right =="C")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A' onclick='clickAction(this);'   id='A' >"+chA+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' onclick='clickAction(this);'  id='B'>"+chB+"</div>";
				test.innerHTML += "<div class='answer-row right-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='C' id='C' checked='checked'><span>"+chC+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' onclick='clickAction(this);'  id='D'>"+chD+"</div>";
				CorrectAnswer=chC;
				}
				else if(right =="D")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A'  onclick='clickAction(this);'  id='A' >"+chA+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B'  onclick='clickAction(this);' id='B'>"+chB+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C'  onclick='clickAction(this);' id='C'>"+chC+"</div>";
				test.innerHTML += "<div class='answer-row right-ans'><input type='radio' name='choices'  onclick='clickAction(this);' value='D' id='D' checked='checked'><span>"+chD+"</span></div>";
				CorrectAnswer=chD;
				}
}

	test.innerHTML += "<div class='footer-btn'><button onclick='renderPreviousQuestion()'  id='visit_return_button'  class='back-btn'>&larr; Back</button><button onclick='renderNextQuestion()'  id='visit_return_button' class='next-btn'>Next &rarr;</button><br class='cl' /></div>";

if (right==wrong)
{
	test.innerHTML += "<div class='solution-row'><h4>You are Right</h4><p> Correct answer is  : " + CorrectAnswer +"</p>";
}
else
{
		test.innerHTML += "<div class='solution-row'><h4>Solution </h4><p> Correct Answer is : <strong> " + CorrectAnswer+"</strong></p></div>";
	
}

	
	
	
}
}
function renderPreviousQuestion(){
var ID;

	if(pos == 0)
	{
	pos=pos;
	}
	else{
		pos=pos-1;

	}
	test = _("test");
	if(pos >= questions.length){
		test.innerHTML = "<h2>You got "+correct+" of "+questions.length+" questions correct</h2>";
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

if(right=="A")
		{

			if(wrong =="B")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='A'onclick='clickAction(this);'  id='A' ><span  >"+chA+"</span></div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='B'  onclick='clickAction(this);' id='B' checked='checked'><span>"+chB+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices'  onclick='clickAction(this);' value='C' id='C'>"+chC+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' onclick='clickAction(this);' value='D' id='D'>"+chD+"</div>";
				}
				else if(wrong =="C")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='A' onclick='clickAction(this);'  id='A' ><span>"+chA+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' id='B' onclick='clickAction(this);' >"+chB+"</div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' onclick='clickAction(this);' value='C' id='C' checked='checked'><span>"+chC+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D'onclick='clickAction(this);' id='D'>"+chD+"</div>";
				}
				else if(wrong =="D")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices'onclick='clickAction(this);'  value='A'  id='A' ><span>"+chA+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' onclick='clickAction(this);'   id='B'>"+chB+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' onclick='clickAction(this);' id='C'>"+chC+"</div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' onclick='clickAction(this);' value='D' id='D' checked='checked'><span>"+chD+"</span></div>";
				}
				CorrectAnswer=chA;
		}

else if(right=="B")
		{

			if(wrong =="A")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='A' id='A' checked='checked'><span>"+chA+"</div>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='B' id='B' ><span>"+chB+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' onclick='clickAction(this);'  id='C'>"+chC+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' onclick='clickAction(this);'  id='D'>"+chD+"</div>";
				}
				else if(wrong =="C")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A'   onclick='clickAction(this);' id='A' >"+chA+"</div>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices'  onclick='clickAction(this);' value='B' id='B'><span>"+chB+"</span></div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices'  onclick='clickAction(this);' value='C' id='C' checked='checked'><span>"+chC+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D'  onclick='clickAction(this);' id='D'>"+chD+"</div>";
				}
				else if(wrong =="D")
				{

				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A' onclick='clickAction(this);'   id='A' >"+chA+"</div>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='B' id='B'><span>"+chB+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' onclick='clickAction(this);'  id='C'>"+chC+"</div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='D' id='D' checked='checked'><span>"+chD+"</span></div>";
				}
				CorrectAnswer=chB;
		}
else if(right=="C")
		{
			if (right!=wrong)
			{

			if(wrong =="A")
				{
					test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='A' id='A' checked='checked'><span>"+chA+"</div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B'  onclick='clickAction(this);'  id='B' >"+chB+"</div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='C' id='C' ><span>"+chC+"</span></div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' onclick='clickAction(this);'  id='D'>"+chD+"</div>";
				}
				else if(wrong =="B")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A'  onclick='clickAction(this);' id='A'>"+chA+"</div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices'  onclick='clickAction(this);' value='B' id='B' checked='checked'><span>"+chB+"</span></div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='C' id='C' ><span>"+chC+"</span></div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' onclick='clickAction(this);'  id='D'>"+chD+"</div>";
				}
				else if(wrong =="D")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A'  onclick='clickAction(this);' id='A'>"+chA+"</div>";
				 test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' onclick='clickAction(this);'  id='B' >"+chB+"</div>";
				 test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='C' id='C' ><span>"+chC+"</span></div>";
				 test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='D' id='D' checked='checked'><span>"+chD+"</span></div>";
				}
				}

CorrectAnswer=chC;
		}
else if (right=="D")
		{
			if(wrong =="A")
				{
					test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
					test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='A' id='A' checked='checked'><span>"+chA+"</div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' onclick='clickAction(this);'  id='B'>"+chB+"</div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' onclick='clickAction(this);'  id='C' >"+chC+"</div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='D'  id='D' ><span>"+chD+"</span></div>";
				}
				else if(wrong =="B")
			{
					test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A' onclick='clickAction(this);'  id='A'>"+chA+"</div>";
					test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='B' id='B' checked='checked'><span>"+chB+"</span></div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' onclick='clickAction(this);'  id='C' >"+chC+"</div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='D'  id='D' ><span>"+chD+"</span></div>";
					}
				else if(wrong =="C")
				{
					test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A' onclick='clickAction(this);'  id='A'>"+chA+"</div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' onclick='clickAction(this);'  id='B'>"+chB+"</div>";
					test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices'  onclick='clickAction(this);' value='C' id='C' checked='checked'><span>"+chC+"</span></div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices'  onclick='clickAction(this);' value='D'  id='D' ><span>"+chD+"</span></div>";
				}
				CorrectAnswer=chD;
		}

		if (right==wrong)
{
	
				if(right =="A")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row right-ans'><input type='radio' name='choices' value='A' onclick='clickAction(this);'   id='A' checked='checked'><span>"+chA+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' onclick='clickAction(this);'  id='B' >"+chB+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' onclick='clickAction(this);'  id='C'>"+chC+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' onclick='clickAction(this);'  id='D'>"+chD+"</div>";
				CorrectAnswer=chA;
				}
				if(right =="B")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A' onclick='clickAction(this);'   id='A' >"+chA+"</div>";
				test.innerHTML += "<div class='answer-row right-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='B' id='B' checked='checked'><span>"+chB+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' onclick='clickAction(this);'  id='C'>"+chC+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' onclick='clickAction(this);'  id='D'>"+chD+"</div>";
				CorrectAnswer=chB;
				}
				else if(right =="C")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A' onclick='clickAction(this);'   id='A' >"+chA+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' onclick='clickAction(this);'  id='B'>"+chB+"</div>";
				test.innerHTML += "<div class='answer-row right-ans'><input type='radio' name='choices' onclick='clickAction(this);'  value='C' id='C' checked='checked'><span>"+chC+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' onclick='clickAction(this);'  id='D'>"+chD+"</div>";
				CorrectAnswer=chC;
				}
				else if(right =="D")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A'  onclick='clickAction(this);'  id='A' >"+chA+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B'  onclick='clickAction(this);' id='B'>"+chB+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C'  onclick='clickAction(this);' id='C'>"+chC+"</div>";
				test.innerHTML += "<div class='answer-row right-ans'><input type='radio' name='choices'  onclick='clickAction(this);' value='D' id='D' checked='checked'><span>"+chD+"</span></div>";
				CorrectAnswer=chD;
				}
}

	test.innerHTML += "<div class='footer-btn'><button onclick='renderPreviousQuestion()'  id='visit_return_button'  class='back-btn'>&larr; Back</button><button onclick='renderNextQuestion()'  id='visit_return_button' class='next-btn'>Next &rarr;</button><br class='cl' /></div>";

if (right==wrong)
{
	test.innerHTML += "<div class='solution-row'><h4>You are Right</h4><p> Correct answer is  : " + CorrectAnswer +"</p>";
}
else
{
		test.innerHTML += "<div class='solution-row'><h4>Solution </h4><p> Correct Answer is : <strong> " + CorrectAnswer+"</strong></p></div>";
	
}


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


function checkAnswer(object){
	choices = document.getElementsByName("choices");
document.getElementsByName("visit_return_button").disabled = true;

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

if(pos<questions.length)
{

	for(var i=0; i<choices.length; i++){
		if(choices[i].checked){
			choice = choices[i].value;
		}
	}
	if(choice == questions[pos][5]){
		correct++;
	}
	else{
	 wrongquestion[wrongcount]=wrongpos;
	 wrongchoice[wrongcount]=choice;
	 wrongcount++;
	}

right =questions[pos][5];
wrong = choice;
questions[pos][6] = wrong;
if(right=="A")
		{

			if(wrong =="B")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='A'  id='A' ><span  >"+chA+"</span></div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='B' id='B' checked='checked'><span>"+chB+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' id='C'>"+chC+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' id='D'>"+chD+"</div>";
				}
				else if(wrong =="C")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='A'  id='A' ><span>"+chA+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' id='B'>"+chB+"</div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='C' id='C' checked='checked'><span>"+chC+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' id='D'>"+chD+"</div>";
				}
				else if(wrong =="D")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='A'  id='A' ><span>"+chA+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' id='B'>"+chB+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' id='C'>"+chC+"</div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='D' id='D' checked='checked'><span>"+chD+"</span></div>";
				}
				CorrectAnswer=chA;
		}

else if(right=="B")
		{

			if(wrong =="A")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='A' id='A' checked='checked'><span>"+chA+"</div>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='B' id='B' ><span>"+chB+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' id='C'>"+chC+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' id='D'>"+chD+"</div>";
				}
				else if(wrong =="C")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A'  id='A' >"+chA+"</div>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='B' id='B'><span>"+chB+"</span></div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='C' id='C' checked='checked'><span>"+chC+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' id='D'>"+chD+"</div>";
				}
				else if(wrong =="D")
				{

				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A'  id='A' >"+chA+"</div>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='B' id='B'><span>"+chB+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' id='C'>"+chC+"</div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='D' id='D' checked='checked'><span>"+chD+"</span></div>";
				}
				CorrectAnswer=chB;
		}
else if(right=="C")
		{
			if (right!=wrong)
			{

			if(wrong =="A")
				{
					test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='A' id='A' checked='checked'><span>"+chA+"</div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' id='B' >"+chB+"</div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='C' id='C' ><span>"+chC+"</span></div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' id='D'>"+chD+"</div>";
				}
				else if(wrong =="B")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A' id='A'>"+chA+"</div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='B' id='B' checked='checked'><span>"+chB+"</span></div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='C' id='C' ><span>"+chC+"</span></div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' id='D'>"+chD+"</div>";
				}
				else if(wrong =="D")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A' id='A'>"+chA+"</div>";
				 test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' id='B' >"+chB+"</div>";
				 test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='C' id='C' ><span>"+chC+"</span></div>";
				 test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='D' id='D' checked='checked'><span>"+chD+"</span></div>";
				}
				}

CorrectAnswer=chC;
		}
else if (right=="D")
		{
			if(wrong =="A")
				{
					test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
					test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='A' id='A' checked='checked'><span>"+chA+"</div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' id='B'>"+chB+"</div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' id='C' >"+chC+"</div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='D'  id='D' ><span>"+chD+"</span></div>";
				}
				else if(wrong =="B")
			{
					test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A' id='A'>"+chA+"</div>";
					test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='B' id='B' checked='checked'><span>"+chB+"</span></div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' id='C' >"+chC+"</div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='D'  id='D' ><span>"+chD+"</span></div>";
					}
				else if(wrong =="C")
				{
					test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A' id='A'>"+chA+"</div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' id='B'>"+chB+"</div>";
					test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='C' id='C' checked='checked'><span>"+chC+"</span></div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='D'  id='D' ><span>"+chD+"</span></div>";
				}
				CorrectAnswer=chD;
		}

		if (right==wrong)
{
	
				if(right =="A")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row right-ans'><input type='radio' name='choices' value='A'  id='A' checked='checked'><span>"+chA+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' id='B' >"+chB+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' id='C'>"+chC+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' id='D'>"+chD+"</div>";
				CorrectAnswer=chA;
				}
				if(right =="B")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A'  id='A' >"+chA+"</div>";
				test.innerHTML += "<div class='answer-row right-ans'><input type='radio' name='choices' value='B' id='B' checked='checked'><span>"+chB+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' id='C'>"+chC+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' id='D'>"+chD+"</div>";
				CorrectAnswer=chB;
				}
				else if(right =="C")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A'  id='A' >"+chA+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' id='B'>"+chB+"</div>";
				test.innerHTML += "<div class='answer-row right-ans'><input type='radio' name='choices' value='C' id='C' checked='checked'><span>"+chC+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' id='D'>"+chD+"</div>";
				CorrectAnswer=chC;
				}
				else if(right =="D")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A'  id='A' >"+chA+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' id='B'>"+chB+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' id='C'>"+chC+"</div>";
				test.innerHTML += "<div class='answer-row right-ans'><input type='radio' name='choices' value='D' id='D' checked='checked'><span>"+chD+"</span></div>";
				CorrectAnswer=chD;
				}
}

	test.innerHTML += "<div class='footer-btn'><button onclick='renderPreviousQuestion()'  id='visit_return_button'  class='back-btn'>&larr; Back</button><button onclick='renderNextQuestion()'  id='visit_return_button' class='next-btn'>Next &rarr;</button><br class='cl' /></div>";

if (right==wrong)
{
	test.innerHTML += "<div class='solution-row'><h4>You are Right</h4><p> Correct answer is  : " + CorrectAnswer +"</p>";
}
else
{
		test.innerHTML += "<div class='solution-row'><h4>Solution </h4><p> Correct Answer is : <strong> " + CorrectAnswer+"</strong></p></div>";
	
}




//



//-----------------------
  // pos++;
  // wrongpos++;

}
}

function renderNextQuestion()
{
	pos=pos+1;
wrongpos = wrongpos +1;//++;
renderQuestion();
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

	test.innerHTML = "<h3>"+question+"</h3>";
right = questions[pos][5];
wrong = wrongchoice[choice];

if(right=="A")
		{

			if(wrong =="B")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='A'  id='A' ><span>"+chA+"</span></div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='B' id='B' checked='checked'><span>"+chB+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' id='C'>"+chC+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' id='D'>"+chD+"</div>";
				}
				else if(wrong =="C")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='A'  id='A' ><span>"+chA+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' id='B'>"+chB+"</div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='C' id='C' checked='checked'><span>"+chC+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' id='D'>"+chD+"</div>";
				}
				else if(wrong =="D")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='A'  id='A' ><span>"+chA+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' id='B'>"+chB+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' id='C'>"+chC+"</div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='D' id='D' checked='checked'><span>"+chD+"</span></div>";
				}
		}

else if(right=="B")
		{

			if(wrong =="A")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='A' id='A' checked='checked'><span>"+chA+"</div>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='B' id='B' ><span>"+chB+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' id='C'>"+chC+"</div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' id='D'>"+chD+"</div>";
				}
				else if(wrong =="C")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A'  id='A' >"+chA+"</div>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='B' id='B'><span>"+chB+"</span></div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='C' id='C' checked='checked'><span>"+chC+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' id='D'>"+chD+"</div>";
				}
				else if(wrong =="D")
				{

				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A'  id='A' >"+chA+"</div>";
				test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='B' id='B'><span>"+chB+"</span></div>";
				test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' id='C'>"+chC+"</div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='D' id='D' checked='checked'><span>"+chD+"</span></div>";
				}
		}
else if(right=="C")
		{
			if(wrong =="A")
				{
					test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='A' id='A' checked='checked'><span>"+chA+"</div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' id='B' >"+chB+"</div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='C' id='C' ><span>"+chC+"</span></div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' id='D'>"+chD+"</div>";
				}
				else if(wrong =="B")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A' id='A'>"+chA+"</div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='B' id='B' checked='checked'><span>"+chB+"</span></div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='C' id='C' ><span>"+chC+"</span></div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='D' id='D'>"+chD+"</div>";
				}
				else if(wrong =="D")
				{
				test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A' id='A'>"+chA+"</div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' id='B' >"+chB+"</div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='C' id='C' ><span>"+chC+"</span></div>";
				test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='D' id='D' checked='checked'><span>"+chD+"</span></div>";
				}
		}
else (right=="D")
		{
			if(wrong =="A")
				{
					test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
					test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='A' id='A' checked='checked'><span>"+chA+"</div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' id='B'>"+chB+"</div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' id='C' >"+chC+"</div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='D'  id='D' ><span>"+chD+"</span></div>";
				}
				else if(wrong =="B")
			{
					test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A' id='A'>"+chA+"</div>";
					test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='B' id='B' checked='checked'><span>"+chB+"</span></div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='C' id='C' >"+chC+"</div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='D'  id='D' ><span>"+chD+"</span></div>";
					}
				else if(wrong =="C")
				{
					test.innerHTML = "<h3 class='ques'>"+question+"</h3>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='A' id='A'>"+chA+"</div>";
					test.innerHTML += "<div class='answer-row'><input type='radio' name='choices' value='B' id='B'>"+chB+"</div>";
					test.innerHTML += "<div class='answer-row wrong-ans'><input type='radio' name='choices' value='C' id='C' checked='checked'><span>"+chC+"</span></div>";
					test.innerHTML += "<div class='answer-row green-ans'><input type='radio' name='choices' value='D'  id='D' ><span>"+chD+"</span></div>";
				}
		}
	
	test.innerHTML += "<div class='footer-btn'><button onclick='WrongAnswer()'  id='visit_return_button' class='next-btn'>Next</button></div>";
	    //generate_table(0,0);
}
	else
	{
			  stopwatch = true;

	 document.write("<div class='test-completed' style='position:absolute; top:50%; left:50%; max-width:300px; margin-top:-10px; margin-left:-150px; color:green; font-size:24px; text-align:center; font-family:Arial'>Your Test completed</div>");
	}
}

window.addEventListener("load", renderQuestion, false);


