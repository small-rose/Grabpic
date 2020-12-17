-- tb_url
create table tb_url(
id VARCHAR(36),
addr_name VARCHAR(128),
addr_desc VARCHAR(120),
is_del VARCHAR(18),
is_mark VARCHAR(10),
is_fetch VARCHAR(10),
addr_code VARCHAR(20),
add_time VARCHAR(19)
);

-- tb_pic
create table tb_pic(
pic_id VARCHAR(36),
url_id VARCHAR(36),
pic_addr VARCHAR(256),
pic_name VARCHAR(128),
pic_oldname VARCHAR(128),
pic_suffix VARCHAR(10),
pic_type VARCHAR(10),
pic_size BIGINT(10),
pic_path VARCHAR(256),
pic_del VARCHAR(5),
pic_mark VARCHAR(5),
pic_no VARCHAR(10),
pic_desc VARCHAR(10),
pic_content BLOB,
add_time VARCHAR(19)
) ;



-- web_site
create table tb_web_site(
web_id VARCHAR(36),
web_url VARCHAR(128),
web_name VARCHAR(50),
web_desc VARCHAR(128),
web_open VARCHAR(10),
add_time VARCHAR(19)
) ;


create table tb_web_category(
category_id VARCHAR(36),
web_id VARCHAR(36),
parent_id VARCHAR(36),
category_url VARCHAR(128),
category_name VARCHAR(50),
category_mark VARCHAR(10),
page_tag VARCHAR(128),
page_tag_attr VARCHAR(200),
page_keys VARCHAR(50),
page_total int(10),
add_time VARCHAR(19)
) ;


create table tb_web_link(
link_id VARCHAR(36),
page_id VARCHAR(36),
link_no int(10),
link_herf VARCHAR(128),
link_name VARCHAR(50),
link_valid VARCHAR(10),
img_tag VARCHAR(128),
img_tag_attr VARCHAR(200),
link_mark VARCHAR(50),
pic_total int(10),
add_time VARCHAR(19)
) ;


create table tb_web_page(
page_id VARCHAR(36),
category_id VARCHAR(36),
page_no int(10),
page_herf VARCHAR(128),
page_name VARCHAR(50),
page_mark VARCHAR(10),
link_tag VARCHAR(128),
link_tag_attr VARCHAR(200),
link_keys VARCHAR(50),
link_total int(10),
add_time VARCHAR(19)
) ;




create table tb_web_pic(
pic_id VARCHAR(36),
link_id VARCHAR(36),
pic_addr VARCHAR(200),
pic_name VARCHAR(128),
pic_oldname VARCHAR(128),
pic_desc VARCHAR(200),
pic_suffix VARCHAR(10),
pic_type VARCHAR(10),
pic_size INT(11),
pic_path VARCHAR(128),
pic_del VARCHAR(2),
pic_mark VARCHAR(2),
pic_no int(10),
add_time VARCHAR(19),
pic_content BLOB
) ;