//Kang_JQuery.js
$(function() {
	//회원정보의 우편번호, 주소 input클릭할 때 검색 팝업창 띄우는 JS
	//회원수정할 때 쓰임!
	$('#info_addr1, #info_addr2').click(function() {
		new daum.Postcode({
			oncomplete : function(data) {
				$('sm_addr1').val(data.zonecode);
				$('sm_addr2').val(data.roadAddress);
			}
		}).open();
	});
});

$(function(){
	//회원가입의 우편번호, 주소 input클릭할 때 검색 팝업창 띄우는 JS
	//회원등록할 때 쓰임!
	$('#addr1, #addr2').click(function(){		
		new daum.Postcode({
	        oncomplete: function(data) {
	            $('#addr1').val(data.zonecode);
	            $('#addr2').val(data.roadAddress);
	        }
	    }).open();
	});
});	

//게시판 상세보기로 이동할 때 로그인 필수!
$(function() {
	$('.noLog').click(function() {				
		alert('로그인 후 이용해주세요!');
		location.href = "board.go";
		return false;
	})
});
