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

 
function renderQuestion(){
	var flag;
	test = _("test");
	if(pos >= questions.length){
		test.innerHTML = "<h2>You got "+correct+" of "+questions.length+" questions correct.</h2>";
		_("test_status").innerHTML = "Test Completed";
		generate_table(correct,questions.length );

		if (correct==questions.length)
		{
		}
		else
		{

		test.innerHTML +="<BR>";
		test.innerHTML += "<button onclick='WrongAnswer()' id='visit_return_button' >Review</button>";
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
	
	test.innerHTML = "<h3>"+question+"</h3>";
	test.innerHTML += "<input type='radio' name='choices' value='A' onclick='checkAnswer();'>"+chA+"<br>";
	test.innerHTML += "<input type='radio' name='choices' value='B'onclick='checkAnswer();' >"+chB+"<br>";
	test.innerHTML += "<input type='radio' name='choices' value='C'onclick='checkAnswer();' >"+chC+"<br>";
	test.innerHTML += "<input type='radio' name='choices' value='D' onclick='checkAnswer();' >"+chD+"<br><br>";



	test.innerHTML += "<button onclick='renderPreviousQuestion()' id='visit_return_button'>Back</button>";
	test.innerHTML += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	//test.innerHTML += "<button onclick='checkAnswer()'> Check </button>";
	test.innerHTML += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    test.innerHTML += "<button onclick='renderNextQuestion()' id='visit_return_button'>Next</button>";

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

	test.innerHTML = "<h3>"+question+"</h3>";
	test.innerHTML +="<input type='radio' name='choices' value='A' onclick='checkAnswer()'>"+chA+"<br>"
	test.innerHTML += "<input type='radio' name='choices' value='B'onclick='checkAnswer()'>"+chB+"<br>";
	test.innerHTML += "<input type='radio' name='choices' value='C'onclick='checkAnswer()'>"+chC+"<br>";
	test.innerHTML += "<input type='radio' name='choices' value='D'onclick='checkAnswer()'>"+chD+"<br><br>";
	test.innerHTML += "<button onclick='renderPreviousQuestion()' id='visit_return_button'>Back</button>";
	test.innerHTML += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	//test.innerHTML += "<button onclick='checkAnswer()'>Check </button>";
		test.innerHTML += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

    test.innerHTML += "<button onclick='renderNextQuestion()' id='visit_return_button'>Next</button>";

}
function generate_table( correct,  total){
if (total ==0)
{
}
else
	{
	var table = document.createElement('table');
	table.setAttribute('border','1');
	table.setAttribute('width','100%')
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

function generate_table_old( correct,  total) {

  if (total==0)
  {
		var tables = document.getElementsByTagName("table");
		for (var i=tables.length-1; i>=0;i-=1)
		if (tables[i]) tables[i].parentNode.removeChild(tables[i]);
   }
  else
{
 // get the reference for the body
    var body = document.getElementsByTagName("body")[0];
   // creates a <table> element and a <tbody> element
  var tbl = document.createElement("table");
  var tblBody = document.createElement("tbody");

  // creating all cells
  for (var i = 0; i < 2; i++) {
    // creates a table row
    var row = document.createElement("tr");

    for (var j = 0; j < 3; j++) {
      // Create a <td> element and a text node, make the text
      // node the contents of the <td>, and put the <td> at
      // the end of the table row
      var cell = document.createElement("td");
	  wrong=total-correct;
	  if (i==0)
	  {
		  if(j==0)
		   var cellText = document.createTextNode("Total");
		  if(j==1)
		   var cellText = document.createTextNode("Correct");
		  if(j==2)
		   var cellText = document.createTextNode("Wrong");
	  }
	  if (i==1)
	  {
		  if(j==0)
		   var cellText = document.createTextNode(total);
		  if(j==1)
		   var cellText = document.createTextNode(correct);
		  if(j==2)
		   var cellText = document.createTextNode(wrong);
	  }
      cell.appendChild(cellText);
      row.appendChild(cell);
    }

    // add the row to the end of the table body
    tblBody.appendChild(row);
  }
    tbl.setAttribute("border", "1");
  // put the <tbody> in the <table>
  tbl.appendChild(tblBody);
  // appends <table> into <body>
  //body.appendChild(tbl);
document.getElementById("test").innerHTML = tbl;
  // sets the border attribute of tbl to 2;
	}
}

function checkAnswer(){

if(pos<questions.length)
{
	choices = document.getElementsByName("choices");

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

if(right=="A")
		{

			if(wrong =="B")
				{
				test.innerHTML = "<h3>"+question+"</h3>";
				test.innerHTML += "<input type='radio' name='choices' value='A'  id='A' ><span style='color:#24b624;font-weight:bold''>"+chA+"</span><br>";
				test.innerHTML += "<input type='radio' name='choices' value='B' id='B' checked='checked'><span style='color:#A52A2A;font-weight:bold'>"+chB+"</span><br>";
				test.innerHTML += "<input type='radio' name='choices' value='C' id='C'>"+chC+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='D' id='D'>"+chD+"<br><br>";
				}
				else if(wrong =="C")
				{
				test.innerHTML = "<h3>"+question+"</h3>";
				test.innerHTML += "<input type='radio' name='choices' value='A'  id='A' ><span style='color:#24b624;font-weight:bold''>"+chA+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='B' id='B'>"+chB+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='C' id='C' checked='checked'> <span style='color:#A52A2A;font-weight:bold'>"+chC+"</span><br>";
				test.innerHTML += "<input type='radio' name='choices' value='D' id='D'>"+chD+"<br><br>";
				}
				else if(wrong =="D")
				{
				test.innerHTML = "<h3>"+question+"</h3>";
				test.innerHTML += "<input type='radio' name='choices' value='A'  id='A' ><span style='color:#008000;font-weight:bold'>"+chA+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='B' id='B'>"+chB+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='C' id='C'>"+chC+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='D' id='D' checked='checked'><span style='color:#A52A2A;font-weight:bold'>"+chD+"</span><br><br>";
				}
		}

else if(right=="B")
		{

			if(wrong =="A")
				{
				test.innerHTML = "<h3>"+question+"</h3>";
				test.innerHTML += "<input type='radio' name='choices' value='A' id='A' checked='checked'> <span style='color:#A52A2A;font-weight:bold'>"+chA+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='B' id='B' ><span style='color:#008000;font-weight:bold'>"+chB+"</span><br>";
				test.innerHTML += "<input type='radio' name='choices' value='C' id='C'>"+chC+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='D' id='D'>"+chD+"<br><br>";
				}
				else if(wrong =="C")
				{
				test.innerHTML = "<h3>"+question+"</h3>";
				test.innerHTML += "<input type='radio' name='choices' value='A'  id='A' >"+chA+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='B' id='B'><span style='color:#008000;font-weight:bold'>"+chB+"</span><br>";
				test.innerHTML += "<input type='radio' name='choices' value='C' id='C' checked='checked'> <span style='color:#A52A2A;font-weight:bold'>"+chC+"</span><br>";
				test.innerHTML += "<input type='radio' name='choices' value='D' id='D'>"+chD+"<br><br>";
				}
				else if(wrong =="D")
				{

				test.innerHTML = "<h3>"+question+"</h3>";
				test.innerHTML += "<input type='radio' name='choices' value='A'  id='A' >"+chA+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='B' id='B'><span style='color:#008000;font-weight:bold'>"+chB+"</span><br>";
				test.innerHTML += "<input type='radio' name='choices' value='C' id='C'>"+chC+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='D' id='D' checked='checked'><span style='color:#A52A2A;font-weight:bold'>"+chD+"</span><br><br>";
				}
		}
else if(right=="C")
		{
			if (right!=wrong)
			{

			if(wrong =="A")
				{
					test.innerHTML = "<h3>"+question+"</h3>";
				test.innerHTML += "<input type='radio' name='choices' value='A' id='A' checked='checked'> <span style='color:#A52A2A;font-weight:bold' >"+chA+"<br>";
					test.innerHTML += "<input type='radio' name='choices' value='B' id='B' >"+chB+"<br>";
					test.innerHTML += "<input type='radio' name='choices' value='C' id='C' ><span style='color:#008000;font-weight:bold'>"+chC+"</span><br>";
					test.innerHTML += "<input type='radio' name='choices' value='D' id='D'>"+chD+"<br><br>";
				}
				else if(wrong =="B")
				{
				test.innerHTML = "<h3>"+question+"</h3>";
					test.innerHTML += "<input type='radio' name='choices' value='A' id='A'>"+chA+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='B' id='B' checked='checked'><span style='color:#A52A2A;font-weight:bold'>"+chB+"</span><br>";
					test.innerHTML += "<input type='radio' name='choices' value='C' id='C' ><span style='color:#008000;font-weight:bold'>"+chC+"</span><br>";
					test.innerHTML += "<input type='radio' name='choices' value='D' id='D'>"+chD+"<br><br>";
				}
				else if(wrong =="D")
				{
				test.innerHTML = "<h3>"+question+"</h3>";
				test.innerHTML += "<input type='radio' name='choices' value='A' id='A'>"+chA+"<br>";
				 test.innerHTML += "<input type='radio' name='choices' value='B' id='B' >"+chB+"<br>";
				 test.innerHTML += "<input type='radio' name='choices' value='C' id='C' ><span style='color:#008000;font-weight:bold'>"+chC+"</span><br>";
				 test.innerHTML += "<input type='radio' name='choices' value='D' id='D' checked='checked'><span style='color:#A52A2A;font-weight:bold'>"+chD+"</span><br><br>";
				}
				}


		}
else if (right=="D")
		{
			if(wrong =="A")
				{
					test.innerHTML = "<h3>"+question+"</h3>";
					test.innerHTML += "<input type='radio' name='choices' value='A' id='A' checked='checked'><span style='color:#A52A2A;font-weight:bold'>"+chA+"<br>";
					test.innerHTML += "<input type='radio' name='choices' value='B' id='B'>"+chB+"<br>";
					test.innerHTML += "<input type='radio' name='choices' value='C' id='C' >"+chC+"<br>";
					test.innerHTML += " <input type='radio' name='choices' value='D'  id='D' ><span style='color:#008000;font-weight:bold'>"+chD+"</span><br><br>";
				}
				else if(wrong =="B")
			{
					test.innerHTML = "<h3>"+question+"</h3>";
					test.innerHTML += "<input type='radio' name='choices' value='A' id='A'>"+chA+"<br>";
					test.innerHTML += "<input type='radio' name='choices' value='B' id='B' checked='checked'><span style='color:#A52A2A;font-weight:bold'>"+chB+"</span><br>";
					test.innerHTML += "<input type='radio' name='choices' value='C' id='C' >"+chC+"<br>";
					test.innerHTML += "<input type='radio' name='choices' value='D'  id='D' ><span style='color:#008000;font-weight:bold'>"+chD+"</span><br><br>";
					}
				else if(wrong =="C")
				{
					test.innerHTML = "<h3>"+question+"</h3>";
					test.innerHTML += "<input type='radio' name='choices' value='A' id='A'>"+chA+"<br>";
					test.innerHTML += "<input type='radio' name='choices' value='B' id='B'>"+chB+"<br>";
					test.innerHTML += "<input type='radio' name='choices' value='C' id='C' checked='checked'> <span style='color:#A52A2A;font-weight:bold'>"+chC+"</span><br>";
					test.innerHTML += "<input type='radio' name='choices' value='D'  id='D' ><span style='color:#008000;font-weight:bold'>"+chD+"</span><br><br>";
				}
		}

		if (right==wrong)
{
				if(right =="A")
				{
				test.innerHTML = "<h3>"+question+"</h3>";
				test.innerHTML += " <input type='radio' name='choices' value='A'  id='A' checked='checked'><span style='color:#008000;font-weight:bold'>"+chA+"</span><br>";
				test.innerHTML += " <input type='radio' name='choices' value='B' id='B' >"+chB+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='C' id='C'>"+chC+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='D' id='D'>"+chD+"<br><br>";
				}
				if(right =="B")
				{
				test.innerHTML = "<h3>"+question+"</h3>";
				test.innerHTML += "<input type='radio' name='choices' value='A'  id='A' >"+chA+"<br>";
				test.innerHTML += " <input type='radio' name='choices' value='B' id='B' checked='checked'><span style='color:#008000;font-weight:bold'>"+chB+"</span><br>";
				test.innerHTML += "<input type='radio' name='choices' value='C' id='C'>"+chC+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='D' id='D'>"+chD+"<br><br>";
				}
				else if(right =="C")
				{
				test.innerHTML = "<h3>"+question+"</h3>";
				test.innerHTML += "<input type='radio' name='choices' value='A'  id='A' >"+chA+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='B' id='B'>"+chB+"<br>";
				test.innerHTML += " <input type='radio' name='choices' value='C' id='C' checked='checked'> <span style='color:#008000;font-weight:bold'>"+chC+"</span><br>";
				test.innerHTML += "<input type='radio' name='choices' value='D' id='D'>"+chD+"<br><br>";
				}
				else if(right =="D")
				{
				test.innerHTML = "<h3>"+question+"</h3>";
				test.innerHTML += "<input type='radio' name='choices' value='A'  id='A' >"+chA+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='B' id='B'>"+chB+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='C' id='C'>"+chC+"<br>";
				test.innerHTML += " <input type='radio' name='choices' value='D' id='D' checked='checked'><span style='color:#008000;font-weight:bold'>"+chD+"</span><br><br>";
				}
}

	test.innerHTML += "<button onclick='renderPreviousQuestion()'  id='visit_return_button'>Back</button>";
	test.innerHTML += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	//test.innerHTML += "<button onclick='checkAnswer()'> Check </button>";
	test.innerHTML += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    test.innerHTML += "<button onclick='renderNextQuestion()'  id='visit_return_button'>Next</button>";



//



//-----------------------
   pos++;
   wrongpos++;

}
}

function renderNextQuestion()
{
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
				test.innerHTML = "<h3>"+question+"</h3>";
				test.innerHTML += "<input type='radio' name='choices' value='A'  id='A' ><span style='color:#008000;font-weight:bold'>"+chA+"</span><br>";
				test.innerHTML += "<input type='radio' name='choices' value='B' id='B' checked='checked'><span style='color:#A52A2A;font-weight:bold'>"+chB+"</span><br>";
				test.innerHTML += "<input type='radio' name='choices' value='C' id='C'>"+chC+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='D' id='D'>"+chD+"<br><br>";
				}
				else if(wrong =="C")
				{
				test.innerHTML = "<h3>"+question+"</h3>";
				test.innerHTML += "<input type='radio' name='choices' value='A'  id='A' ><span style='color:#008000;font-weight:bold'>"+chA+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='B' id='B'>"+chB+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='C' id='C' checked='checked'> <span style='color:#A52A2A;font-weight:bold'>"+chC+"</span><br>";
				test.innerHTML += "<input type='radio' name='choices' value='D' id='D'>"+chD+"<br><br>";
				}
				else if(wrong =="D")
				{
				test.innerHTML = "<h3>"+question+"</h3>";
				test.innerHTML += "<input type='radio' name='choices' value='A'  id='A' ><span style='color:#008000;font-weight:bold'>"+chA+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='B' id='B'>"+chB+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='C' id='C'>"+chC+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='D' id='D' checked='checked'><span style='color:#A52A2A;font-weight:bold'>"+chD+"</span><br><br>";
				}
		}

else if(right=="B")
		{

			if(wrong =="A")
				{
				test.innerHTML = "<h3>"+question+"</h3>";
				test.innerHTML += "<input type='radio' name='choices' value='A' id='A' checked='checked'> <span style='color:#A52A2A;font-weight:bold'>"+chA+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='B' id='B' ><span style='color:#008000;font-weight:bold'>"+chB+"</span><br>";
				test.innerHTML += "<input type='radio' name='choices' value='C' id='C'>"+chC+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='D' id='D'>"+chD+"<br><br>";
				}
				else if(wrong =="C")
				{
				test.innerHTML = "<h3>"+question+"</h3>";
				test.innerHTML += "<input type='radio' name='choices' value='A'  id='A' >"+chA+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='B' id='B'><span style='color:#008000;font-weight:bold'>"+chB+"</span><br>";
				test.innerHTML += "<input type='radio' name='choices' value='C' id='C' checked='checked'> <span style='color:#A52A2A;font-weight:bold'>"+chC+"</span><br>";
				test.innerHTML += "<input type='radio' name='choices' value='D' id='D'>"+chD+"<br><br>";
				}
				else if(wrong =="D")
				{

				test.innerHTML = "<h3>"+question+"</h3>";
				test.innerHTML += "<input type='radio' name='choices' value='A'  id='A' >"+chA+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='B' id='B'><span style='color:#008000;font-weight:bold'>"+chB+"</span><br>";
				test.innerHTML += "<input type='radio' name='choices' value='C' id='C'>"+chC+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='D' id='D' checked='checked'><span style='color:#A52A2A;font-weight:bold'>"+chD+"</span><br><br>";
				}
		}
else if(right=="C")
		{
			if(wrong =="A")
				{
					test.innerHTML = "<h3>"+question+"</h3>";
				test.innerHTML += "<input type='radio' name='choices' value='A' id='A' checked='checked'> <span style='color:#A52A2A;font-weight:bold' >"+chA+"<br>";
					test.innerHTML += "<input type='radio' name='choices' value='B' id='B' >"+chB+"<br>";
					test.innerHTML += "<input type='radio' name='choices' value='C' id='C' ><span style='color:#008000;font-weight:bold'>"+chC+"</span><br>";
					test.innerHTML += "<input type='radio' name='choices' value='D' id='D'>"+chD+"<br><br>";
				}
				else if(wrong =="B")
				{
				test.innerHTML = "<h3>"+question+"</h3>";
					test.innerHTML += "<input type='radio' name='choices' value='A' id='A'>"+chA+"<br>";
				test.innerHTML += "<input type='radio' name='choices' value='B' id='B' checked='checked'><span style='color:#A52A2A;font-weight:bold'>"+chB+"</span><br>";
					test.innerHTML += "<input type='radio' name='choices' value='C' id='C' ><span style='color:#008000;font-weight:bold'>"+chC+"</span><br>";
					test.innerHTML += "<input type='radio' name='choices' value='D' id='D'>"+chD+"<br><br>";
				}
				else if(wrong =="D")
				{
				test.innerHTML = "<h3>"+question+"</h3>";
					test.innerHTML += "<input type='radio' name='choices' value='A' id='A'>"+chA+"<br>";
					test.innerHTML += "<input type='radio' name='choices' value='B' id='B' >"+chB+"<br>";
					test.innerHTML += "<input type='radio' name='choices' value='C' id='C' ><span style='color:#008000;font-weight:bold'>"+chC+"</span><br>";
				test.innerHTML += "<input type='radio' name='choices' value='D' id='D' checked='checked'><span style='color:#A52A2A;font-weight:bold'>"+chD+"</span><br><br>";
				}
		}
else if (right=="D")
		{
			if(wrong =="A")
				{
					test.innerHTML = "<h3>"+question+"</h3>";
					test.innerHTML += "<input type='radio' name='choices' value='A' id='A' checked='checked'> <span style='color:#A52A2A;font-weight:bold'>"+chA+"<br>";
					test.innerHTML += "<input type='radio' name='choices' value='B' id='B'>"+chB+"<br>";
					test.innerHTML += "<input type='radio' name='choices' value='C' id='C' >"+chC+"<br>";
					test.innerHTML += "<input type='radio' name='choices' value='D'  id='D' ><span style='color:#008000;font-weight:bold'>"+chD+"</span><br><br>";
				}
				else if(wrong =="B")
			{
					test.innerHTML = "<h3>"+question+"</h3>";
					test.innerHTML += "<input type='radio' name='choices' value='A' id='A'>"+chA+"<br>";
					test.innerHTML += "<input type='radio' name='choices' value='B' id='B' checked='checked'><span style='color:#A52A2A;font-weight:bold'>"+chB+"</span><br>";
					test.innerHTML += "<input type='radio' name='choices' value='C' id='C' >"+chC+"<br>";
					test.innerHTML += "<input type='radio' name='choices' value='D'  id='D' ><span style='color:#008000;font-weight:bold'>"+chD+"</span><br><br>";
					}
				else if(wrong =="C")
				{
					test.innerHTML = "<h3>"+question+"</h3>";
					test.innerHTML += "<input type='radio' name='choices' value='A' id='A'>"+chA+"<br>";
					test.innerHTML += "<input type='radio' name='choices' value='B' id='B'>"+chB+"<br>";
					test.innerHTML += "<input type='radio' name='choices' value='C' id='C' checked='checked'> <span style='color:#A52A2A;font-weight:bold'>"+chC+"</span><br>";
					test.innerHTML += "<input type='radio' name='choices' value='D'  id='D' ><span style='color:#008000;font-weight:bold'>"+chD+"</span><br><br>";
				}
		}
	
	test.innerHTML += "<button onclick='WrongAnswer()'  id='visit_return_button'>Next</button>";
	    generate_table(0,0);
}
	else
	 document.write("<b>Test completed</b>");
	 
}

window.addEventListener("load", renderQuestion, false);


