const reimbursementTypeSelector = document.getElementsByClassName('form-select');
let reimTable = document.getElementById('reimbursements-table');
const reimbursements = [];

function selectReimbursementTableStatus(selectObject) {

    console.log("In the selectReimbursementTableStatus function");

    const value = selectObject.value;

    fetchReimbursements(value);
}

const createPendingTable = (reims) => {

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

        console.log("Adding " + e + "to table");

        let row = document.createElement('tr');
        let td1 = document.createElement('td');
        let td2 = document.createElement('td');
        let td3 = document.createElement('td');
        let td4 = document.createElement('td');
        let td5 = document.createElement('td');
        let td6 = document.createElement('td');

        d.setUTCSeconds(e.submitted.epochSecond);

        td1.innerHTML = e.id;
        td2.innerHTML = e.authorId.firstName;
        td3.innerHTML = e.typeId.reim_type;
        td4.innerHTML = e.description;
        td5.innerHTML = e.amount;
        td6.innerHTML = d;

        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
        row.appendChild(td5);
        row.appendChild(td6);

        reimTable.appendChild(row);
    })

}

function fetchReimbursements(reimStatus) {
    let hostname = window.location.hostname;  // this will grab the IP of where it's deployed  

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

        switch (reimStatus) {

            case 'PENDING':
                console.log("Pending table selected");
                createPendingTable(reimbursements);
                break;
            case 'APPROVED':
                console.log("Approved table selected");
                break;
            case 'DENIED':
                console.log("Denied table selected");
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
