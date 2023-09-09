//KangCheck

//회원가입 유효성 검사
function signUpCheck(){
	let idBox = document.signUpForm.sm_id;
	let pwBox = document.signUpForm.sm_pw;
	let pwChkBox = document.signUpForm.sm_pwChk;
	let nameBox = document.signUpForm.sm_name;
	let photoBox = document.signUpForm.sm_photo;
	let addrBox1 = document.signUpForm.sm_addr1;
	let addrBox2 = document.signUpForm.sm_addr2;
	let addrBox3 = document.signUpForm.sm_addr3;
	let roleBox = document.signUpForm.sm_role;
	if(isEmpty(idBox)){
		alert('ID를 입력하세요?(최대 10자 까지)');
		idBox.value="";
		idBox.focus();
		return false;
	}
	else if (isEmpty(pwBox)) {
		alert('암호를 입력하세요?(최대 20자 까지만 가능)');
		pwBox.value="";
		pwBox.focus();
		return false;
	}
	else if (isEmpty(pwChkBox) || notEqualPW(pwBox, pwChkBox)) {
		alert('위에 암호와 같게 입력하세요?');
		pwChkBox.value="";
		pwChkBox.focus();
		return false;
	}
	else if (isEmpty(nameBox)) {
		alert('이름을 입력하세요?');
		nameBox.value="";
		nameBox.focus();
		return false;
	}
	else if (isEmpty(photoBox)) {
		alert('사진을 꼭 넣어주세요?');
		photoBox.value="";
		photoBox.focus();
		return false;
	}
	else if (isEmpty(addrBox1) || isEmpty(addrBox2) || isEmpty(addrBox3)) {
		alert('주소를 입력하세요?');
		addrBox1.value="";
		addrBox2.value="";
		addrBox3.value="";
		addrBox3.focus();
		return false;
	}
	else if (isEmpty(roleBox)) {
		alert('둘중 하나를 선택하세요?');
		roleBox.value="";
		roleBox.focus();
		return false;
	}
	return true;
}

//로그인 유효성 검사
function loginCheck(){
	let idLog = document.loginForm.sm_id;
	let pwLog = document.loginForm.sm_pw;
	if(isEmpty(idLog)){
		alert('ID를 입력하세요?(최대 10자 까지)');
		idLog.value="";
		idLog.focus();
		return false;
	}
	else if (isEmpty(pwLog)) {
		alert('암호를 입력하세요?(최대 20자 까지)');
		pwLog.value="";
		pwLog.focus();
		return false;
	}
	return true;
}

//회원정보 수정 유효성 검사
function memberUpdateCheck(){
	let pwU = document.memberUpdateForm.sm_pw;
	let nameU = document.memberUpdateForm.sm_name;
	let photoU = document.memberUpdateForm.sm_photo;
	let addrU1 = document.memberUpdateForm.sm_addr1;
	let addrU2 = document.memberUpdateForm.sm_addr2;
	let addrU3 = document.memberUpdateForm.sm_addr3;
	if(isEmpty(pwU)){
		alert('암호 입력하세요?');
		pwU.value="";
		pwU.focus();
		return false;
	}
	else if (isEmpty(nameU)) {
		alert('이름 입력하세요?');
	}
	else if (isEmpty(photoU) || (isNotType(photoU, "png") && 
			isNotType(photoU, "gif") && isNotType(photoU, "jpg"))) {
		alert('프사 넣으세요?');
		photoU.value="";
		photoU.focus();
		return false;
	}
	else if (isEmpty(addrU1) || isEmpty(addrU2) || isEmpty(addrU3)) {
		alert('주소 입력하세요?');
		addrU1.value="";
		addrU2.value="";
		addrU3.value="";
		addrU3.focus();
		return false;
	}
	return true;
}

//지하철 정보 검색 유효성 검사
function subwaySearchCheck(){
	let ss = document.subwaySearchForm.search;
	if(isEmpty(ss)){
		alert('검색를 입력하세요?');
		ss.value="";
		ss.focus();
		return false;
	}
	return true;
}

//게시판 글 생성 유효성 검사
function boardWriteCheck() {
	let title = document.boardWriteForm.b_title;
	let txt = document.boardWriteForm.b_txt;
	if(isEmpty(title)){
		alert('제목을 입력하세요?');
		title.value="";
		title.focus();
		return false;
	}
	else if (isEmpty(txt)) {
		alert('내용을 입력하세요?');
		txt.value="";
		txt.focus();
		return false;
	}
	return true;
}

//게시판 목록 List 검색 유효성 검사
function boardSearchListCheck() {
	let bsl = document.boardSearchForm.search;
	if(isEmpty(bsl)){
		alert('검색어를 입력하세요?');
		bsl.value="";
		bsl.focus();
		return false;
	}
	return true;
}

//댓글 쓰기 유효성 검사
function replyWriteCheck() {
	let rTxt = document.replyWriteForm.r_txt;
	if(isEmpty(rTxt)){
		alert('댓글내용을 입력하세요?');
		rTxt.value="";
		rTxt.focus();
		return false;
	}
	return true;
}



