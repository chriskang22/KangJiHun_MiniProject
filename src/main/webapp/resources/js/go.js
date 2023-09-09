//go.js

function signupGo(){
	let s = confirm('회원가입 페이지로 이동하시겠습니까?');
	if(s){
		location.href = "member.signupGo";
	}
}

function logout(){
	let lo = confirm('로그아웃 하시겠습니까?');
	if(lo){
		location.href = "member.logout";
	}
}

function memberInfoGo(){
	let mifg = confirm('회원정보로 이동하시겠습니까?');
	if(mifg){
		location.href = "member.info.go";
	}
}

function memberDelete(){
	let md = confirm('회원탈퇴 하시겠습니까?');
	if(md){
		location.href = "member.delete";
	}
}

//지하철 정보 초기화
function subwayDelete() {
	let del = confirm('초기화를 하시겠습니까?');
	if(del){
		location.href = "subway.delete";
	}
}

//지하철 정보 페이징 처리
function subwayPageChange(page){
	location.href = "subway.page.change?p=" + page;
}

//게시판 생성 페이지로 이동
function boardWrite(){
	let w = confirm('게시글 작성 페이지로 이동 하시겠습니까?');
	if(w){
		location.href = "board.write.go";
	}
}

//게시판 페이징 처리
function boardPageChange(page) {
	location.href = "board.page.change?p=" + page;
}

//게시글 상세정보 페이지
function boardDetailGO(no) {
	let bd = confirm('게시글 상세정보 페이지로 이동 하시겠습니까?');
	if(bd){
		location.href = "board.detail.go?b_no=" + no;
	}
}

//게시판 삭제
function btnDelete(no) {
	let del = confirm('삭제하시겠습니까?');
	if(del){
		location.href = "boardDetail.delete?b_no=" + no;
	}
}

//게시판 수정
function btnUpdate(no, txt) {
	txt = prompt("수정할 글내용 : ", txt);
	if (txt != null && txt.length > 0 && txt.length < 250) {
		location.href = "boardDetail.update?b_no=" + no + "&b_txt=" + txt;
	}
}

//댓글 삭제
function replyDelete(r_no, r_b_no) {
	let rd = confirm('삭제하시겠습니까?');
	if(rd){
		location.href = "reply.delete?r_no=" + r_no + "&r_b_no=" + r_b_no;
	}
}