# DB에 추가 시 계정 추가
# CREATE DATABASE krowdpoping;
# CREATE USER 'jellybears'@'%' IDENTIFIED BY  'jellybears';
# GRANT ALL PRIVILEGES ON krowdpoping.* TO 'jellybears'@'%';
#
# USE krowdpoping;

create table category
(
    category_code       int auto_increment comment '카테고리코드'
        primary key,
    category_title      varchar(255) null comment '카테고리제목',
    upper_category_code int          null comment '상위카테고리코드',
    constraint category_FK
        foreign key (upper_category_code) references category (category_code)
)
    comment '카테고리';

create table community_files
(
    file_no           int auto_increment comment '파일번호'
        primary key,
    origin_file_name  varchar(255) not null comment '원본파일명',
    changed_file_name varchar(255) not null comment '변경된파일명',
    update_date       varchar(255) not null comment '등록일',
    whether_deleted   char         not null comment '삭제여부',
    board_code        int          not null comment '커뮤니티코드'
)
    comment '커뮤니티파일목록';

create table creator_notice_file
(
    cr_notice_file_code int auto_increment comment '첨부파일코드'
        primary key,
    origin_name         varchar(255) null comment '원본파일명',
    update_name         varchar(255) null comment '변경된파일명',
    register_date       varchar(255) null comment '등록일',
    delete_opt          tinyint(1)   null comment '삭제여부'
)
    comment '창작자공지사항첨부파일';

create table creator_report_category
(
    creator_report_category_code  int auto_increment comment '창작자 신고 카테고리코드'
        primary key,
    creator_report_category_title varchar(255) null comment '창작자 신고 카테고리제목'
)
    comment '창작자 신고 카테고리';

create table creator_tmp
(
    cr_type      int           not null comment '창작자유형',
    business_num varchar(255)  null comment '사업자등록번호',
    address      varchar(255)  null comment '주소',
    user_code    int           not null comment '회원코드',
    phone        varchar(255)  not null comment '전화번호',
    email        varchar(255)  not null comment '이메일',
    introduce    varchar(3000) null comment '소개',
    identi_num   varchar(255)  null comment '주민등록번호',
    issue_type   int           null comment '세금계산서 발행구분',
    acc_user_nm  varchar(20)   null comment '세금계산서 발행 성명(상호명)',
    ceo_nm       varchar(20)   null comment '대표자명',
    creator_nm   varchar(20)   not null comment '창작자 이름'
);

create table delivery_address_info
(
    delivery_code           int auto_increment comment '배송지코드'
        primary key,
    address_title           varchar(255) not null comment '배송지명',
    recipient_name          varchar(255) not null comment '받는사람',
    recipient_address       varchar(255) not null comment '주소',
    recipient_phone_number  varchar(255) not null comment '연락처',
    delivery_comment        varchar(255) null,
    whether_default_address varchar(1)   null comment '기본배송지여부'
)
    comment '배송지정보';

create table email
(
    email_code        int auto_increment
        primary key,
    email_certificate varchar(255) not null
);

create table faq
(
    faq_code        int auto_increment comment 'FAQ 코드'
        primary key,
    faq_title       varchar(255)  not null comment '답변 제목',
    faq_content     varchar(3000) not null comment '답변 내용',
    faq_category    varchar(255)  not null comment '답변 카테고리',
    whether_deleted char          not null comment '삭제여부',
    update_date     date          not null comment '등록일'
)
    comment 'FAQ';

create table item
(
    item_code int auto_increment comment '아이템코드'
        primary key,
    name      varchar(30) not null comment '아이템명'
)
    comment '아이템코드';

create table judge_status
(
    judge_status_code int auto_increment comment '심사상태코드'
        primary key,
    judge_status      varchar(255) not null comment '심사상태'
)
    comment '심사상태';

