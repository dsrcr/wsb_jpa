INSERT INTO address (address_line1, address_line2, city, postal_code)
VALUES
    ('123 Main St', 'Apt 101', 'New York', '10001'),
    ('456 Elm St', 'Apt 202', 'Los Angeles', '90001'),
    ('789 Oak St', 'Apt 303', 'Chicago', '60001'),
    ('101 Pine St', 'Apt 404', 'Houston', '77001'),
    ('202 Maple St', 'Apt 505', 'Phoenix', '85001'),
    ('303 Birch Rd', 'Suite 10B', 'San Francisco', '94101'),
    ('505 Cedar Ave', 'Unit 12', 'Miami', '33101'),
    ('707 Willow Ln', 'Apt 7C', 'Seattle', '98101'),
    ('909 Aspen Blvd', 'Floor 2', 'Denver', '80201'),
    ('111 Walnut St', 'Room 8A', 'Boston', '02101');

INSERT INTO doctor (doctor_number, first_name, last_name, telephone_number, email, specialization, address_id)
VALUES
    ('DOC001', 'John', 'Doe', '555-0101', 'johndoe@email.com', 'GP', 1),
    ('DOC002', 'Jane', 'Smith', '555-0102', 'janesmith@email.com', 'DERMATOLOGIST', 2),
    ('DOC003', 'Emily', 'Brown', '555-0103', 'emilybrown@email.com', 'GP', 3),
    ('DOC004', 'Michael', 'Johnson', '555-0104', 'michaeljohnson@email.com', 'OCULIST', 4),
    ('DOC005', 'Sarah', 'Williams', '555-0105', 'sarahwilliams@email.com', 'SURGEON', 5);

INSERT INTO patient (patient_number, first_name, last_name, telephone_number, email, date_of_birth, address_id, gender, id_card_number)
VALUES
    ('PAT001', 'Alice', 'Green', '555-1001', 'alicegreen@email.com', '1990-05-14', 6, 'F', 'ID123456789'),
    ('PAT002', 'Bob', 'White', '555-1002', 'bobwhite@email.com', '1985-07-22', 7, 'M', 'ID987654221'),
    ('PAT003', 'Charlie', 'Black', '555-1003', 'charlieblack@email.com', '1992-11-30', 8, 'M', 'ID135792468'),
    ('PAT004', 'Daisy', 'Blue', '555-1004', 'daisyblue@email.com', '1988-03-12', 9, 'F', 'ID246813102'),
    ('PAT005', 'Eve', 'Red', '555-1005', 'evered@email.com', '1995-09-18', 10, 'F', 'ID102938475');


INSERT INTO medical_treatment (description, type)
VALUES
    ('Ultrasound', 'USG'),
    ('Skin biopsy', 'RTG'),
    ('Vaccination', 'ECG'),
    ('Eye scan', 'ECG'),
    ('X-ray', 'RTG');

INSERT INTO visit (description, time, doctor_id, medical_treatment_id, patient_id)
VALUES
    ('Annual checkup', '2024-01-01 10:00:00', 1, 3, 1),
    ('Consultation for skin rash', '2024-02-15 11:00:00', 2, 2, 2),
    ('Pediatric checkup', '2024-03-10 09:00:00', 3, 3, 3),
    ('Neurology consultation', '2024-04-05 14:30:00', 4, 4, 4),
    ('Orthopedic examination', '2024-05-12 13:00:00', 5, 5, 5),
    ('Follow-up consultation', '2024-06-15 10:00:00', 1, 1, 2),
    ('Eye examination', '2024-07-20 12:30:00', 4, 4, 1),
    ('Dental checkup', '2024-08-25 15:00:00', 5, 2, 3),
    ('Cardiology follow-up', '2024-09-10 16:30:00', 3, 5, 4);

