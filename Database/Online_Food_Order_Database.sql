create sequence n1 increment by 1 start with 1201; --user_details
create sequence n3 increment by 1 start with 1601; --restaurant_details
create sequence n2 increment by 1 start with 1401; --food_items
create sequence n4 increment by 1 start with 1801; --order_foods

CREATE TABLE user_details ( user_id int default n1.nextval,  
                                user_name varchar2(30) not null,  
                                phone_no number not null, 
                                role varchar2(10) Default 'user',
                                address varchar2(100) not null, 
                                email_address varchar2(100) not null,  
                                password varchar2(20) not null, 
                                wallet int Default '100000',
                                constraint con_user_id primary key(user_id),  
                                unique(email_address), unique(phone_no));                 
 
INSERT INTO user_details(user_name, phone_no, role, address, email_address, password, wallet) values('jeyram', 9500727441, 'Admin', '153WestStreet,Melur,625106', 'jeyram@gmail.com','Jeyram1010', 0);                         

CREATE TABLE restaurant_details ( restaurant_id int default n3.nextval, 
                                  restaurant_name varchar2(30) not null,
                                  area varchar2(100) not null, 
                                  city varchar2(100) not null,  
                                  pincode number not null,  
                                  restaurant_landline_no number not null, 
                                  owner_name varchar2(30) not null, 
                                  operational_hours varchar2(20) not null,
                                  email varchar2(50) not null, 
                                  password varchar2(20) not null,
                                  constraint con_restaurant_id primary key(restaurant_id), 
                                  unique(email),unique(restaurant_landline_no));
                                  
update food_items set price=70 where item_id =1423;                                  
CREATE TABLE food_items(restaurant_id int not null,
                        item_id int default n2.nextval, 
                        food_name varchar2(30) not null,
                        cuisine_name varchar2(30) not null,
                        description varchar2(100) not null,  
                        price number not null,
                        constraint con_item_id primary key(item_id),
                        constraint con_restaurant_id1 foreign key(restaurant_id) references restaurant_details(restaurant_id)); 

alter table food_items modify price number;

CREATE TABLE order_foods ( order_id int default n4.nextval, 
                           user_id int not null,
                           item_id int not null,  
                           quantity int not null, 
                           total_price decimal not null,
                           constraint con_orderfoodsid primary key(order_id), 
                           constraint con_userid foreign key(user_id) references user_details(user_id),
                           constraint con_orderitem_id foreign key(item_id) references food_items(item_id));
alter table order_foods modify total_price number(10,2);                           

CREATE TABLE order_details ( order_id int not null, 
                             user_id int not null, 
                             status varchar2(20) not null, 
                             order_date timestamp default current_timestamp,
                             constraint con_order_id1 foreign key(order_id) references order_foods(order_id),
                             constraint con_user_id1 foreign key(user_id) references user_details(user_id));

delete from user_details where user_id =1229;
commit;                             
desc food_items;
select * from user_details;
select * from restaurant_details;
select * from food_items;
select * from order_foods;
delete restaurant_details where restaurant_id = 1637;
drop table user_details cascade constraints;
select * from food_items where restaurant_id =1628;

delete from order_foods where user_id =1227;

alter table order_foods add order_date timestamp default current_timestamp;