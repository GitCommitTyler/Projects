function getDeals(){
    deals = [
        {
          "deal_id": 1,
          "vendor_name": "Microsoft",
          "project_name": "Apollo Project",
          "project_cost": 1000
        },
        {
          "deal_id": 2,
          "vendor_name": "Intel",
          "project_name": "Hermes Project",
          "project_cost": 10000
        },
        {
          "deal_id": 3,
          "vendor_name": "Apple",
          "project_name": "Zeus Project",
          "project_cost": 100000
        }
      ];

      if(localStorage.getItem("deals")!= null){
          return JSON.parse(localStorage.getItem("deals"));
         
      }
      localStorage.setItem("deals", JSON.stringify(deals));
      return deals;

}

function createTable(){
    let data = deals;
    console.log(data);
    let table = document.createElement("table");
    table.id = "dealstable"

    var row = table.insertRow(-1);
    rowCount += 1;
    Object.keys(data[0]).forEach(element => {
    var th = document.createElement("th");  
        th.innerHTML = element;
        row.appendChild(th);
    });
    if(userType == "Project Manager"){
        var deleteCol = document.createElement("th");
        deleteCol.classList.add("deleteCol");
        row.appendChild(deleteCol);
    }

    data.forEach(element => { 
        var innerRow = table.insertRow(-1);
        rowCount += 1;
        innerRow.id = "row"+element.deal_id;
        var cell = innerRow.insertCell(-1);
        cell.innerHTML = element.deal_id;

        var cell1 = innerRow.insertCell(-1);
        cell1.innerHTML = element.vendor_name;
        cell1.id = "vendor"+element.deal_id;

        var cell2 = innerRow.insertCell(-1);
        cell2.innerHTML = element.project_name;
        cell2.id = "project"+element.deal_id;

        var cell3 = innerRow.insertCell(-1);
        cell3.innerHTML = "$"+element.project_cost;
        cell3.id = "cost"+element.deal_id;
        if(userType == "Finance Team"){
            innerRow.addEventListener("dblclick", function(){editDealFinance(element.deal_id)}, false);
        }
        if(userType == "Project Manager"){
            innerRow.addEventListener("dblclick", function(){editDealPM(element.deal_id)}, false);
        }
        
        if(userType == "Project Manager"){

            var cell4 = innerRow.insertCell(-1);
            var deleteButton = document.createElement("button");
            deleteButton.id = "deleteButton"+element.deal_id
            console.log(deleteButton.id);
            cell4.appendChild(deleteButton);
            deleteButton.innerHTML = "Delete Deal";
            deleteButton.classList.add("btn", "btn-danger");
            deleteButton.type = "button";
            deleteButton.addEventListener("click", function(){deleteDeal(element.deal_id)}, false);
        }
       
    })

    if(userType == "Project Manager"){
        var addRow = table.insertRow(-1);
        addRow.id = "addRow";
        var addCell = addRow.insertCell(-1);
        addCell.colSpan = "5";
        addCell.classList.add("text-center");

        plusButton = document.createElement("button");
        plusButton.classList.add("btn", "btn-warning");
        plusButton.innerHTML = "add deal";
        plusButton.addEventListener("click", function(){addDeal()}, false);
        addCell.appendChild(plusButton);
    }

    
    //console.log(btn);
    //btn.onclick = deleteProject();

    table.classList.add("table", "table-dark", "table-striped");
    document.body.appendChild(table);
}

function deleteDeal(id){
    var obj = document.getElementById("row"+id);
    obj.remove();
    deals.splice(id-1, 1);
    localStorage.setItem("deals", JSON.stringify(deals));
    rowCount -=1;
}

function editDealFinance(id){
    var costCell = document.getElementById("cost"+id);
    console.log(costCell);
    costCell.innerHTML = '';
    var form = document.createElement("form");
    var inputField = document.createElement("input");
    inputField.type = "number";
    inputField.id = "field"+id;
    inputField.style = "color: rgb(255,255,255); background-color: rgb(40, 40, 40)"
    form.appendChild(inputField);
    console.log(form);
    form.id = "costForm"+id;
    costCell.appendChild(form);
    form.addEventListener("submit", function(){formOnSubmit(id)}, false);
}

