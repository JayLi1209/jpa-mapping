# JPA Mapping Exercise

This is a practice on connecting database (SQL code) to Spring Boot using JPA Hibernate.

> Yes, it contains a lot of dumb and repetitive code - bear with me, I'm still learning!

Here's a introduction if you're interested:
The `Instructor` class has a `@OneToOne` relationship with `InstructorDetail`, and it also has `@OneToMany` relationship with `Course`.

For each `Course`, there will be a unidirectional `@OneToMany` mapping to `Review`. There's no exciting GUI for this project for now. I'll try to make one if ime allowed.