create table policy_file
(
    file_code int auto_increment comment '파일 코드',
    file_seq  int           not null comment '파일 일련번호',
    file_name varchar(255)  null comment '파일명',
    save_name varchar(255)  null comment '변경된 파일명',
    file_path varchar(1000) null comment '파일 경로',
    primary key (file_code, file_seq)
)
    comment '심사 서류';

create table priceplan
(
    priceplan_code int auto_increment comment '요금제코드'
        primary key,
    type           varchar(30) not null comment '요금제종류',
    percent        varchar(30) not null comment '수수료'
)
    comment '요금제';

create table project_report_category
(
    project_report_category_code  int auto_increment comment '프로젝트 신고 카테고리코드'
        primary key,
    project_report_category_title varchar(255) null comment '프로젝트 신고 카테고리제목'
)
    comment '프로젝트 신고 카테고리';

create table project_status
(
    status_code    int auto_increment comment '상태코드'
        primary key,
    project_status varchar(255) not null comment '프로젝트상태'
)
    comment '프로젝트상태';

create table role
(
    role_code int auto_increment comment '권한코드'
        primary key,
    role_name varchar(255) not null comment '권한명'
)
    comment '권한';

create table tag
(
    tag_code     int auto_increment comment '태그코드'
        primary key,
    content      varchar(30) not null comment '태그내용',
    project_code int         not null comment '프로젝트코드'
)
    comment '태그';

create table user_info
(
    user_code    int auto_increment comment '회원코드'
        primary key,
    id           varchar(255)              not null comment '아이디',
    password     varchar(255)              not null comment '비밀번호',
    name         varchar(255)              not null comment '이름',
    nickname     varchar(255)              not null comment '닉네임',
    birthday     date                      not null comment '생년월일',
    email        varchar(255)              not null comment '이메일',
    phone_number varchar(255)              not null comment '전화번호',
    user_status  varchar(255) default '일반' null comment '회원상태',
    email_code   int                       null,
    exit_status  char         default 'N'  not null comment '탈퇴여부',
    exit_date    date                      null comment '탈퇴일',
    join_date    date                      null comment '가입일',
    constraint user_info_ibfk_1
        foreign key (email_code) references email (email_code)
)
    comment '회원정보';

create table creator
(
    cr_type       int           null comment '창작자유형',
    business_num  varchar(255)  null comment '사업자등록번호',
    address       varchar(255)  null comment '주소',
    user_code     int           not null comment '회원코드'
        primary key,
    phone         varchar(255)  null comment '전화번호',
    email         varchar(255)  null comment '이메일',
    inquiry_email varchar(255)  null comment '문의 이메일',
    introduce     varchar(3000) null comment '소개',
    identi_num1   varchar(255)  null comment '주민등록번호 앞자리',
    issue_type    int           null comment '세금계산서 발행구분',
    acc_user_nm   varchar(20)   null comment '세금계산서 발행 성명(상호명)',
    ceo_nm        varchar(20)   null comment '대표자명',
    creator_nm    varchar(20)   null comment '창작자 이름',
    name          varchar(255)  null comment '성명(개인)',
    identi_num2   varchar(255)  null comment '주민등록번호 뒷자리',
    constraint creator_user_info_user_code_fk
        foreign key (user_code) references user_info (user_code)
)
    comment '창작자정보';

create table creator_files
(
    profile_code  int auto_increment comment '첨부파일코드'
        primary key,
    origin_name   varchar(255) null comment '원본파일명',
    update_name   varchar(255) null comment '변경된파일명',
    register_date date         null comment '등록일',
    delete_opt    tinyint(1)   null comment '삭제여부',
    user_code     int          null comment '회원코드',
    constraint creator_profile_FK
        foreign key (user_code) references creator (user_code)
)
    comment '창작자프로필첨부파일';

