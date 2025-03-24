package tasks.adts

import u03.extensionmethods.Sequences.*
import Sequence.*
import org.junit.Assert.{assertEquals, assertFalse, assertTrue}
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import tasks.adts.SchoolModel.BasicSchoolModule.*

class SchoolModelTest {


  @Test def testEmptySchool(): Unit =
    val school = emptySchool
    assertEquals(Nil(), school.teachers)
    assertEquals(Nil(), school.courses)


  @Test def testTeachersList(): Unit =
    val school = emptySchool
    val john = teacher("John")
    val mark = teacher("Mark")
    val math = course("Math")
    val italian = course("Italian")
    val school2 = school.setTeacherToCourse(john, math)
    val school3 = school2.setTeacherToCourse(mark, italian)
    assertEquals(Nil(), school.teachers)
    assertEquals(Cons("John", Nil()), school2.teachers)
    assertEquals(Cons("John", Cons("Mark", Nil())), school3.teachers)

  @Test def testCoursesList(): Unit =
    val school = emptySchool
    val john = teacher("John")
    val mark = teacher("Mark")
    val math = course("Math")
    val italian = course("Italian")
    val school2 = school.setTeacherToCourse(john, math)
    val school3 = school2.setTeacherToCourse(mark, italian)
    assertEquals(Nil(), school.courses)
    assertEquals(Cons("Math", Nil()), school2.courses)
    assertEquals(Cons("Math", Cons("Italian", Nil())), school3.courses)

  @Test def testHasTeacher(): Unit =
    val school = emptySchool
    val john = teacher("John")
    val math = course("Math")
    val school2 = school.setTeacherToCourse(john, math)
    assertFalse(school.hasTeacher("John"))
    assertTrue(school2.hasTeacher("John"))

  @Test def testHasCourse(): Unit =
    val school = emptySchool
    val john = teacher("John")
    val math = course("Math")
    val school2 = school.setTeacherToCourse(john, math)
    assertFalse(school.hasCourse("Math"))
    assertTrue(school2.hasCourse("Math"))

  @Test def testTeacherToCourses(): Unit =
    val school = emptySchool
    val john = teacher("John")
    val math = course("Math")
    val italian = course("Italian")
    val school2 = school.setTeacherToCourse(john, math)
    val school3 = school2.setTeacherToCourse(john, italian)
    assertEquals(Cons("Math",Cons("Italian",Nil())), school3.coursesOfATeacher(john))

}
