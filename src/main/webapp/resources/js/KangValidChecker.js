//KangValidChecker.js

//잘못되었으면 true(뭔가 잘못되면 true, 정상적이면 false)

//<input>이 있을 때
//아무것도 안썼으면 true, 뭐라도 썼으면 false
function isEmpty(input){
	return (!input.value);
}

//<input>이 있을 때
//한글 / 특수문자가 적혀있으면 true, 없다면 false
function containsAnother(input){
	let pass = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFG@_.';
	
	let iv = input.value;
	for(let i = 0; i < iv.length; i++){
		if(pass.indexOf(iv[i]) == -1){ //pass부분에 있는 내용 중에서
			return true; //내가 쓴 내용이 없으면
		}
	}
	return false;
}

//<input>, 글자수 넣으면
//원하는 글자수보다 적으면 true, 글자수 이상이면 false
function atLeastLetter(input, len){
	return (input.value.length < len);
}

//<input> x 2 넣었을 때
//내용이 서로 다르면 true, 같으면 false (PW, PW확인)
function notEqualPW(input1, input2){
	return (input1.value != input2.value);
}

//<input>, 문자열 세트 넣었을 때
//없으면 true, 있으면 false(입력한 PW에 지정한 문자열이 없는 경우)
function notContain(input, passSet){
	let iv = input.value;
	for(let i = 0; i < passSet.length; i++){
		if(iv.indexOf(passSet[i]) != -1){
			return false;
		}
	}
	return true;
}

//<input> 넣었을 때
//숫자가 아닌게 있으면 true, 없으면 false
function isNotNumber(input){
	return isNaN(input.value);
}

//<input>, 확장자 넣었을 때
//지정한 확장자가 아니면 true, 맞으면 false
function isNotType(input, type){
	//asdf.pdf
	type = "." + type;
	return (input.value.indexOf(type) == -1);
}
