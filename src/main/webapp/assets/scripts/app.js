const exampleModal = document.getElementById('exampleModal');
const reimbModal = document.getElementById('reimbModal');

const userInputsInfo = exampleModal.querySelectorAll('input');

const reimInputs = reimbModal.querySelectorAll('input');
const reimSelect = reimbModal.querySelector('select');

const submitInfoButton = exampleModal.querySelector('.btn.btn-success');
const submitReimButton = reimbModal.querySelector('.btn.btn-success');
const viewButton = document.getElementById('viewReimbs');


submitInfoButton.addEventListener('click', submitUpdate);
submitReimButton.addEventListener('click', submitReim);
viewButton.addEventListener('click', viewReim);

function fetchUser() {
	
	let hostname = window.location.hostname;
	fetch(`http://localhost:8080/official-project-one/login`, {

        method: 'GET',
        headers: {
            "Content-type": "application/json; charset=UTF-8",
        },
        body: JSON.stringify({
            username: u.username,
            firstname: u.firstname,
            lastname: u.lastname,
            password: u.password,
            email: u.email
            
        }),
    })	
	};


function updateUser() {

    let firstName = userInputsInfo[0].value;
    let lastName = userInputsInfo[1].value;
    let username = userInputsInfo[2].value;
    let password = userInputsInfo[3].value;
    let email = userInputsInfo[4].value;

    fetch(`http://localhost:8080/official-project-one/update`, {
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
        .then((json) => console.log(json));	
};

function submitUpdate() {

    updateUser();
    alert("User info sucessfully updated!")

};


function submitReim() {

    let amount = reimInputs[0].value;
    let description = reimInputs[1].value;
    let type = reimSelect.value;


    fetch(`http://localhost:8080/official-project-one/reimbursements`, {

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
}



function viewReim() {
    fetch(`http://localhost:8080/official-project-one/view-filed-reimbursements`, {
    
}





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

