let reimTable = document.getElementById('reimbursements-table');

const reimbursementTypeSelector = document.getElementsByClassName('form-select');

let reimbursements = [];
let approveButtons = [];
let denyButtons = [];

function selectReimbursementTableStatus(selectObject) {

    console.log("In the selectReimbursementTableStatus function");

    const value = selectObject.value;

    fetchReimbursements(value);
}

function clearTable() {

    reimTable.innerHTML = "";
}

function approveReimbursement(evt) {

    console.log("Approve button pressed " + evt.currentTarget.value);

    let curReim = reimbursements[evt.currentTarget.value];

    console.log(curReim.id);

    fetch(`http://localhost:8080/official-project-one/update-reimbursement`, {

        method: 'POST',
        headers: {
            "Content-type": "application/json; charset=UTF-8",
        },
        body: JSON.stringify({
            id: curReim.id,
            status: "APPROVED"
        }),
    })

    reimbursements = reimbursements.filter(data => data != curReim);
    clearTable();
    createPendingTable(reimbursements);
}


function denyReimbursement(evt) {

    console.log("Deny button pressed " + evt.currentTarget.value);

    let curReim = reimbursements[evt.currentTarget.value];

    console.log(curReim.id);

    fetch(`http://localhost:8080/official-project-one/update-reimbursement`, {

        method: 'POST',
        headers: {
            "Content-type": "application/json; charset=UTF-8",
        },
        body: JSON.stringify({
            id: curReim.id,
            status: "DENIED"
        }),
    })

    reimbursements = reimbursements.filter(data => data != curReim);
    clearTable();
    createPendingTable(reimbursements);
}

const createPendingTable = (reims) => {

    reimTable = document.getElementById('reimbursements-table');
    approveButtons = [];
    denyButtons = [];

    let header = document.createElement('thead'); // these are HTML elements
    let body = document.createElement('tbody');
    let headerRow = document.createElement('tr');
    let bodyRow = document.createElement('tr');

    header.appendChild(headerRow);
    body.appendChild(bodyRow);

    reimTable.appendChild(header);
    reimTable.appendChild(body);

    let th1 = document.createElement('th');
    th1.innerHTML = "ID";
    th1.scope = "col";

    let th2 = document.createElement('th');
    th2.innerHTML = "Employee";
    th2.scope = "col";

    let th3 = document.createElement('th');
    th3.innerHTML = "Type";
    th3.scope = "col";

    let th4 = document.createElement('th');
    th4.innerHTML = "Description";
    th4.scope = "col";

    let th5 = document.createElement('th');
    th5.innerHTML = "Amount";
    th5.scope = "col";

    let th6 = document.createElement('th');
    th6.innerHTML = "Submitted";
    th6.scope = "col";

    headerRow.appendChild(th1);
    headerRow.appendChild(th2);
    headerRow.appendChild(th3);
    headerRow.appendChild(th4);
    headerRow.appendChild(th5);
    headerRow.appendChild(th6);


    reimbursements.forEach(e => {

        let row = document.createElement('tr');
        let th1 = document.createElement('th');
        th1.scope = "row";
        let td1 = document.createElement('td');
        let td2 = document.createElement('td');
        let td3 = document.createElement('td');
        let td4 = document.createElement('td');
        let td5 = document.createElement('td');

        var d = new Date(0);
        d.setUTCSeconds(e.submitted.epochSecond);


        th1.innerHTML = e.id;
        td1.innerHTML = e.authorId.firstName;
        td2.innerHTML = e.typeId.reim_type;
        td3.innerHTML = e.description;
        td4.innerHTML = e.amount;
        td5.innerHTML = d
        //(d.getMonth() + 1) + "/" + d.getDate() + "/" + d.getFullYear() + " " + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + " UCT+" + d.getTimezoneOffset() / 60;

        row.appendChild(th1);
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
        row.appendChild(td5);


        let approveButton = document.createElement('button');
        approveButton.innerHTML = "Approve";
        approveButton.className = "btn btn-primary";
        approveButton.id = "action-reimbursement";
        approveButtons.push(approveButton);

        let denyButton = document.createElement('button');
        denyButton.innerHTML = "Deny";
        denyButton.className = "btn btn-primary";
        denyButton.id = "action-reimbursement";
        denyButtons.push(denyButton);

        row.appendChild(approveButton);
        row.appendChild(denyButton);


        body.appendChild(row);
    })

    approveButtons.forEach(b => {
        b.value = approveButtons.indexOf(b);
        b.addEventListener('click', approveReimbursement);
    })

    denyButtons.forEach(b => {
        b.value = denyButtons.indexOf(b);
        b.addEventListener('click', denyReimbursement);
    })
}

