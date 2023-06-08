select s.name, s.age from student s left join faculty f on s.faculty_id = f.id;


select s.* from student s inner join avatars a on a.student_id = s.id;