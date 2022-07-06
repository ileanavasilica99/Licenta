function validate(){
    const username = document.getElementById("loginUsername").value;
    const password = document.getElementById("loginPassword").value;
    if ( username === "student@student.com" && password === "student#123"){
        alert ("Login successfully");
        window.open("http://localhost:63342/OrientationAndOrganizationApp/templates/student_home.html?_ijt=f34pjq2f30a17jq987u7dv660s&_ij_reload=RELOAD_ON_SAVE");
        return false;
    }
    else if ( username === "teacher@teacher.com" && password === "teacher#123"){
        alert ("Login successfully");
        window.open("http://localhost:63342/OrientationAndOrganizationApp/templates/teacher_home.html?_ijt=f34pjq2f30a17jq987u7dv660s&_ij_reload=RELOAD_ON_SAVE");
        return false;
    } else if ( username === "admin@admin.com" && password === "admin#123") {
        alert ("Login successfully");
        window.open("http://localhost:63342/OrientationAndOrganizationApp/templates/admin_home.html?_ijt=f34pjq2f30a17jq987u7dv660s&_ij_reload=RELOAD_ON_SAVE");
        return false;
    } else {
        alert("Your credentials are incorrect !");

    }
}