function editDealPM(id){
    var vendorCell = document.getElementById("vendor"+id);
    var projectCell = document.getElementById("project"+id);
    vendorCell.innerHTML = '';
    projectCell.innerHTML = '';
    var form1 = document.createElement("form");
    var form2 = document.createElement("form");
    var vendorInputField = document.createElement("input");
    var projectInputField = document.createElement("input");
    // vendorInputField.required="true";
    // projectInputField.required ="true";

    vendorInputField.id = "vendorfield"+id;
    projectInputField.id = "projectfield"+id;
    projectInputField.style = "color: rgb(255,255,255); background-color: rgb(40, 40, 40)";
    vendorInputField.style = "color: rgb(255,255,255); background-color: rgb(40, 40, 40)";
    form1.appendChild(vendorInputField);
    form2.appendChild(projectInputField);
    form1.id="pmForm1"+id;
    form2.id = "pmForm2"+id;
    vendorCell.appendChild(form1);
    projectCell.appendChild(form2);
    form1.addEventListener("submit", function(){pmFormOnSubmit(id, "vendor"+id, "vendorfield"+id)}, false);
    form2.addEventListener("submit", function(){pmFormOnSubmit(id, "project"+id, "projectfield"+id)}, false);

}

function addDeal(){
    var table = document.getElementById("dealstable");
    var addedRow = table.insertRow(rowCount);
    var form = document.createElement("form");
    form.id = "addform";
    document.body.appendChild(form);
    
    var cell1 = addedRow.insertCell(-1);
    var id = rowCount;
    cell1.innerHTML = rowCount;
    var cell2 = addedRow.insertCell(-1);
    var vendorInputField = document.createElement("input");
    vendorInputField.required = "true";
    vendorInputField.style = "color: rgb(255,255,255); background-color: rgb(40, 40, 40); margin-right: 40px;";
    vendorInputField.setAttribute("form", "addform");
    vendorInputField.setAttribute("placeholder", "enter vendor name");
    
    cell2.appendChild(vendorInputField);

    var cell3 = addedRow.insertCell(-1);
    var projectInputField = document.createElement("input");
    projectInputField.required = "true";
    projectInputField.style = "color: rgb(255,255,255); background-color: rgb(40, 40, 40); margin-right: 40px;";
    projectInputField.setAttribute("form", "addform");  
    projectInputField.setAttribute("placeholder", "enter project name");

    cell3.appendChild(projectInputField);

    var cell4 = addedRow.insertCell(-1);
    var costInputField = document.createElement("input");
    costInputField.required = "true";
    costInputField.style = "color: rgb(255,255,255); background-color: rgb(40, 40, 40); margin-right: 50px;";
    costInputField.setAttribute("form", "addform");
    costInputField.setAttribute("placeholder", "enter cost");
    costInputField.setAttribute("type", "number");
    
    cell4.appendChild(costInputField);

    cell5 = addedRow.insertCell(-1);
    var submitInputField = document.createElement("input");
    submitInputField.type = "submit";
    submitInputField.value = "Add Deal";
    submitInputField.classList.add("btn", "btn-primary");
    submitInputField.setAttribute("form", "addform");
    
    
    cell5.appendChild(submitInputField);
    
    rowCount +=1;

    form.addEventListener("submit", function(){
        submitNewDeal(id, vendorInputField.value, projectInputField.value, costInputField.value);
    }, false);

}

function submitNewDeal(id, vendor, project, cost){
    console.log(id, vendor, project, cost);
    deals.push({"deal_id": id, "vendor_name": vendor, "project_name": project, "project_cost": cost});
    localStorage.setItem("deals", JSON.stringify(deals));
    deals.push({})
    return false;
}

function formOnSubmit(id){
    var costCell = document.getElementById("cost"+id);
    console.log(document.getElementById("field"+id));
    var inputValue = document.getElementById("field"+id).value;
    costCell.innerHTML= "$"+inputValue;
    deals[id-1].project_cost = inputValue;
    localStorage.setItem("deals", JSON.stringify(deals));
}

function pmFormOnSubmit(dealsId, id, fieldId){
    var cell = document.getElementById(id);
    var inputValue = document.getElementById(fieldId).value;
    
    console.log(deals[dealsId-1].vendor_name);
    if(fieldId == "vendorfield"+dealsId && inputValue != '' ){
        deals[dealsId-1].vendor_name = inputValue;
        cell.innerHTML = inputValue;
    }
    else if(fieldId == "projectfield"+dealsId && inputValue != '' ){
        deals[dealsId-1].project_name = inputValue;
        cell.innerHTML = inputValue;
    }
    localStorage.setItem("deals", JSON.stringify(deals));
    
}
var deals = getDeals();
var rowCount = 0;
var userType = localStorage.getItem("userType");
createTable();
