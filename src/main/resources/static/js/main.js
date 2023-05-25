const userActionButton = document.getElementById("userAction");
const csrfParameterName = "${(_csrf.parameterName)!'_csrf'}";
const csrfToken = "${(_csrf.token)!'--no-token--'}";

const userEmail = document.getElementById("userEmail");

if (userEmail !== null) {
    userActionButton.innerHTML = `
    <div class="auth-block">
      <form action="/logout" method="post" class="auth-btn p-4">
        <input type="hidden" name="${csrfParameterName}" value="${csrfToken}" />
        <button type="submit" class="btn btn-primary">Logout</button>
      </form>
    </div>
  `;
} else {
    userActionButton.innerHTML = '<a href="/login"><i class="bi bi-person-fill"></i></a>';

}