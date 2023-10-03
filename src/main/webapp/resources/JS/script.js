function validateString(str,exp){
    return exp.test(str);
}

function showError(id,msg) {
    document.getElementById(id).innerHTML=msg;
    document.getElementById(id).style.visibility='visible';
}



function formv(){
    //event.preventDefault();
    let nameExp=/^[a-zA-Z ]+$/;
    //let numExp=/^[0-9]+$/;
    
    let color=document.getElementById('col').value;
    let flag=true;
    
    console.log("inside form");
    //validate title
    if (validateString(title,nameExp)) {
        document.getElementById('colorerror').style.visibility='hidden';
    }else{
        showError('colorerror','* invalid color');
        flag=false;
    }
    
           return flag;

       
}