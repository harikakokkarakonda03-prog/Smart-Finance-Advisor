let totalExpense = 0;
let expenseNames = [];
let expenseAmounts = [];
let chart;

function calculateEMI(){

let P = document.getElementById("amount").value;
let R = document.getElementById("rate").value / 12 / 100;
let N = document.getElementById("time").value;

let EMI = (P * R * Math.pow(1+R,N)) / (Math.pow(1+R,N)-1);

document.getElementById("result").innerHTML =
"Your Monthly EMI is: " + EMI.toFixed(2);

}

function addExpense(){

let name = document.getElementById("expenseName").value;
let amount = parseFloat(document.getElementById("expenseAmount").value);

let list = document.getElementById("expenseList");

let li = document.createElement("li");

li.innerHTML = name + " - ₹" + amount;

list.appendChild(li);

totalExpense += amount;

expenseNames.push(name);
expenseAmounts.push(amount);

updateChart();

}

function analyzeSpending(){

let advice = "";

if(totalExpense > 5000){
advice = "Your spending is high this month. Try to reduce unnecessary expenses.";
}
else if(totalExpense > 2000){
advice = "Your spending is moderate. Maintain a balance between saving and spending.";
}
else{
advice = "Good job! Your spending is under control.";
}

document.getElementById("advice").innerHTML = advice;

}

function updateChart(){

let ctx = document.getElementById("expenseChart").getContext("2d");

if(chart){
chart.destroy();
}

chart = new Chart(ctx, {
type: "bar",
data: {
labels: expenseNames,
datasets: [{
label: "Expense Amount",
data: expenseAmounts
}]
}
});

}