const createApprovedTables = (reims) => {

    reimTable = document.getElementById('reimbursements-table');


    let header = document.createElement('thead'); // these are HTML elements
    let body = document.createElement('tbody');
    let headerRow = document.createElement('tr');
    let bodyRow = document.createElement('tr');

    header.appendChild(headerRow);
    body.appendChild(bodyRow);

    reimTable.appendChild(header);
    reimTable.appendChild(body);

    let th1 = document.createElement('th');
    th1.innerHTML = "ID";
    th1.scope = "col";

    let th2 = document.createElement('th');
    th2.innerHTML = "Employee";
    th2.scope = "col";

    let th3 = document.createElement('th');
    th3.innerHTML = "Type";
    th3.scope = "col";

    let th4 = document.createElement('th');
    th4.innerHTML = "Description";
    th4.scope = "col";

    let th5 = document.createElement('th');
    th5.innerHTML = "Amount";
    th5.scope = "col";

    let th6 = document.createElement('th');
    th6.innerHTML = "Submitted";
    th6.scope = "col";

    let th7 = document.createElement('th');
    th7.innerHTML = "Resolved By";
    th7.scope = "col";

    let th8 = document.createElement('th');
    th8.innerHTML = "Resolved";
    th8.scope = "col";

    headerRow.appendChild(th1);
    headerRow.appendChild(th2);
    headerRow.appendChild(th3);
    headerRow.appendChild(th4);
    headerRow.appendChild(th5);
    headerRow.appendChild(th6);
    headerRow.appendChild(th7);
    headerRow.appendChild(th8);

    reimbursements.forEach(e => {

        let row = document.createElement('tr');
        let th1 = document.createElement('th');
        th1.scope = "row";
        let td1 = document.createElement('td');
        let td2 = document.createElement('td');
        let td3 = document.createElement('td');
        let td4 = document.createElement('td');
        let td5 = document.createElement('td');
        let td6 = document.createElement('td');
        let td7 = document.createElement('td');

        var d = new Date(0);
        d.setUTCSeconds(e.submitted.epochSecond);

        var r = new Date(0);
        r.setUTCSeconds(e.resolved.epochSecond);

        th1.innerHTML = e.id;
        td1.innerHTML = e.authorId.firstName;
        td2.innerHTML = e.typeId.reim_type;
        td3.innerHTML = e.description;
        td4.innerHTML = e.amount;
        td5.innerHTML = d
        //(d.getMonth() + 1) + "/" + d.getDate() + "/" + d.getFullYear() + " " + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + " UCT+" + d.getTimezoneOffset() / 60;
        td6.innerHTML = e.resolverId.firstName;
        td7.innerHTML = r
        //(d.getMonth() + 1) + "/" + d.getDate() + "/" + r.getFullYear() + " " + r.getHours() + ":" + r.getMinutes() + ":" + r.getSeconds() + " UCT+" + r.getTimezoneOffset() / 60;

        row.appendChild(th1);
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
        row.appendChild(td5);
        row.appendChild(td6);
        row.appendChild(td7);
        let button = document.createElement('button');



        body.appendChild(row);
    })
}