create table creator_report
(
    creator_report_code int auto_increment comment '창작자 신고 코드'
        primary key,
    report_title        varchar(255) not null comment '제목',
    report_content      varchar(255) not null comment '내용',
    report_category     int          not null comment '창작자 신고 사유 카테고리',
    reported_date       date         not null comment '등록일자',
    whether_deleted     char         not null comment '삭제여부',
    whether_completed   varchar(255) not null comment '처리여부',
    completed_date      date         null comment '처리일자',
    reporter            int          not null comment '작성자',
    reported_creator    int          not null comment '신고대상창작자',
    constraint creator_report_FK
        foreign key (reporter) references user_info (user_code),
    constraint creator_report_FK1
        foreign key (report_category) references creator_report_category (creator_report_category_code),
    constraint creator_report_user_info_user_code_fk
        foreign key (reported_creator) references user_info (user_code)
)
    comment '창작자신고';

create table creator_sns
(
    sns_code  int auto_increment comment '창작자SNS코드'
        primary key,
    sns_type  varchar(255) null comment 'SNS구분',
    user_code int          not null comment '회원코드',
    constraint creator_sns_FK
        foreign key (user_code) references creator (user_code)
)
    comment '창작자SNS';

create table delivery_address
(
    user_code     int not null comment '회원코드',
    delivery_code int not null comment '배송지코드',
    primary key (user_code, delivery_code),
    constraint delivery_address_FK
        foreign key (delivery_code) references delivery_address_info (delivery_code),
    constraint delivery_address_FK1
        foreign key (user_code) references user_info (user_code)
)
    comment '회원배송지목록';

create table following
(
    creator_code      int not null comment '창작자',
    default_user_code int not null comment '일반회원',
    primary key (creator_code, default_user_code),
    constraint following_FK
        foreign key (creator_code) references creator (user_code),
    constraint following_FK1
        foreign key (default_user_code) references user_info (user_code)
)
    comment '팔로우';

create table inquiry
(
    inquiry_code    int auto_increment comment '문의번호'
        primary key,
    inquiry_type    varchar(255)  not null comment '문의타입',
    inquiry_title   varchar(255)  not null comment '문의제목',
    inquiry_content varchar(3000) not null comment '문의내용',
    inquiry_date    date          not null comment '문의일자',
    inquiry_status  varchar(255)  not null comment '문의상태',
    questioner      int           null comment '질문자',
    replier         int           null comment '답변자',
    constraint Inquiry_FK
        foreign key (replier) references user_info (user_code),
    constraint inquiry_user_info_user_code_fk
        foreign key (questioner) references user_info (user_code)
)
    comment '1:1문의';

create table admin_reply
(
    reply_num       int auto_increment
        primary key,
    inquiry_num     int           null,
    reply_title     varchar(255)  null,
    reply_content   varchar(3000) null,
    reply_user_code int           null,
    reply_date      date          null,
    constraint admin_reply_ibfk_1
        foreign key (inquiry_num) references inquiry (inquiry_code),
    constraint admin_reply_ibfk_2
        foreign key (reply_user_code) references user_info (user_code)
);

create index inquiry_num
    on admin_reply (inquiry_num);

create index reply_user_code
    on admin_reply (reply_user_code);

create table notice
(
    notice_code    int auto_increment comment '공지사항 코드'
        primary key,
    notice_title   varchar(255)  not null comment '제목',
    notice_content varchar(3000) not null comment '내용',
    update_date    timestamp     not null comment '작성일자',
    user_code      int           not null comment '회원코드',
    notice_type    varchar(255)  not null,
    notice_count   int default 0 not null,
    constraint Notice_FK
        foreign key (user_code) references user_info (user_code)
)
    comment '공지사항';

create table notice_files
(
    file_code         int auto_increment comment '첨부파일코드'
        primary key,
    origin_file_name  varchar(255) null comment '원본파일명',
    changed_file_name varchar(255) null comment '변경된파일명',
    update_date       date         null comment '등록일',
    whether_deleted   char         null comment '삭제여부',
    notice_code       int          not null comment '공지사항 코드',
    constraint notice_files_FK
        foreign key (notice_code) references notice (notice_code)
)
    comment '공지사항첨부파일';

