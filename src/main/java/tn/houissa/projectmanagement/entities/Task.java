package tn.houissa.projectmanagement.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor // default constructor
@AllArgsConstructor  //
@RequiredArgsConstructor // custom constructor ==> for final and not null fields
@ToString
// anotate @ToString.Exclude to make Lombok ignore it or @ToString(onlyExplicitlyIncluded = true) and anotate fields with @ToString.Include
//@Data  // it replaces @ToString, @EqualsAndHashCode, @Getter @Setter, and @RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity

public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    int task_id;

    @NonNull
    @Column(name = "title")
    String title;

    @NonNull
    @Column(name = "description")
    String description;

    @NonNull
    @Column(name = "due_date")
    Date dueDate;

    @NonNull
    @Column(name = "start_time")
    LocalTime startTime;
    @NonNull
    @Column(name = "end_time")
    LocalTime endTime;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    TaskStatus status;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    TaskPriority priority;
    @ManyToOne
    @JsonIgnore
    User user;


}