const createDeniedTables = (reims) => {

    reimTable = document.getElementById('reimbursements-table');


    let header = document.createElement('thead'); // these are HTML elements
    let body = document.createElement('tbody');
    let headerRow = document.createElement('tr');
    let bodyRow = document.createElement('tr');

    header.appendChild(headerRow);
    body.appendChild(bodyRow);

    reimTable.appendChild(header);
    reimTable.appendChild(body);

    let th1 = document.createElement('th');
    th1.innerHTML = "ID";
    th1.scope = "col";

    let th2 = document.createElement('th');
    th2.innerHTML = "Employee";
    th2.scope = "col";

    let th3 = document.createElement('th');
    th3.innerHTML = "Type";
    th3.scope = "col";

    let th4 = document.createElement('th');
    th4.innerHTML = "Description";
    th4.scope = "col";

    let th5 = document.createElement('th');
    th5.innerHTML = "Amount";
    th5.scope = "col";

    let th6 = document.createElement('th');
    th6.innerHTML = "Submitted";
    th6.scope = "col";

    let th7 = document.createElement('th');
    th7.innerHTML = "Resolved By";
    th7.scope = "col";

    let th8 = document.createElement('th');
    th8.innerHTML = "Resolved";
    th8.scope = "col";

    headerRow.appendChild(th1);
    headerRow.appendChild(th2);
    headerRow.appendChild(th3);
    headerRow.appendChild(th4);
    headerRow.appendChild(th5);
    headerRow.appendChild(th6);
    headerRow.appendChild(th7);
    headerRow.appendChild(th8);

    reimbursements.forEach(e => {

        let row = document.createElement('tr');
        let th1 = document.createElement('th');
        th1.scope = "row";
        let td1 = document.createElement('td');
        let td2 = document.createElement('td');
        let td3 = document.createElement('td');
        let td4 = document.createElement('td');
        let td5 = document.createElement('td');
        let td6 = document.createElement('td');
        let td7 = document.createElement('td');

        var d = new Date(0);
        d.setUTCSeconds(e.submitted.epochSecond);

        var r = new Date(0);
        r.setUTCSeconds(e.resolved.epochSecond);

        th1.innerHTML = e.id;
        td1.innerHTML = e.authorId.firstName;
        td2.innerHTML = e.typeId.reim_type;
        td3.innerHTML = e.description;
        td4.innerHTML = e.amount;
        td5.innerHTML = d
        //(d.getMonth() + 1) + "/" + d.getDate() + "/" + d.getFullYear() + " " + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + " UCT+" + d.getTimezoneOffset() / 60;
        td6.innerHTML = e.resolverId.firstName;
        td7.innerHTML = r
        //(d.getMonth() + 1) + "/" + d.getDate() + "/" + r.getFullYear() + " " + r.getHours() + ":" + r.getMinutes() + ":" + r.getSeconds() + " UCT+" + r.getTimezoneOffset() / 60;

        row.appendChild(th1);
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
        row.appendChild(td5);
        row.appendChild(td6);
        row.appendChild(td7);
        let button = document.createElement('button');

        body.appendChild(row);
    })
}

