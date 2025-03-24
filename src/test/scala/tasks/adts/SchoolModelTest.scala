package tasks.adts

import u03.extensionmethods.Sequences.*
import Sequence.Nil
import org.junit.Assert.assertEquals
import org.junit.Test
import tasks.adts.SchoolModel.BasicSchoolModule.*

class SchoolModelTest {

  @Test def testEmptySchool(): Unit =
    val school = emptySchool
    assertEquals(Nil(), school.teachers)
    assertEquals(Nil(), school.courses)


  @Test def testTeachersList(): Unit = ???




}
