let reimTable = document.getElementById('reimbursements-table');

const reimbursementTypeSelector = document.getElementsByClassName('form-select');

let curEmp;
let reimbursements = [];
let pendingReimbursements = [];
let emps = [];
let approveButtons = [];
let denyButtons = [];
let empButtons = [];

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

function approveEmpReimbursement(evt) {

    console.log("Approve button pressed " + evt.currentTarget.value);

    let curReimId = evt.currentTarget.value;

    fetch(`http://localhost:8080/official-project-one/update-reimbursement`, {

        method: 'POST',
        headers: {
            "Content-type": "application/json; charset=UTF-8",
        },
        body: JSON.stringify({
            id: curReimId,
            status: "APPROVED"
        }),
    })

    clearTable();
    setTimeout(() => {

        getEmpReims(curEmp);
    }, 2000);
}


function denyEmpReimbursement(evt) {

    console.log("Deny button pressed " + evt.currentTarget.value);

    let curReimId = evt.currentTarget.value;

    console.log(curReimId);

    fetch(`http://localhost:8080/official-project-one/update-reimbursement`, {

        method: 'POST',
        headers: {
            "Content-type": "application/json; charset=UTF-8",
        },
        body: JSON.stringify({
            id: curReimId,
            status: "DENIED"
        }),
    })

    clearTable();
    setTimeout(() => {

        getEmpReims(curEmp);
    }, 2000);
}

function getEmpReims(evt) {

    reimbursements = [];
    approveButtons = [];
    denyButtons = [];

    if (isNaN(evt)) {
        curEmp = evt.currentTarget.value;
    } else {
        curEmp = evt;
    }

    console.log("Showing requests for employee with id of " + curEmp.id);

    fetch(`http://${hostname}:8080/official-project-one/view-filed-reimbursements`, {

        method: 'POST',
        headers: {
            "Content-type": "application/json; charset=UTF-8",
        },
        body: JSON.stringify({
            id: curEmp,
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
        })

        clearTable();

        createEmployeeRequestsTable(reimbursements);
    })
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

    let th7 = document.createElement('th');
    th7.innerHTML = "";
    th7.scope = "col";

    let th8 = document.createElement('th');
    th8.innerHTML = "";
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


        let approveTD = document.createElement('td');
        let approveButton = document.createElement('button');
        approveButton.innerHTML = "Approve";
        approveButton.className = "btn btn-primary";
        approveButton.id = "action-reimbursement";
        approveButtons.push(approveButton);
        approveTD.appendChild(approveButton);

        let denyTD = document.createElement('td');
        let denyButton = document.createElement('button');
        denyButton.innerHTML = "Deny";
        denyButton.className = "btn btn-primary";
        denyButton.id = "action-reimbursement";
        denyButtons.push(denyButton);
        denyTD.appendChild(denyButton);

        row.appendChild(approveTD);
        row.appendChild(denyTD);


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

    let th7 = document.createElement('th');
    th7.innerHTML = "";
    th7.scope = "col";

    headerRow.appendChild(th1);
    headerRow.appendChild(th2);
    headerRow.appendChild(th3);
    headerRow.appendChild(th4);
    headerRow.appendChild(th5);
    headerRow.appendChild(th6);
    headerRow.appendChild(th7);

    emps.forEach(e => {

        let row = document.createElement('tr');
        let th1 = document.createElement('th');
        th1.scope = "row";
        let td1 = document.createElement('td');
        let td2 = document.createElement('td');
        let td3 = document.createElement('td');
        let td4 = document.createElement('td');
        let td5 = document.createElement('td');
        let td6 = document.createElement('td');

        let empButton = document.createElement('button');
        empButton.innerHTML = "View Requests";
        empButton.className = "btn btn-primary";
        empButton.id = "action-reimbursement";
        empButton.value = e.id;
        empButtons.push(empButton);
        td6.appendChild(empButton);


        th1.innerHTML = e.id;
        td1.innerHTML = e.lastName;
        td2.innerHTML = e.firstName;
        td3.innerHTML = e.username;
        td4.innerHTML = e.email;
        td5.innerHTML = e.type.user_type;

        row.appendChild(th1);
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
        row.appendChild(td5);
        row.appendChild(td6);



        body.appendChild(row);
    })

    empButtons.forEach(b => {
        b.addEventListener('click', getEmpReims);
    })
}

