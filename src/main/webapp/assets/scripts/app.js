const exampleModal = document.getElementById('exampleModal')
exampleModal.addEventListener('show.bs.modal', event => {
  // Button that triggered the modal
  const button = event.relatedTarget
  // Extract info from data-bs-* attributes
  
  // If necessary, you could initiate an AJAX request here
  // and then do the updating in a callback.
  //
  // Update the modal's content.
 
  const modalBodyInput = exampleModal.querySelector('.modal-body input')


  modalBodyInput.value = recipient
});

const reimbModal = document.getElementById('reimbModal')
reimbModal.addEventListener('show.bs.modal', event => {
  // Button that triggered the modal
  const button2 = event.relatedTarget
  // Extract info from data-bs-* attributes
  
  // If necessary, you could initiate an AJAX request here
  // and then do the updating in a callback.
  //
  // Update the modal's content.
 
  const modalBodyInput2 = reimbModal.querySelector('.modal-body input')


  modalBodyInput.value = recipient
});