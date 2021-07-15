package com.springstatesman.devapp.service;
import com.springstatesman.devapp.entity.Course;
import com.springstatesman.devapp.entity.Lecturer;
import com.springstatesman.devapp.entity.Schedule;
import com.springstatesman.devapp.entity.Student;
import com.springstatesman.devapp.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by HP on 1/20/2021.
 */
@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private LecturerService lecturerService;
//    @Autowired
//    private ScheduleService scheduleService;

    public void saveCourse(Course course) {

         courseRepository.save(course);
    }

    public Optional<Course> findById(Long id){return this.courseRepository.findById(id);}
//    public void addCourse(String name,int duration,String code,double unit){courseRepository.save(new Course(name,duration,code,unit));}

    public List<Course> getAllCourses(){ return this.courseRepository.findAll();
    }
    public Page<Course> getAllCourses(Pageable pageable){return courseRepository.findAll(pageable);}

    public Course getById(Long id){
        return this.courseRepository.getOne(id);
    }

    public void deleteById(Long id){
        this.courseRepository.deleteById(id);
    }

    public void editCourse(Course course){
        this.courseRepository.save(course);
    }

    public List<Course> findByDepartment(long progId){
        List<Course> courses = this.courseRepository.findAllByPrograme(progId);
        return courses;

    }

    public  void editCourse2(Course c){
        Course tempCourse = this.courseRepository.getOne(c.getCourseId());
        tempCourse.setCourseName(c.getCourseName());
        tempCourse.setPrograme(c.getPrograme());
        tempCourse.setCourseCode(c.getCourseCode());
        tempCourse.setDuration(c.getDuration());
        courseRepository.save(tempCourse);
    }

   public void addLecturerToCourse(long id ,Lecturer lecturer){
        Course course = this.courseRepository.getOne(id);
        List<Lecturer> lecturers = course.getLecturers();
        lecturers.add(lecturer);
        this.courseRepository.save(course);
   }

   public List<Course> findCourseByStudentId(Long id){
       Student student = this.studentService.getById(id);
       List<Course> courses = student.getCourses();
       return courses;
   }

//    public List<Course> findCourseByLecturerId(Long id) {
////        System.out.println("..>>>  "+id);
//       Lecturer lecturer = this.lecturerService.getLecturerById(id);
//
//       List<Course> courses = lecturer.getCourses();
//
//       return courses;
//    }
//    public List<Lecturer> findLecturerByCourseName(String courseName){
//       List<Lecturer> lecturers = this.lecturerService.getAllByCourse(courseName);
////        Lecturer lecturer = this.lecturerService.getLecturerById(id);
//
////        List<Course> courses = lecturer.getCourses();
//
//        return lecturers;
//    }

    public List<Schedule> getCourseSchedules(Long id){
       Course course = this.courseRepository.getOne(id);
//       List<Schedule> scheduleList = this.scheduleService.findScheduleByCourseId(id);
        List<Schedule>  scheduleList  = course.getSchedules();

       return scheduleList;
    }

    public void saveEditCourse(Course course) {
        Course course1 = this.courseRepository.getOne(course.getCourseId());
        course.setCourseId(course1.getCourseId());
//        course.setPrograme();
        courseRepository.save(course);
    }


    public List<Course> findCourseByLecturerId(Long id) {
//        System.out.println("..>>>  "+id);
        Lecturer lecturer = this.lecturerService.getById(id);

        List<Course> courses = lecturer.getCourses();

        return courses;
    }
}
