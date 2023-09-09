create table sub_member(
	sm_id varchar2(10 char) primary key, -- 아이디
	sm_pw varchar2(20 char) not null, -- 암호
	sm_name varchar2(10 char) not null, -- 이름
	sm_photo varchar2(100 char) not null, -- 프사
	sm_addr varchar2(100 char) not null, -- 우편번호/주소/상세주소
	sm_role char(1 char) not null -- 권한 (일반/관리자)
);

select * from sub_member;
---------------------------------------------------------------------------------
create table sub_Info(
	btrainNo varchar2(10 char) primary key,  -- 열차번호(현재운행하고 있는 호선별 열차번호)
	statnNm varchar2(10 char) not null, -- 지하철역명
	trnsitCo varchar2(10 char) not null, -- 환승노선수
	btrainSttus varchar2(10 char) not null, -- 열차종류(급행,ITX,일반)
	barvlDt varchar2(10 char) not null, -- 열차도착예정시간(단위:초)
	bstatnNm varchar2(10 char) not null, --  종착지하철역명
	recptnDt date not null, -- 열차도착정보를 생성한 시각
	arvlMsg2 varchar2(20 char) not null-- 도착메세지(도착, 출발 , 진입 등)
);

select * from sub_Info;
---------------------------------------------------------------------------------
create table board(
	b_no number(4) primary key, -- 게시판 번호
	b_title varchar2(100 char) not null, -- 제목
	b_owner varchar2(10 char) not null, -- 오너
	b_when date not null, -- 등록일
	b_updateWhen date not null, -- 수정일
	b_txt varchar2(300 char) not null, -- 글내용
	b_viewcnt number(6) not null, -- 조회수
	b_good number(4) not null, -- 좋아요 증감수
	b_likeCheck number(1) not null -- 좋아요 여부(1/0)
);
create sequence board_seq; 

select * from board order by b_no;
-----------
create table reply(
	r_no number(5) primary key, -- 댓글번호
	r_b_no number(4) not null, -- 소속된 글 번호
	r_owner varchar2(10 char) not null, -- 댓글 작성자
	r_txt varchar2(200 char) not null, -- 댓글 내용
	r_when date not null, -- 댓글 등록 날짜
	constraint board_reply
		foreign key(r_b_no) references board(b_no)
		on delete cascade
);
create sequence reply_seq;

select * from reply order by r_when desc;