const createEmployeesTables = () => {

    reimTable = document.getElementById('reimbursements-table');


    let header = document.createElement('thead'); // these are HTML elements
    let body = document.createElement('tbody');
    let headerRow = document.createElement('tr');
    let bodyRow = document.createElement('tr');

    header.appendChild(headerRow);
    body.appendChild(bodyRow);

    reimTable.appendChild(header);
    reimTable.appendChild(body);

    let th1 = document.createElement('th');
    th1.innerHTML = "ID";
    th1.scope = "col";

    let th2 = document.createElement('th');
    th2.innerHTML = "Last Name";
    th2.scope = "col";

    let th3 = document.createElement('th');
    th3.innerHTML = "First Name";
    th3.scope = "col";

    let th4 = document.createElement('th');
    th4.innerHTML = "Username";
    th4.scope = "col";

    let th5 = document.createElement('th');
    th5.innerHTML = "E-mail";
    th5.scope = "col";

    let th6 = document.createElement('th');
    th6.innerHTML = "Position";
    th6.scope = "col";

    headerRow.appendChild(th1);
    headerRow.appendChild(th2);
    headerRow.appendChild(th3);
    headerRow.appendChild(th4);
    headerRow.appendChild(th5);
    headerRow.appendChild(th6);

    reimbursements.forEach(e => {

        let row = document.createElement('tr');
        let th1 = document.createElement('th');
        th1.scope = "row";
        let td1 = document.createElement('td');
        let td2 = document.createElement('td');
        let td3 = document.createElement('td');
        let td4 = document.createElement('td');
        let td5 = document.createElement('td');
        let td6 = document.createElement('td');
        let td7 = document.createElement('td');

        var d = new Date(0);
        d.setUTCSeconds(e.submitted.epochSecond);

        var r = new Date(0);
        r.setUTCSeconds(e.resolved.epochSecond);

        th1.innerHTML = e.id;
        td1.innerHTML = e.authorId.firstName;
        td2.innerHTML = e.typeId.reim_type;
        td3.innerHTML = e.description;
        td4.innerHTML = e.amount;
        td5.innerHTML = d
        //(d.getMonth() + 1) + "/" + d.getDate() + "/" + d.getFullYear() + " " + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + " UCT+" + d.getTimezoneOffset() / 60;
        td6.innerHTML = e.resolverId.firstName;
        td7.innerHTML = r
        //(d.getMonth() + 1) + "/" + d.getDate() + "/" + r.getFullYear() + " " + r.getHours() + ":" + r.getMinutes() + ":" + r.getSeconds() + " UCT+" + r.getTimezoneOffset() / 60;

        row.appendChild(th1);
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
        row.appendChild(td5);
        row.appendChild(td6);
        row.appendChild(td7);
        let button = document.createElement('button');

        body.appendChild(row);
    })
}

function fetchReimbursements(reimStatus) {
    let hostname = window.location.hostname;  // this will grab the IP of where it's deployed  
    reimbursements = [];
    approveButtons = [];
    denyButtons = [];

    fetch(`http://localhost:8080/official-project-one/view-reimbursements-by-status`, {

        method: 'POST',
        headers: {
            "Content-type": "application/json; charset=UTF-8",
        },
        body: JSON.stringify({
            status: reimStatus
        }),
    }).then(function (response) {
        if (!response.ok) {
            throw Error("ERROR");
        }
        return response.json();
    }).then(function (data) {

        data.forEach((obj) => {

            let newReim = {
                id: obj.id,
                amount: obj.amount,
                submitted: obj.submitted,
                resolved: obj.resolved,
                description: obj.description,
                authorId: obj.authorId,
                resolverId: obj.resolverId,
                statusId: obj.statusId,
                typeId: obj.typeId
            };

            reimbursements.push(newReim);
        });

        console.log(reimbursements);

        clearTable();

        switch (reimStatus) {

            case 'PENDING':
                console.log("Pending table selected");
                setTimeout(() => {

                    createPendingTable(reimbursements)


                }, 1000);
                break;
            case 'APPROVED':
                console.log("Approved table selected");
                setTimeout(() => {


                    createApprovedTables(reimbursements)
                }, 1000);
                break;
            case 'DENIED':
                console.log("Denied table selected");
                createDeniedTables(reimbursements);
                break;
            case 'EMPLOYEES':
                console.log("Employees table selected")
                createEmmployeesTable();
                break;
        };
    })
    // .then(response => {
    //         if (!response.ok) {
    //             throw Error("ERROR")
    //         }

    //         let childDiv = document.getElementById("warningText")
    //         childDiv.innerHTML = `<p style="color:red;"><b>Failed to gather reimbursements!/b></p>`;
    //         return response.json();
    //     }).catch(error => {
    //         console.log(error);

    //         let childDiv = document.getElementById("warningText")
    //         childDiv.innerHTML = `<p style="color:red;"><b>Failed to get reimbursements!</b></p>`;
    //     })

    // // we must remove the port number because when it's deployed it won't need this
    // //fetch(`http://${hostname}/official-project-one/employees`)
    // // this is changed because the port will be inferred when deployed on Elastic beanstalk


}