create table project
(
    project_code   int auto_increment comment '프로젝트코드'
        primary key,
    title          varchar(255)  not null comment '프로젝트명',
    category_code  int           not null comment '카테고리코드',
    content        varchar(3000) not null comment '프로젝트소개',
    start_date     timestamp     null comment '펀딩시작일',
    end_date       timestamp     null comment '펀딩종료일',
    target_amount  bigint        null comment '목표금액',
    funding_status varchar(255)  null comment '펀딩상태',
    update_date    date          null comment '수정일자',
    plan_content   blob          null comment '프로젝트일정계획',
    budget_content blob          null comment '프로젝트예산계회',
    info_content   blob          null,
    user_code      int           null comment '회원코드',
    edit_status    varchar(255)  null,
    sum_pay_amount bigint        null comment '누적 금액',
    judge_status   varchar(255)  null,
    priceplan_code int           null,
    constraint Project_FK
        foreign key (user_code) references creator (user_code),
    constraint project_FK2
        foreign key (category_code) references category (category_code),
    constraint project_priceplan_priceplan_code_fk
        foreign key (priceplan_code) references priceplan (priceplan_code)
)
    comment '프로젝트';

create table alarm
(
    alarm_code        int auto_increment comment '알림코드'
        primary key,
    send_user_code    int          not null comment '보낸사람',
    receive_user_code int          not null comment '받는사람',
    alarm_type        varchar(255) not null comment '알림구분',
    alarm_check       char         not null comment '확인여부',
    project_code      int          not null comment '코드',
    constraint Alarm_FK
        foreign key (project_code) references project (project_code),
    constraint Alarm_FK1
        foreign key (receive_user_code) references user_info (user_code),
    constraint Alarm_FK2
        foreign key (send_user_code) references user_info (user_code)
)
    comment '알림';

create table calculate
(
    cal_code        int auto_increment comment '정산코드'
        primary key,
    sum_pay_amount  bigint      not null comment '총모금액',
    total_money     bigint      not null comment '총결제금액',
    money           bigint      not null comment '정산금액',
    project_code    int         not null comment '프로젝트코드',
    user_code       int         null comment '회원 코드',
    identinum_front varchar(10) null comment '주민등록번호 앞자리',
    constraint calculate_FK
        foreign key (project_code) references project (project_code),
    constraint user_code
        foreign key (user_code) references creator (user_code)
)
    comment '정산';

create table community
(
    board_code    int auto_increment comment '커뮤니티코드'
        primary key,
    board_title   varchar(255)  not null comment '제목',
    board_content varchar(3000) not null comment '내용',
    writer_code   int           not null comment '작성자',
    update_date   date          not null comment '등록일',
    project_code  int           not null comment '프로젝트코드',
    constraint community_FK
        foreign key (project_code) references project (project_code),
    constraint writer___fk
        foreign key (writer_code) references user_info (user_code)
)
    comment '커뮤니티(게시판)';

create table community_comment
(
    comment_code    int auto_increment comment '댓글코드'
        primary key,
    comment_writer  int          not null comment '댓글작성자',
    comment_content varchar(255) not null comment '댓글내용',
    write_time      date         not null comment '작성시간',
    whether_deleted char         not null comment '삭제여부',
    upper_comment   int          null comment '상위댓글코드',
    board_code      int          not null comment '커뮤니티코드',
    constraint comment_FK
        foreign key (upper_comment) references community_comment (comment_code),
    constraint comment_FK1
        foreign key (board_code) references community (board_code),
    constraint comment__fk2
        foreign key (comment_writer) references user_info (user_code)
)
    comment '댓글';