const createEmployeeRequestsTable = (reims) => {

    reimTable = document.getElementById('reimbursements-table');

    if (reimbursements.length == 0) {
        alert("This user does not have any requests.");
        createEmployeesTables();
    } else {

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

        let th9 = document.createElement('th');
        th9.innerHTML = "Status";
        th9.scope = "col";

        let th10 = document.createElement('th');
        th10.innerHTML = "";
        th10.scope = "col";

        let th11 = document.createElement('th');
        th11.innerHTML = "";
        th11.scope = "col";

        headerRow.appendChild(th1);
        headerRow.appendChild(th2);
        headerRow.appendChild(th3);
        headerRow.appendChild(th4);
        headerRow.appendChild(th5);
        headerRow.appendChild(th6);
        headerRow.appendChild(th7);
        headerRow.appendChild(th8);
        headerRow.appendChild(th9);
        headerRow.appendChild(th10);
        headerRow.appendChild(th11);

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
            let td8 = document.createElement('td');
            let td9 = document.createElement('td');
            let td10 = document.createElement('td');

            var d = new Date(0);
            d.setUTCSeconds(e.submitted.epochSecond);

            if (e.statusId.reim_status == "APPROVED" || e.statusId.reim_status == "DENIED") {

                var r = new Date(0);
                r.setUTCSeconds(e.resolved.epochSecond);
                td6.innerHTML = e.resolverId.firstName;
            } else {
                r = "Not Yet Resolved";
                td6.innerHTML = "";
            }

            th1.innerHTML = e.id;
            td1.innerHTML = e.authorId.firstName;
            td2.innerHTML = e.typeId.reim_type;
            td3.innerHTML = e.description;
            td4.innerHTML = e.amount;
            td5.innerHTML = d;
            td7.innerHTML = r;
            td8.innerHTML = e.statusId.reim_status;

            if (e.statusId.reim_status == "PENDING") {

                pendingReimbursements.push(e);

                let approveButton = document.createElement('button');
                approveButton.innerHTML = "Approve";
                approveButton.className = "btn btn-primary";
                approveButton.id = "action-reimbursement";
                approveButton.value = e.id;
                approveButtons.push(approveButton);
                td9.appendChild(approveButton);

                let denyButton = document.createElement('button');
                denyButton.innerHTML = "Deny";
                denyButton.className = "btn btn-primary";
                denyButton.id = "action-reimbursement";
                denyButton.value = e.id;
                denyButtons.push(denyButton);
                td10.appendChild(denyButton);
            }

            row.appendChild(th1);
            row.appendChild(td1);
            row.appendChild(td2);
            row.appendChild(td3);
            row.appendChild(td4);
            row.appendChild(td5);
            row.appendChild(td6);
            row.appendChild(td7);
            row.appendChild(td8);
            row.appendChild(td9);
            row.appendChild(td10);


            body.appendChild(row);
        })

        approveButtons.forEach(b => {
            b.addEventListener('click', approveEmpReimbursement);
        })

        denyButtons.forEach(b => {
            b.addEventListener('click', denyEmpReimbursement);
        })
    }
}

function fetchReimbursements(reimStatus) {
    let hostname = window.location.hostname;  // this will grab the IP of where it's deployed  
    reimbursements = [];
    emps = [];
    approveButtons = [];
    denyButtons = [];
    empButtons = [];

    if (reimStatus == "EMPLOYEES") {
        console.log("Employee table selected");

        fetch(`http://${hostname}:8080/official-project-one/employees`, {

            method: 'POST',
            headers: {
                "Content-type": "application/json; charset=UTF-8",
            }
        }).then(function (response) {
            if (!response.ok) {
                throw Error("ERROR");
            }
            return response.json();
        }).then(function (data) {

            data.forEach((obj) => {

                let emp = {
                    id: obj.id,
                    firstName: obj.firstName,
                    lastName: obj.lastName,
                    username: obj.username,
                    email: obj.email,
                    type: obj.userType,
                };

                emps.push(emp);
            });

            clearTable();

            createEmployeesTables();
        })
    } else {

        fetch(`http://${hostname}:8080/official-project-one/view-reimbursements-by-status`, {

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
            };
        })
    }

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
