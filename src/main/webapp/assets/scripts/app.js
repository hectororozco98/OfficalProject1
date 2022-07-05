const exampleModal = document.getElementById('exampleModal');
const reimbModal = document.getElementById('reimbModal');
const infoButton = document.getElementById('infoBtn');
const userInputsInfo = exampleModal.querySelectorAll('input');

const reimInputs = reimbModal.querySelectorAll('input');
const reimSelect = reimbModal.querySelector('select');

const submitInfoButton = exampleModal.querySelector('.btn.btn-success');
const submitReimButton = reimbModal.querySelector('.btn.btn-success');
const viewButton = document.getElementById('viewReimbs');
const table = document.getElementById('user-reims');

let user;

submitInfoButton.addEventListener('click', submitUpdate);
submitReimButton.addEventListener('click', submitReim);
viewButton.addEventListener('click', viewUserReim);
infoButton.addEventListener('click', fetchUser);

function fetchUser() {

    let hostname = window.location.hostname;
    fetch(`http://${hostname}:8080/official-project-one/get-user`, {

        method: 'GET',
        headers: {
            "Content-type": "application/json; charset=UTF-8",
        },
    }).then(function(response) {
        return response.json();
    })
    .then(data => showInfo(data))
  
     
    
};

function showInfo(data) {
    user = data;
    console.log(user)

    userInputsInfo[0].value = data.firstName
    userInputsInfo[1].value = data.lastName
    userInputsInfo[2].value = data.username
    userInputsInfo[3].value = data.password
    userInputsInfo[4].value = data.email
}

function updateUser() {

    let firstName = userInputsInfo[0].value;
    let lastName = userInputsInfo[1].value;
    let username = userInputsInfo[2].value;
    let password = userInputsInfo[3].value;
    let email = userInputsInfo[4].value;

    let hostname = window.location.hostname;
    fetch(`http://${hostname}:8080/official-project-one/update`, {
        method: 'POST',
        headers: {
            "Content-type": "application/json; charset=UTF-8",
        },
        body: JSON.stringify({

            firstName: firstName,
            lastName: lastName,
            username: username,
            password: password,
            email: email
        }),
    })
        .then((response) => response.json())

};

function submitUpdate() {

    updateUser();
    alert("User info sucessfully updated!")

};


function submitReim() {

    let amount = reimInputs[0].value;
    let description = reimInputs[1].value;
    let type = reimSelect.value;

    let hostname = window.location.hostname;
    fetch(`http://${hostname}:8080/official-project-one/reimbursements`, {

        method: 'POST',
        headers: {
            "Content-type": "application/json; charset=UTF-8",
        },
        body: JSON.stringify({
            amount: amount,
            description: description,
            type: type
        })
    })
        .then((response) => response.json())
        .then((json) => console.log(json));

    alert('Reimbursement submitted!')
};



function viewUserReim() {
    fetchUser();
    getEmpReims();
    createRequestsTable();
};

function getEmpReims(data) {

    let hostname = window.location.hostname;
    let reimbursements = [];


    fetch(`http://${hostname}:8080/official-project-one/view-filed-reimbursements`, {

        method: 'POST',
        headers: {
            "Content-type": "application/json; charset=UTF-8",
        },
        body: JSON.stringify({
            id: data.id
            
        }),
        
        })
    .then(function (response) {
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

        createRequestsTable(reimbursements);
    });
}

const createRequestsTable = (reims) => {

    reimTable = document.getElementById('user-reims');

    if (reimbursements.length == 0) {
        alert("This user does not have any requests.");

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

    }
};

function clearTable() {

    reimTable.innerHTML = "";
};

exampleModal.addEventListener('show.bs.modal', event => {
    // Button that triggered the modal
    const button = event.relatedTarget
    // Extract info from data-bs-* attributes

    // If necessary, you could initiate an AJAX request here
    // and then do the updating in a callback.
    //
    // Update the modal's content.

    const modalBodyInput = exampleModal.querySelector('.modal-body input')


    //modalBodyInput.value = recipient
});


reimbModal.addEventListener('show.bs.modal', event => {
    // Button that triggered the modal
    const button2 = event.relatedTarget
    // Extract info from data-bs-* attributes

    // If necessary, you could initiate an AJAX request here
    // and then do the updating in a callback.
    //
    // Update the modal's content.

    const modalBodyInput2 = reimbModal.querySelector('.modal-body input')


    //modalBodyInput.value = recipient
});