create table creator_file
(
    file_code     int auto_increment comment '서류코드'
        primary key,
    origin_name   varchar(255) not null comment '원본파일명',
    update_name   varchar(255) null comment '변경된파일명',
    register_date date         not null comment '등록일',
    file_type     varchar(255) not null comment '서류타입',
    file_content  varchar(255) null comment '파일구분',
    project_code  int          not null comment '프로젝트코드',
    delete_opt    tinyint(1)   null,
    constraint creator_file_FK
        foreign key (project_code) references project (project_code)
)
    comment '심사서류';

create table creator_notice
(
    notice_code   int auto_increment comment '창작자공지사항코드'
        primary key,
    title         varchar(255)  null comment '제목',
    content       varchar(5000) null comment '내용',
    register_date date          null comment '등록일',
    project_code  int           not null comment '프로젝트코드',
    file_code     int           not null comment '첨부파일코드',
    constraint creator_notice_FK
        foreign key (file_code) references creator_notice_file (cr_notice_file_code),
    constraint creator_notice_FK1
        foreign key (project_code) references project (project_code)
)
    comment '창작자공지사항';

create table creator_notice_comment
(
    comment_code    int auto_increment comment '댓글코드'
        primary key,
    writer          varchar(255) null comment '댓글작성자',
    content         varchar(255) null comment '댓글내용',
    time            time(6)      null comment '작성시간',
    delete_opt      tinyint(1)   null comment '삭제여부',
    up_comment_code int          null comment '상위댓글코드',
    notice_code     int          not null comment '창작자공지사항코드',
    constraint creator_notice_comment_FK
        foreign key (notice_code) references creator_notice (notice_code),
    constraint creator_notice_comment_creator_notice_comment_comment_code_fk
        foreign key (up_comment_code) references creator_notice_comment (comment_code)
)
    comment '창작자공지사항댓글';

create table goods
(
    goods_code   int auto_increment comment '선물코드'
        primary key,
    name         varchar(30) not null comment '선물이름',
    quantity     int         null comment '선물수량',
    project_code int         not null comment '프로젝트코드',
    amount       bigint      not null,
    constraint Goods_FK
        foreign key (project_code) references project (project_code)
)
    comment '선물구성';

create table goods_details
(
    item_code     int not null,
    goods_code    int not null,
    item_quantity int null,
    primary key (item_code, goods_code),
    constraint goods_details_goods_goods_code_fk
        foreign key (goods_code) references goods (goods_code),
    constraint goods_details_item_item_code_fk
        foreign key (item_code) references item (item_code)
);

create table judge_status_list
(
    project_code      int not null comment '프로젝트코드',
    judge_status_code int not null comment '심사상태코드',
    primary key (judge_status_code, project_code),
    constraint judge_status_list_FK1
        foreign key (project_code) references project (project_code),
    constraint judge_status_list_FK2
        foreign key (judge_status_code) references judge_status (judge_status_code)
)
    comment '심사상태내역';

create table payment
(
    pay_code          int auto_increment comment '결제코드'
        primary key,
    pay_approval_code varchar(255) null comment '승인코드',
    user_code         int          not null comment '회원코드',
    delivery_code     int          null comment '배송지코드',
    goods_code        int          not null,
    payed_time        datetime     null,
    total_amount      int          null,
    constraint payment_FK1
        foreign key (user_code) references user_info (user_code),
    constraint payment_ibfk_1
        foreign key (goods_code) references goods (goods_code)
)
    comment '결제';

create table pay_cancellation
(
    cancellation_code   int auto_increment
        primary key,
    pay_code            int          not null,
    pay_status          varchar(255) not null,
    cancellation_amount int          not null,
    cancellation_date   date         not null,
    cancellation_reason varchar(255) null,
    constraint pay_cancellation_ibfk_1
        foreign key (pay_code) references payment (pay_code)
);

create index pay_code
    on pay_cancellation (pay_code);



create index goods_code
    on payment (goods_code);

create index pay_approval_code
    on payment (pay_approval_code);

