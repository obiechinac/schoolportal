package com.springstatesman.devapp.service;
import com.springstatesman.devapp.entity.Block;
import com.springstatesman.devapp.entity.Course;
import com.springstatesman.devapp.entity.Room;
import com.springstatesman.devapp.entity.Schedule;
import com.springstatesman.devapp.entity.enums.DaysOfWeek;
import com.springstatesman.devapp.entity.enums.Periods;
import com.springstatesman.devapp.entity.enums.Venues;
import com.springstatesman.devapp.repository.BlockRepository;
import com.springstatesman.devapp.repository.RoomRepository;
import com.springstatesman.devapp.repository.ScheduleRepository;
import com.springstatesman.devapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by HP on 5/10/2021.
 */
@Service
public class ScheduleService {


    private ScheduleRepository scheduleRepository;


    private StudentService studentService;


    private LecturerService lecturerService;

    private CourseService courseService;

    private RoomRepository roomRepository;

    private BlockRepository blockRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, StudentService studentService,
                           LecturerService lecturerService, CourseService courseService, RoomRepository roomRepository,
                           BlockRepository blockRepository) {
        this.scheduleRepository = scheduleRepository;
        this.studentService = studentService;
        this.lecturerService = lecturerService;
        this.courseService = courseService;
        this.roomRepository = roomRepository;
        this.blockRepository = blockRepository;
    }

    public void saveSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    public List<Schedule> getScheduleByStudentId(long id) {
//        List<Schedule> schedules = this.studentRepository.getOne(id).getSchedules();
        List<Course> courses = this.courseService.findCourseByStudentId(id);
        List<Schedule> schedules = new ArrayList<>();
        for (Course course : courses
                ) {
            schedules = this.courseService.getCourseSchedules(course.getCourseId());
        }

        return schedules;

    }

    public List<Schedule> getScheduleByLecturerId(Long id) {
        List<Course> courses = this.courseService.findCourseByLecturerId(id);
        List<Schedule> schedules = new ArrayList<>();
        for (Course course : courses) {
            schedules = this.courseService.getCourseSchedules(course.getCourseId());
        }

        return schedules;
    }

    public List<Schedule> findScheduleByCourseId(long id) {
        return this.scheduleRepository.findAllByCourseIs(id);
    }


    public void generateSchedule(Course course) {

        Course course1 = this.courseService.getById(course.getCourseId());
        Venues venues1 = course1.getVenues();
        scheduleAuto(course1, venues1);
    }

    public void scheduleAuto(Course course, Venues venues) {
        double duration = course.getCreditUnit();
        Random r = new Random();
        List<Block> blocks = this.blockRepository.findAllByVenuesIs(venues);
        int rj = r.nextInt(blocks.size());
        Block block = blocks.get(rj);
        int rdnWk = r.nextInt(5);
        int rdnPrd = r.nextInt(21);
        if (!(course.getCourseName() == null)) {


            List<Room> rooms = this.roomRepository.findAll();
            Room room = rooms.get(r.nextInt(rooms.size()));
            if (rdnPrd > 21 || rdnWk > 5) {
                rdnPrd = rdnPrd - 5;
                rdnWk = rdnWk - 2;

            } else if (rdnPrd < 0 || rdnWk < 0) {
                rdnPrd = rdnPrd + 5;
                rdnWk = rdnWk + 2;


            }

            if (checkForFreeSchedule(getWeekDay(rdnWk), getPeriods(rdnPrd), room, venues, block)) {

                if (duration == 1) {
                    Schedule schedule = new Schedule();
                    schedule.setDaysOfWeek(getWeekDay(rdnWk));
                    schedule.setPeriods(getSinglePeriods(rdnPrd));
                    schedule.setCourse(course);
                    schedule.setVenues(venues);
                    schedule.setBlock(block);
                    schedule.setRoom(room);

                    scheduleRepository.save(schedule);

                }
                if (duration == 2) {
                    Schedule schedule = new Schedule();
                    schedule.setDaysOfWeek(getWeekDay(rdnWk));
                    schedule.setPeriods(getDoublePeriods(rdnPrd));
                    schedule.setCourse(course);
                    schedule.setVenues(venues);
                    schedule.setBlock(block);
                    schedule.setRoom(room);

                    scheduleRepository.save(schedule);

                }
                if (duration == 3) {
                    Schedule schedule = new Schedule();
                    schedule.setDaysOfWeek(getWeekDay(rdnWk));
                    schedule.setPeriods(getDoublePeriods(rdnPrd));
                    schedule.setCourse(course);
                    schedule.setVenues(venues);
                    schedule.setBlock(block);
                    schedule.setRoom(room);

                    scheduleRepository.save(schedule);
                    Block block1 = new Block();
                    rj += 1;
                    if (rj > blocks.size()) {
                        rj -= 2;
                    } else {
                        block1 = blocks.get(rj);
                    }

//                    shuffle for room
                    room = rooms.get(r.nextInt(rooms.size() - 2));
                    Long rmId = room.getRoomId() - 2;
                    Room room1 = this.roomRepository.getOne(rmId);


                    Schedule tempSchedule = new Schedule();
                    rdnPrd = rdnPrd + 5;
                    System.out.println(" rdnwk firs = " + rdnWk);
                    rdnWk += 2;
                    System.out.println(" rdnwk seco = " + rdnWk);
                    if (rdnPrd > 21 || rdnWk > 5) {
                        rdnPrd = rdnPrd - 5;
                        rdnWk = rdnWk - 3;

                    }
                    System.out.println(" rdnwk thir = " + rdnWk);

                    tempSchedule.setDaysOfWeek(getWeekDay(rdnWk));
                    tempSchedule.setPeriods(getSinglePeriods(rdnPrd));
                    tempSchedule.setCourse(course);
                    tempSchedule.setVenues(venues);
                    tempSchedule.setBlock(block1);
                    tempSchedule.setRoom(room1);

                    scheduleRepository.save(tempSchedule);

                }

            }

        }


    }

    public void autoSchedule(Course course, Venues venues) {
        int duration = course.getDuration();

        Random r = new Random();

        List<Block> blocks = this.blockRepository.findAllByVenuesIs(venues);
        int ri = r.nextInt(5);
        int ru = r.nextInt(21);
        int rj = r.nextInt(blocks.size());

        Block block = blocks.get(rj);
        for (int i = 0; i < 5; i++) {
            System.out.println("running once >>>>>>");
            Course course1 = this.courseService.getById(course.getCourseId());
            List<Schedule> scheduleList = course1.getSchedules();
            if (scheduleList.size() == 2) break;
            else {

                for (int j = 0; j < 21; j++) {

                    System.out.println("now looping j >>>>");
                    Course course2 = this.courseService.getById(course.getCourseId());
                    List<Schedule> scheduleList2 = course2.getSchedules();
                    if (scheduleList2.size() == 2) break;
                    else {

                        if (!(course.getCourseName() == null)) {

//
//                            List<Room> rooms = this.roomRepository.findAll();
//                            Room room = rooms.get(r.nextInt(rooms.size()));
//
//                            if (checkForFreeSchedule(getWeekDay(i), getPeriods(j), room, venues, block)) {
//
////
//                                if (duration == 1) {
//                                    Schedule schedule = new Schedule();
//                                    schedule.setDaysOfWeek(getWeekDay(i));
//                                    schedule.setPeriods(getSinglePeriods(j));
//                                    schedule.setCourse(course);
//                                    schedule.setVenues(venues);
//                                    schedule.setBlock(block);
//
//                                    scheduleRepository.save(schedule);
//
//                                }
//                                if (duration == 2) {
//                                    Schedule schedule = new Schedule();
//                                    schedule.setDaysOfWeek(getWeekDay(i));
//                                    schedule.setPeriods(getDoublePeriods(j));
//                                    schedule.setCourse(course);
//                                    schedule.setVenues(venues);
//                                    schedule.setBlock(block);
//
//                                    scheduleRepository.save(schedule);
//
//                                }
//                                if (duration == 3) {
//                                    Schedule schedule = new Schedule();
//                                    schedule.setDaysOfWeek(getWeekDay(i));
//                                    schedule.setPeriods(getDoublePeriods(j));
//                                    schedule.setCourse(course);
//                                    schedule.setVenues(venues);
//                                    schedule.setBlock(block);
//
//                                    scheduleRepository.save(schedule);
//
//                                    block = blocks.get(r.nextInt(blocks.size()));
//
//
//                                    Schedule tempSchedule = new Schedule();
//                                    tempSchedule.setDaysOfWeek(getWeekDay(j));
//                                    tempSchedule.setPeriods(getSinglePeriods(i));
//                                    tempSchedule.setCourse(course);
//                                    tempSchedule.setVenues(venues);
//                                    tempSchedule.setBlock(block);
//
//                                    scheduleRepository.save(tempSchedule);
//
//                                }
//
//                            }

                        }
                    }
                }
            }
        }
    }


    private DaysOfWeek getWeekDay(int index) {

        if (index == 1) return DaysOfWeek.MONDAY;
        if (index == 2) return DaysOfWeek.TUESDAY;
        if (index == 3) return DaysOfWeek.WEDNESDAY;
        if (index == 4) return DaysOfWeek.THURSDAY;
        if (index == 5) return DaysOfWeek.FRIDAY;
//        if (index==5)return DaysOfWeek.TUESDAY;

        else return DaysOfWeek.MONDAY;

    }

    private Venues getVenue(int index) {
        if (index == 0) return Venues.FIRST_CAMPUS;
        if (index == 1) return Venues.SECOND_CAMPUS;
        return null;
    }

    private Periods getPeriods(int index) {
        if (index == 0) return Periods.ONE;
        if (index == 1) return Periods.TWO;
        if (index == 2) return Periods.THREE;
        if (index == 3) return Periods.FOUR;
        if (index == 4) return Periods.FIVE;
        if (index == 5) return Periods.SIX;
        if (index == 6) return Periods.SEVEN;
        if (index == 7) return Periods.EIGHT;
        if (index == 8) return Periods.NINE;
        if (index == 9) return Periods.TEN;
        if (index == 10) return Periods.ELEVEN;
        if (index == 11) return Periods.TWELVE;
        if (index == 12) return Periods.THIRTEEN;
        if (index == 13) return Periods.FOURTEEN;
        if (index == 14) return Periods.FIFTEEN;
        if (index == 15) return Periods.SIXTEEN;
        if (index == 16) return Periods.SEVENTEEN;
        if (index == 17) return Periods.EIGHTEEN;
        if (index == 18) return Periods.NENETEEN;
        if (index == 19) return Periods.TWENTY;
        if (index == 20) return Periods.TWENTYONE;
//        if (index==21)return Periods.twen


        else return null;
    }

    private Periods getSinglePeriods(int index) {
        if (index == 0 || index == 1 || index == 18) return Periods.ONE;
        if (index == 2 || index == 3 || index == 19) return Periods.FOUR;
        if (index == 4 || index == 5 || index == 20) return Periods.SEVEN;
        if (index == 6 || index == 7) return Periods.TEN;
        if (index == 8 || index == 9) return Periods.THIRTEEN;
        if (index == 10 || index == 11) return Periods.FIFTEEN;
        if (index == 12 || index == 13) return Periods.SEVENTEEN;
        if (index == 14 || index == 15) return Periods.NENETEEN;
        if (index == 16 || index == 17 || index == 21) return Periods.TWENTYONE;
        return null;
    }

    private Periods getDoublePeriods(int index) {
        if (index == 0 || index == 1 || index == 16) return Periods.TWO;
        if (index == 2 || index == 3 || index == 17) return Periods.FIVE;
        if (index == 4 || index == 5 || index == 18) return Periods.EIGHT;
        if (index == 6 || index == 7 || index == 19) return Periods.ELEVEN;
        if (index == 8 || index == 9 || index == 20) return Periods.FOURTEEN;
        if (index == 10 || index == 11) return Periods.FIFTEEN;
        if (index == 12 || index == 13) return Periods.EIGHTEEN;
        if (index == 14 || index == 15 || index == 21) return Periods.TWENTY;


        return null;
    }

    private Periods getTriplePeriods(int index) {
        if (index == 0) return Periods.THREE;
        if (index == 1) return Periods.NINE;
        if (index == 2) return Periods.TWELVE;
        if (index == 3) return Periods.SIXTEEN;
        return null;
    }


