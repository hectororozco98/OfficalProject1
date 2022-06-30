const startAddReimbursementButton = document.querySelector('header button');
const addReimbursementModal = document.getElementById('add-modal');
const backdrop = document.getElementById('backdrop');
const cancelAddReimbursementButton = addReimbursementModal.querySelector('.btn--passive');
const confirmAddReimbursementButton = cancelAddReimbursementButton.nextElementSibling;
const userInputs = document.querySelectorAll('input');
const reimType = document.querySelector('select')
const reimbursements = [];


const renderNewReimbursementElement = (id, amount, description, type) => {

    const newReimbursementElement = document.createElement('li');
    newReimbursementElement.className = 'reimbursement-element';

    newReimbursementElement.innerHTML = `
    <div class="reimbursement-element__info">
      <h1>Description: ${description}</h1>
      <h2>Amount: $${amount}</h2>
      <p>${type}</p>
    </div>
  `;

  const listRoot = document.getElementById('reimbursement-list');
  listRoot.append(newReimbursementElement);
}

const toggleReimbursementModal = () => {

    addReimbursementModal.classList.toggle('visible');
    toggleBackDropHandler();
}

const cancelAddReimbursementHandler = () => {

    toggleReimbursementModal();
}

const toggleBackDropHandler = () => {

    backdrop.classList.toggle('visible');
}

const confirmAddReimbursementHandler = () => {

    const amountValue = userInputs[0].value;
    const descriptionValue = userInputs[1].value;
    const typeValue = reimType.value;

    if (
        +amountValue < 1 ||
        descriptionValue.trim() === '' 
    ) {
        alert('Please enter valid values (amount must be greater than 1)')
        return; // prevent the function from continuing execution 
    }

    const newReimbursement = {
        amount: amountValue,
        description: descriptionValue,
        type: typeValue
    }

    // Call acynchronous method to Stringify json object and send it to server
    sendReimbursementToServer(newReimbursement);


    reimbursements.push(newReimbursement);
    console.log(reimbursements);

    toggleReimbursementModal();
    clearReimbursementInput();

    renderNewReimbursementElement(newReimbursement.id, newReimbursement.amount, newReimbursement.description, newReimbursement.type);
}

const clearReimbursementInput = () => {

    for (let userInput of userInputs) {
        userInput.value = '';
    }
    reimType.value = "LODGING";
}

const sendReimbursementToServer = (newReimbursement) => {

    console.log('SendReimbursementToServer incoked')

    
    fetch(`http://localhost:8080/official-project-one/reimbursements`, {

        method: "POST",

        body: JSON.stringify({
            amount: newReimbursement.amount,
            description: newReimbursement.description,
            type: newReimbursement.type
        }),

        headers: {
            "Content-type": "application/json; charset=UTF-8",
          },
    })
    .then((response) => response.json())
    .then((json) => console.log(json));
}

startAddReimbursementButton.addEventListener('click', toggleReimbursementModal);
backdrop.addEventListener('click', toggleReimbursementModal);
cancelAddReimbursementButton.addEventListener('click', cancelAddReimbursementHandler);
confirmAddReimbursementButton.addEventListener('click', confirmAddReimbursementHandler);