create table policy
(
    policy_code     int           not null
        primary key,
    project_code    int           not null,
    file_code       int           not null,
    basic_policy    varchar(2000) null,
    difficulty_info varchar(2000) null,
    product_type    varchar(255)  null,
    product_name    int default 0 null,
    size            varchar(1000) null,
    material        varchar(1000) null,
    specification   varchar(1000) null,
    caution         varchar(1000) null,
    constraint file_code
        foreign key (file_code) references policy_file (file_code),
    constraint project_code
        foreign key (project_code) references project (project_code)
);

create index file_code_idx
    on policy (file_code);

create table project_files
(
    projectfile_code int auto_increment comment '프로젝트파일'
        primary key,
    project_code     int           not null comment '프로젝트코드',
    original_name    varchar(255)  not null comment '원본파일명',
    saved_name       varchar(255)  not null comment '변경된파일명',
    save_path        varchar(1000) null,
    file_type        varchar(255)  not null comment '파일종류',
    thumbnail_path   varchar(255)  null,
    whether_deleted  char          not null comment '삭제여부',
    update_date      date          not null comment '등록일',
    constraint ProjectFiles_FK
        foreign key (project_code) references project (project_code)
)
    comment '프로젝트파일목록';

create table project_like
(
    project_code int not null comment '프로젝트코드',
    user_code    int not null comment '회원코드',
    primary key (project_code, user_code),
    constraint project_like_FK
        foreign key (project_code) references project (project_code),
    constraint project_like_FK1
        foreign key (user_code) references user_info (user_code)
)
    comment '프로젝트찜';

create table project_list
(
    project_code  int          not null comment '프로젝트 코드'
        primary key,
    title         varchar(500) null comment '프로젝트 제목',
    image         varchar(255) null comment '프로젝트 이미지',
    achieve_rate  int          null comment '달성률',
    achieve_money int          null comment '현재 금액',
    days          date         null comment '남은 기간',
    current_money int          null comment '현재 금액',
    constraint project_code_fk
        foreign key (project_code) references project (project_code)
)
    comment '프로젝트목록';

create table project_report
(
    project_report_code int auto_increment comment '프로젝트 신고 코드'
        primary key,
    report_title        varchar(255)  not null comment '제목',
    report_content      varchar(3000) not null comment '내용',
    user_code           int           not null comment '작성자',
    project_code        int           not null comment '신고 대상 프로젝트',
    report_category     int           not null comment '프로젝트 신고 사유 카테고리',
    report_date         date          not null comment '작성일자',
    whether_deleted     char          not null comment '삭제여부',
    whether_completed   varchar(255)  null comment '처리여부',
    completed_date      date          null comment '처리일자',
    constraint project_report_FK
        foreign key (project_code) references project (project_code),
    constraint project_report_FK1
        foreign key (user_code) references user_info (user_code),
    constraint project_report_FK2
        foreign key (report_category) references project_report_category (project_report_category_code)
)
    comment '프로젝트신고';

create table project_status_content
(
    status_code  int not null comment '상태코드',
    project_code int not null comment '프로젝트코드',
    primary key (status_code, project_code),
    constraint project_status_content_FK
        foreign key (project_code) references project (project_code),
    constraint project_status_content_FK1
        foreign key (status_code) references project_status (status_code)
)
    comment '프로젝트상태내역';

create table recent_delivery_address
(
    recent_delivery_code int auto_increment comment '최근배송지코드'
        primary key,
    address_upload_date  date not null comment '등록일자',
    user_code            int  not null comment '회원코드',
    delivery_code        int  not null comment '배송지코드',
    constraint recent_delivery_address_FK
        foreign key (delivery_code) references delivery_address_info (delivery_code),
    constraint recent_delivery_address_FK1
        foreign key (user_code) references user_info (user_code)
)
    comment '최근배송지';