//    private boolean checkForFreeCourse(Course course, DaysOfWeek daysOfWeek, Periods periods) {
//        boolean yes = true;
//        List<Course> courses = courseService.getAllCourses();
//        List<Schedule> schedules = new ArrayList<>();
//        for (Course course1 : courses) {
//            schedules = course1.getSchedules();
//            for (Schedule schedule : schedules) {
//                if (schedule.getCourse() == course && schedule.getDaysOfWeek() == daysOfWeek && schedule.getPeriods() == periods)
//                    yes = false;
//            }
//
//        }
//        return yes;
//    }

    private boolean checkForFreeSchedule(DaysOfWeek daysOfWeek, Periods periods, Room room, Venues venues, Block block) {

        boolean yes = true;
        List<Course> courses = courseService.getAllCourses();
        Block block1 = this.blockRepository.getOne(block.getBlockId());
        List<Schedule> schedules = new ArrayList<>();
        for (Course course1 : courses) {
            schedules = course1.getSchedules();
            Room roomList = new Room();
            if (!(schedules.isEmpty())) {
                for (Schedule schedule : schedules) {
                    room = schedule.getRoom();

                    if (schedule.getVenues() == venues && schedule.getDaysOfWeek() == daysOfWeek && schedule.getPeriods() == periods
                            && schedule.getBlock() == block && roomList == room)
                        yes = false;

                }
            }
        }
        return yes;
    }


    public Block getBlock(long i) {
        Block block = this.blockRepository.getOne(i);
        return block;
    }


    public List<Schedule> findAllSchedulea() {
        List<Schedule> schedules = this.scheduleRepository.findAll();
        return schedules;
    }

    public void deleteAll(List<Schedule> scheduleList) {

        this.scheduleRepository.deleteAll();
    }
}
