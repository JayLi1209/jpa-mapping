# JPA Mapping Exercise

This is a practice on connecting database (SQL code) to Spring Boot using JPA Hibernate.

> Yes, it contains a lot of dumb and repetitive code - bear with me, I'm still learning!

Here's a introduction if you're interested:
The `Instructor` class has a `@OneToOne` relationship with `InstructorDetail`, and it also has `@OneToMany` relationship with `Course`.

For each `Course`, there will be a unidirectional `@OneToMany` mapping to `Review`. 

Each `Student` have a `@ManyToMany` relationship with `Course`. There's no exciting GUI for this project for now, but I did make this graph for clarity:

<img width="746" alt="Screen Shot 2023-07-19 at 10 36 53 PM" src="https://github.com/JayLi1209/jpa-mapping/assets/100735820/cab5d490-1675-45a1-a1e7-5a118e482f05">