create table report_files
(
    file_no                 int auto_increment comment '파일번호'
        primary key,
    origin_file_name        varchar(255) not null comment '원본파일명',
    changed_file_name       varchar(255) not null comment '변경된파일명',
    update_date             date         not null comment '등록일',
    whether_deleted         char         not null comment '삭제여부',
    ref_project_report_code int          not null comment '프로젝트신고 코드',
    save_path               varchar(255) null,
    constraint report_files_project_project_code
        foreign key (ref_project_report_code) references project_report (project_report_code)
)
    comment '신고 첨부파일';

create table role_type
(
    user_code int not null comment '회원코드',
    role_code int not null comment '권한코드',
    primary key (user_code, role_code),
    constraint role_type_FK
        foreign key (role_code) references role (role_code),
    constraint role_type_FK1
        foreign key (user_code) references user_info (user_code)
)
    comment '권한목록';

create table support_info
(
    support_code int auto_increment comment '후원코드'
        primary key,
    goods_code   int          not null comment '옵션',
    status       varchar(255) not null comment '상태',
    support_date date         not null comment '후원날짜',
    project_code int          not null comment '프로젝트코드',
    user_code    int          not null comment '회원코드',
    constraint Support_info_FK
        foreign key (goods_code) references goods (goods_code),
    constraint Support_info_FK1
        foreign key (project_code) references project (project_code),
    constraint Support_info_FK2
        foreign key (user_code) references user_info (user_code)
)
    comment '후원정보';

create table tax
(
    tax_code  int auto_increment comment '세금계산서코드'
        primary key,
    issue_opt tinyint(1) null comment '발행여부',
    date      date       null comment '발행일자',
    user_code int        not null comment '회원코드',
    constraint tax_FK
        foreign key (user_code) references creator (user_code)
)
    comment '세금계산서';

create table user_file_list
(
    file_code          int auto_increment comment '사진번호'
        primary key,
    original_file_name varchar(255) not null comment '원본파일명',
    edited_file_name   varchar(255) null comment '변경된파일명',
    upload_date        date         not null comment '등록일',
    whether_deleted    char         not null comment '삭제여부',
    user_code          int          not null comment '회원코드',
    constraint user_file_list_FK
        foreign key (user_code) references user_info (user_code)
)
    comment '회원파일목록(회원사진)';

create index email_code
    on user_info (email_code);

create table user_report_category
(
    user_report_category_code  int auto_increment comment '유저 신고 카테고리코드'
        primary key,
    user_report_category_title varchar(255) null comment '유저 신고 카테고리제목'
)
    comment '유저 신고 카테고리';

create table user_report
(
    user_report_code  int auto_increment comment '유저 신고 코드'
        primary key,
    report_title      varchar(255)  not null comment '제목',
    report_content    varchar(3000) not null comment '내용',
    report_category   int           not null comment '유저 신고 사유 카테고리',
    reported_date     date          not null comment '등록일자',
    whether_deleted   char          not null comment '삭제여부',
    whether_completed varchar(255)  not null comment '처리여부',
    completed_date    date          null comment '처리일자',
    reporter          int           not null comment '작성자',
    reported_user     int           not null comment '신고대상유저',
    constraint user_report_FK
        foreign key (reporter) references user_info (user_code),
    constraint user_report_FK1
        foreign key (report_category) references user_report_category (user_report_category_code),
    constraint user_report_user_info_user_code_fk
        foreign key (reported_user) references user_info (user_code)
)
    comment '유저신고';

create table pay_info
(
    detailed_pay_code int auto_increment comment '내역코드'
        primary key,
    pay_status        varchar(255) not null comment '결제상태',
    payed_time        date         not null comment '처리일자',
    pay_approval_code varchar(255) null,
    canceled_time     date         null,
    constraint pay_info_ibfk_1
        foreign key (pay_approval_code) references payment (pay_approval_code)
)
    comment '결제내역';

create index pay_approval_code
    on pay_info (pay_approval_code);
