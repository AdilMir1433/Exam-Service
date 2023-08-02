
function submitForm() {
   const mcq = document.getElementById("mcq");
   const text = document.getElementById("text");
   if(mcq.checked) {
      const form = document.getElementById("mcq-form");
      const question = document.getElementById("questionType");
      question.style.display = "none";
      form.style.display = "block";


   } else if(text.checked) {
        document.getElementById("text-form").submit();
   }
}

function addQuestion() {
    // Get the values of the input fields
    const question = document.getElementById('question').value;
    const option1 = document.getElementById('option1').value;
    const option2 = document.getElementById('option2').value;
    const option3 = document.getElementById('option3').value;
    const option4 = document.getElementById('option4').value;
    const a1 = document.getElementById('one')
    const a2 = document.getElementById('two')
    const a3 = document.getElementById('three')
    const a4 = document.getElementById('four')

    let answer = 1;
    if(a1.checked) {
        answer = 1;
    }
    else if(a2.checked) {
        answer = 2;
    }
    else if(a3.checked) {
        answer = 3;
    }
    else if(a4.checked) {
        answer = 4;
    }

    // Create a new list item to display the question and options
    const listItem = document.createElement('li');
    listItem.innerHTML = `
                <p><strong>Question:</strong> ${question}</p>
                <p><strong>Options:</strong></p>
                <ul>
                    <li>${option1}</li>
                    <li>${option2}</li>
                    <li>${option3}</li>
                    <li>${option4}</li>
                </ul>
                <p>Answer:<strong>${answer} </strong></p>
            `;

    // Append the list item to the quiz list
    const quizList = document.getElementById('quizList');
    quizList.appendChild(listItem);

    // Clear the input fields for the next question
    document.getElementById('question').value = '';
    document.getElementById('option1').value = '';
    document.getElementById('option2').value = '';
    document.getElementById('option3').value = '';
    document.getElementById('option4').value = '';
}

function finishQuiz() {
    // Disable the form to prevent further input
    document.getElementById('mcq-form').setAttribute('disabled', true);

    // Display a message indicating the quiz is finished
    const message = document.createElement('p');
    message.textContent = 'Quiz Finished!';
    document.body.appendChild(message);
}

function selectedOption() {


}