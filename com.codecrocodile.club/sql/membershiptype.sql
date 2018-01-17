truncate table membershiptype_lu;

insert into membershiptype_lu (name, description)
values ('Junior', 'Members under the age of 16.');

insert into membershiptype_lu (name, description)
values ('Standard', 'Members over 16, but under reirement age.');

insert into membershiptype_lu (name, description)
values ('Senior', 'Members at retirement age.');

select * from membershiptype_lu;