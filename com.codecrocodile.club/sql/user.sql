truncate table user;

INSERT INTO user(title, forename, surname, dob, gender, address1, address2, address3, postcode, homePhone, mobilePhone, email, photo, photoSource, 
  password, startDate, suspendedDate, retiredDate, membershipTypeId) 
VALUES ('Mr', 'Chris', 'Hatton', '20750820', 'M', '3 Aillort Place', 'East Mains', 'East Kilbride', 'G74 4LL', '0135521225', '078143577824', 'chris@codecrocodile.com', null, null, 
  null, 20140101 , null, null, 2);

select * from user;